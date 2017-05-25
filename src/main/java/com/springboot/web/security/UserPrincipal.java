package com.springboot.web.security;

import com.springboot.common.Constant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Administrator on 2017/5/23.
 */
public class UserPrincipal extends User {
    private String salt = Constant.SALT;//加密盐值

    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getSalt() {
        return salt;
    }
}
