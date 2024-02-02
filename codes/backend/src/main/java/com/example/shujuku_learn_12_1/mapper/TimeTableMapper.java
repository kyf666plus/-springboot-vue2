package com.example.shujuku_learn_12_1.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TimeTableMapper {

    @Select("SELECT laboratory.laboratory_name, laboratory.brief, experiment.week, experiment.workday, experiment.courseName " +
            "FROM laboratory " +
            "LEFT JOIN experiment ON laboratory.laboratory_name = experiment.labLocation " +
            "WHERE laboratory.laboratory_name = #{labName} AND experiment.week = #{week} AND experiment.workday = #{workday}")
    List<Map<String, Object>> getTimeTable(@Param("labName") String labName, @Param("week") int week, @Param("workday") int workday);
}
