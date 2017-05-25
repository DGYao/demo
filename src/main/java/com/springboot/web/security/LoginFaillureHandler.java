package com.springboot.web.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/23.
 */
@Component
public class LoginFaillureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //密码错误异常
        if (exception instanceof BadCredentialsException) {
            //TODO 记录登录错误次数，锁定
            System.out.println("BadCredentialsException异常");
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
