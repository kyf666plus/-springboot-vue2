package com.example.shujuku_learn_12_1.controller;

import com.example.shujuku_learn_12_1.entity.Lesson;
import com.example.shujuku_learn_12_1.mapper.LessonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonMapper lessonMapper;

    // 查询所有课程
    @GetMapping
    public List<Lesson> query() {
        List<Lesson> list = lessonMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    // 添加课程
    @PostMapping
    public String save(@RequestBody Lesson lesson) {
        // 查询数据库中最大的课程编号
        Integer maxId = lessonMapper.getMaxId();

        // 如果数据库中没有课程，maxId为null，将其设置为1
        int nextId = (maxId != null) ? maxId + 1 : 1;

        // 设置新课程的编号
        lesson.setId(nextId);

        // 执行插入操作
        int i = lessonMapper.insert(lesson);
        if (i > 0) {
            return "插入成功";
        } else {
            return "插入失败";
        }
    }

    // 编辑课程
    @PutMapping("/{id}")
    public String updateLesson(@PathVariable int id, @RequestBody Lesson updatedLesson) {
        // 根据 id 查询课程信息
        Lesson lesson = lessonMapper.selectById(id);
        if (lesson == null) {
            return "课程不存在";
        }

        // 更新课程信息
        lesson.setFaculty(updatedLesson.getFaculty());
        lesson.setCourseName(updatedLesson.getCourseName());
        lesson.setClassname(updatedLesson.getClassname());
        lesson.setStudentsNumber(updatedLesson.getStudentsNumber());
        lesson.setTermName(updatedLesson.getTermName());
        lesson.setEnvironment(updatedLesson.getEnvironment());
        lesson.setSubject(updatedLesson.getSubject());
        lesson.setTeacherName(updatedLesson.getTeacherName());
        lesson.setCourseNature(updatedLesson.getCourseNature());

        // 执行更新操作
        int i = lessonMapper.updateById(lesson);
        if (i > 0) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    // 删除课程
    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable int id) {
        // 执行删除操作
        int i = lessonMapper.deleteById(id);
        if (i > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
