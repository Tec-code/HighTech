package com.ht.common.auth;

import com.alibaba.fastjson.JSONObject;
import com.ht.model.UserInfo;
import com.ht.util.AESSecretUtil;
import com.ht.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import java.util.HashMap;
import java.util.Map;

public class TokenProvider {
    private static final Log logger = LogFactory.getLog(TokenProvider.class);

    @Value("${jwt.secret.key}")
    private String BASE64SECRET;

    @Value("${jwt.token.expireTime: 0}")
    private int EXPIRE_TIME;

    @Value("${jwt.data.encrypt.key}")
    private String DATA_ENCRYPT_KEY;

    public String getToken(UserInfo userInfo) {
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("userId", AESSecretUtil.encryptToStr(userInfo.getUserId().toString(), DATA_ENCRYPT_KEY));
        claimMap.put("userNo", userInfo.getUserNo());
        claimMap.put("userRole", userInfo.getRole());
        return JWTUtil.createToken(claimMap, BASE64SECRET, EXPIRE_TIME);
    }

    public String validateLogin(String jsonWebToken) {
        Map<String, Object> retMap = null;
        Claims claims = JWTUtil.parseToken(jsonWebToken, BASE64SECRET);
        if (claims != null) {
            String decryptUserId = AESSecretUtil.decryptToStr((String)claims.get("userId"), DATA_ENCRYPT_KEY);
            retMap = new HashMap<>();
            retMap.put("userId", decryptUserId);
            retMap.put("userNo", claims.get("userNo"));
            retMap.put("userRole", claims.get("userRole"));
        } else {
            logger.warn("[JWTUtil]-JWT解析出claims为空");
        }
        return retMap != null ? JSONObject.toJSONString(retMap) : null;
    }
}
