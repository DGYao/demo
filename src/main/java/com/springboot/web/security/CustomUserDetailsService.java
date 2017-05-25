package com.springboot.web.security;

import com.springboot.web.mapper.UUserMapper;
import com.springboot.web.model.UUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2017/5/20.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UUserMapper uUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UUser user = uUserMapper.findByName(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("city"));
            UserPrincipal userDetails = new UserPrincipal(username, user.getPswd(), authorities);
            return userDetails;
        }
    }
}
