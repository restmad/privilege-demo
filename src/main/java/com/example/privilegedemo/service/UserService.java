package com.example.privilegedemo.service;

import com.example.privilegedemo.data.User;

import java.util.List;

public interface UserService {
    User findUser(String userId);
    List<User> findUserList(String userId);
}
