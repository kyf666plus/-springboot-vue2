package com.example.shujuku_learn_12_1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shujuku_learn_12_1.entity.Laboratory;
import org.apache.ibatis.annotations.Select;


public interface LaboratoryMapper extends BaseMapper<Laboratory> {
    @Select("SELECT MAX(id) FROM user")
    Integer getMaxId();
}
