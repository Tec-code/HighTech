package com.ht.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static final Log logger = LogFactory.getLog(JWTUtil.class);

    /**
     * 格式：A.B.C
     * A-header头信息
     * B-payload 有效负荷
     * C-signature 签名信息 是将header和payload进行加密生成的
     */
    public static String createToken(Map<String, Object> claimMap, String base64key, int expireTime) {
        //签名算法，选择SHA-256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //将base64key字符串使用base64解码成字节数组
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64key);
        //使用HmacSHA256签名算法生成一个HS256的签名秘钥Key
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("alg", signatureAlgorithm.getValue());
        headMap.put("typ", "JWT");

        long nowTimeMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder().setHeader(headMap)
                .addClaims(claimMap)
                .setIssuedAt(new Date(nowTimeMillis))
                .setExpiration(new Date(nowTimeMillis + expireTime))
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    /**
     * 返回Claims对象
     * @param jsonWebToken - JWT
     */
    public static Claims parseToken(String jsonWebToken, String base64key) {
        Claims claims = null;
        try {
            if (StringUtils.hasText(jsonWebToken)) {
                //解析jwt
                claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64key))
                        .parseClaimsJws(jsonWebToken).getBody();
            } else {
                logger.warn("[JWTUtil]-json web token 为空");
            }
        } catch (Exception e) {
            logger.error("[JWTUtil]-JWT解析异常：可能因为 token 非法或已经超时");
        }
        return claims;
    }

}
