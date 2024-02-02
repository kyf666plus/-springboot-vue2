package com.example.shujuku_learn_12_1;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.shujuku_learn_12_1.mapper")
public class ShujukuLearn121Application {

	public static void main(String[] args) {
		SpringApplication.run(ShujukuLearn121Application.class, args);
	}

}
