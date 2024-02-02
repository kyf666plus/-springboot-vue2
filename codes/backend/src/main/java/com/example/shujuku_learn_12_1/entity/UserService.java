package com.example.shujuku_learn_12_1.entity;

import com.example.shujuku_learn_12_1.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// UserService.java
@Service
public class UserService {

    @Autowired
    private LoginMapper loginMapper;

    public User loginUser(String username, String password) {
        return loginMapper.loginUser(username, password);
    }
}
