package com.team.mange.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5util {
    //加密工具类
    public static String getMD5Str(String str){
        byte[] digest = null;
        try{
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String md5Str = new BigInteger(1,digest).toString(16);

        return md5Str;
    }
}





















