package com.example.shujuku_learn_12_1.controller;

import com.example.shujuku_learn_12_1.entity.Laboratory;
import com.example.shujuku_learn_12_1.mapper.LaboratoryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    // 查询所有实验室信息
    @GetMapping
    public List<Laboratory> query() {
        List<Laboratory> list = laboratoryMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    // 添加实验室信息
    @PostMapping
    public String save(@RequestBody Laboratory laboratory) {
        // 执行插入操作
        int i = laboratoryMapper.insert(laboratory);
        if (i > 0) {
            return "插入成功";
        } else {
            return "插入失败";
        }
    }

    // 编辑实验室信息
    @PutMapping("/{classroomId}")
    public String updateLaboratory(@PathVariable int classroomId, @RequestBody Laboratory updatedLaboratory) {
        // 根据 classroomId 查询实验室信息
        Laboratory laboratory = laboratoryMapper.selectById(classroomId);
        if (laboratory == null) {
            return "实验室不存在";
        }

        // 更新实验室信息
        laboratory.setLaboratoryName(updatedLaboratory.getLaboratoryName());
        laboratory.setClassroomId(updatedLaboratory.getClassroomId());
        laboratory.setBrief(updatedLaboratory.getBrief());
        // 其他属性的更新

        // 执行更新操作
        int i = laboratoryMapper.updateById(laboratory);
        if (i > 0) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    // 删除实验室信息
    @DeleteMapping("/{classroomId}")
    public String deleteLaboratory(@PathVariable int classroomId) {
        // 执行删除操作
        int i = laboratoryMapper.deleteById(classroomId);
        if (i > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
