package com.czk.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    // 加密
    public static  String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }



}
