package com.example.shujuku_learn_12_1.mapper;

import com.example.shujuku_learn_12_1.entity.Experiment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExperimentMapper {

    @Select("SELECT * FROM Experiment")
    List<Experiment> selectList();

    @Insert("INSERT INTO Experiment (experimentName, courseName, labUpdate, labType, nature, " +
            "requirement, groupsNumber, labLocation, labTime, labTeacher, week, workday) " +
            "VALUES (#{experimentName}, #{courseName}, #{labUpdate}, #{labType}, #{nature}, " +
            "#{requirement}, #{groupsNumber}, #{labLocation}, #{labTime}, #{labTeacher}, #{week}, #{workday})")
    int insert(Experiment experiment);

    @Update("UPDATE Experiment SET labUpdate = #{labUpdate}, labType = #{labType}, " +
            "nature = #{nature}, requirement = #{requirement}, groupsNumber = #{groupsNumber}, " +
            "labLocation = #{labLocation}, labTime = #{labTime}, labTeacher = #{labTeacher}, " +
            "week = #{week}, workday = #{workday} " +
            "WHERE experimentName = #{experimentName}")
    int updateExperiment(Experiment experiment);

    @Delete("DELETE FROM Experiment WHERE experimentName = #{experimentName}")
    int deleteExperiment(@Param("experimentName") String experimentName);

    // 其他自定义方法
}
