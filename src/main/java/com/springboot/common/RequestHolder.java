package com.springboot.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 监听器中获取HttpServletRequest和HttpServletResponse
 * Created by Administrator on 2017/3/4.
 */
public class RequestHolder {

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse(){
        return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
