package com.ht.util;

import org.springframework.util.StringUtils;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESSecretUtil {

    private static final int KEY_SIZE = 128;
    private static final String CIPHER_ALGORITHM = "AES";
    private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";

    private static Cipher getCipher(int mode, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CIPHER_ALGORITHM);
        SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        random.setSeed(key.getBytes());
        keyGenerator.init(KEY_SIZE, random);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encodeFormat = secretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(encodeFormat, CIPHER_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);// 创建密码器
        cipher.init(mode, secretKeySpec);// 初始化
        return cipher;
    }

    /**
     * @param data - 待加密内容
     * @param key - 加密秘钥
     */
    public static byte[] encrypt(String data, String key) {
        if(!StringUtils.hasText(data)){
            return null;
        }
        try {
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, key);
            byte[] byteContent = data.getBytes(StandardCharsets.UTF_8);
            return cipher.doFinal(byteContent);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param data - 待加密内容
     * @param key - 加密秘钥
     */
    public static String encryptToStr(String data, String key){

        return StringUtils.hasText(data) ? parseByte2HexStr(encrypt(data, key)) : null;
    }

    /**
     * @param data - 待解密字节数组
     * @param key - 秘钥
     */
    public static byte[] decrypt(byte[] data, String key) {
        if (data.length == 0) {
            return null;
        }
        try {
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param encryptData - 待解密字节数组
     * @param key - 秘钥
     */
    public static String decryptToStr(String encryptData, String key) {
        return StringUtils.hasText(encryptData) ? new String(decrypt(parseHexStr2Byte(encryptData), key)) : null;
    }

    /**
     * @param buf - 二进制数组
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * @param hexStr - 16进制字符串
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
