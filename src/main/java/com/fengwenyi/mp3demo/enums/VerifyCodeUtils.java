package com.fengwenyi.mp3demo.enums;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class VerifyCodeUtils {
    public static byte[] md5(String data) {
    return DigestUtils.md5(data);
}

    public static String md5AndBase64(String data) {
        return VerifyCodeUtils.base64Encode(md5(data));
    }

    public static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }
    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }
}
