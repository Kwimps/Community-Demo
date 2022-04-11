package com.kwimps.community.service;

import com.kwimps.community.entity.LoginTicket;
import com.kwimps.community.entity.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface UserService {

    public User findUserById(int id);

    public Map<String,Object> register(User user);

    public int activation(int userId, String code);

    public Map<String, Object> login(String username, String password, long expiredSeconds);

    public void logout(String ticket);

    public LoginTicket findLoginTicket(String ticket);

    public int updateHeader(int userId,String headUrl);

    public Map<String,Object> updatePassword(int userId,String newPassword,String oldPassword);

    public User findUserByName(String username);

}
