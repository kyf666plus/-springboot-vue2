package com.example.shujuku_learn_12_1.mapper;

import com.example.shujuku_learn_12_1.entity.Term;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TermMapper {

    @Select("SELECT * FROM Term")
    List<Term> selectList();


    @Insert("INSERT INTO Term (termname, date, week, courseload) " +
            "VALUES (#{termname}, #{date}, #{week}, #{courseload})")
    int insert(Term term);


    @Update("UPDATE Term SET date = #{date}, week = #{week}, courseload = #{courseload} " +
            "WHERE termname = #{termname}")
    int updateTerm(Term term);

    @Delete("DELETE FROM Term WHERE termname = #{termname}")
    int deleteTerm(@Param("termname") String termName);


}
