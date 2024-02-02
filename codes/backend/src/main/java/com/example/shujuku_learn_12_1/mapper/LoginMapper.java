package com.example.shujuku_learn_12_1.mapper;

import com.example.shujuku_learn_12_1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface LoginMapper {

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User loginUser(@Param("username") String username, @Param("password") String password);
}
