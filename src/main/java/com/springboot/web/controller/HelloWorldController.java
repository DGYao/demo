package com.springboot.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ez on 2017/5/11.
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/")
    public String sayHello(){
        return "Hello World";
    }
}
