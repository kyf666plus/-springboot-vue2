package com.example.shujuku_learn_12_1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.shujuku_learn_12_1.entity.Lesson;

import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface LessonMapper extends BaseMapper<Lesson> {

    @Select("SELECT * FROM lesson")
    List<Lesson> selectList();
    @Select("SELECT MAX(id) FROM lesson")
    Integer getMaxId();
}
