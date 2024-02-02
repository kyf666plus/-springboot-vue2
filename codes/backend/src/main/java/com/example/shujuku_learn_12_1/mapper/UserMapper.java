package com.example.shujuku_learn_12_1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.shujuku_learn_12_1.entity.User;
import org.apache.ibatis.annotations.*;



@Mapper
public interface UserMapper extends BaseMapper <User>{
    @Select("SELECT MAX(id) FROM user")
    Integer getMaxId();
}
