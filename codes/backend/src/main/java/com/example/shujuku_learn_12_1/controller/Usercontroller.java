package com.example.shujuku_learn_12_1.controller;

import com.example.shujuku_learn_12_1.entity.User;
import com.example.shujuku_learn_12_1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class Usercontroller {

    @Autowired
    private UserMapper userMapper;

    // 查询所有用户
    @GetMapping
    public List<User> query() {
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    // 添加用户
    @PostMapping
    public String save(@RequestBody User user) {
        // 查询数据库中最大的工号
        Integer maxId = userMapper.getMaxId();

        // 如果数据库中没有用户，maxId为null，将其设置为1
        int nextId = (maxId != null) ? maxId + 1 : 1;

        // 设置新用户的工号
        user.setId(nextId);

        // 执行插入操作
        int i = userMapper.insert(user);
        if (i > 0) {
            return "插入成功";
        } else {
            return "插入失败";
        }
    }

    // 编辑用户
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        // 根据 id 查询用户信息
        User user = userMapper.selectById(id);
        if (user == null) {
            return "用户不存在";
        }

        // 更新用户信息
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setIdCard(updatedUser.getIdCard());
        user.setAdmin(updatedUser.getAdmin()); // 设置管理员状态

        // 执行更新操作
        int i = userMapper.updateById(user);
        if (i > 0) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    // 删除用户
    @DeleteMapping("/{id}") // 注意修改路径变量名
    public String deleteUser(@PathVariable int id) {
        // 执行删除操作
        int i = userMapper.deleteById(id);
        if (i > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
