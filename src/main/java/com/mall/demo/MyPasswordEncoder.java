package com.mall.demo;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.*;


public class MyPasswordEncoder implements PasswordEncoder {

    private  final  static  String SALT = "123456";

    @Override
    public String encode(CharSequence charSequence) {

        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(charSequence.toString(),SALT) ;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.isPasswordValid(s,charSequence.toString(),SALT);
    }
}
