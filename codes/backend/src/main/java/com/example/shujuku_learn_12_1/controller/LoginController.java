package com.example.shujuku_learn_12_1.controller;


import com.example.shujuku_learn_12_1.entity.User;
import com.example.shujuku_learn_12_1.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        User user = userService.loginUser(username, password);

        if (user != null) {
            boolean isAdmin = user.getAdmin();
            return isAdmin ? "管理员登录成功" : "用户登录成功";
        } else {
            return "登录失败，用户名或密码错误";
        }
    }
}
