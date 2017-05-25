package com.springboot.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by Administrator on 2017/5/17.
 */
public class ShiroToken extends UsernamePasswordToken {
    public ShiroToken(String username, String password) {
        super(username, password);
    }

}
