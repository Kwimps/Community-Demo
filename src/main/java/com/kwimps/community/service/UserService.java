package com.kwimps.community.service;

import com.kwimps.community.entity.User;

import java.util.Map;

public interface UserService {

    public User findUserById(int id);

    public Map<String,Object> register(User user);

    public int activation(int userId, String code);

}
