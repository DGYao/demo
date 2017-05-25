package com.springboot.web.service;

import com.springboot.web.model.User;

import java.util.List;

/**
 * 用户service
 * Created by ez on 2017/5/11.
 */
public interface UserService {
    List<User> findList();
}
