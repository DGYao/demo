package com.springboot.web.service.impl;

import com.springboot.web.mapper.UserMapper;
import com.springboot.web.model.User;
import com.springboot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户service实现
 * Created by Administrator on 2017/5/11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findList() {
        return userMapper.selectAll();
    }
}
