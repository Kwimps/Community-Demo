package com.kwimps.community.service.impl;

import com.kwimps.community.dao.UserMapper;
import com.kwimps.community.entity.User;
import com.kwimps.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

}
