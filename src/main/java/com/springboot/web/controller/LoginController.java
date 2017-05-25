package com.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/22.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
        model.addAttribute("error",request.getParameter("error"));
        model.addAttribute("logout",request.getParameter("logout"));
        model.addAttribute("expired",request.getParameter("expired"));
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
