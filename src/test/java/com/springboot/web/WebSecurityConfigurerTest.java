package com.springboot.web;

import com.springboot.DemoApplicationTests;
import com.springboot.common.Constant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/23.
 */
public class WebSecurityConfigurerTest extends DemoApplicationTests{
    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;
    @Test
    public void passwordEncoder() throws Exception {
        Random RANDOM = new SecureRandom();
        byte[] bytes = new byte[16];
        RANDOM.nextBytes(bytes);
        String salt = new BASE64Encoder().encode(bytes);
        System.out.println("salt:"+salt);
        System.out.println("password1:"+md5PasswordEncoder.encodePassword("123456", Constant.SALT));
    }

}