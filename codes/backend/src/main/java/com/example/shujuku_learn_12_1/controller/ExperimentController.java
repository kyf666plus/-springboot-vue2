package com.example.shujuku_learn_12_1.controller;

import com.example.shujuku_learn_12_1.entity.Experiment;
import com.example.shujuku_learn_12_1.mapper.ExperimentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/experiment")
public class ExperimentController {

    @Autowired
    private ExperimentMapper experimentMapper;

    @GetMapping
    public List<Experiment> query() {
        return experimentMapper.selectList();
    }

    @PostMapping
    public String save(@RequestBody Experiment experiment) {
        int result = experimentMapper.insert(experiment);
        return result > 0 ? "插入成功" : "插入失败";
    }

    @PutMapping("/{experimentName}")
    public String updateExperiment(@PathVariable String experimentName, @RequestBody Experiment updatedExperiment) {
        int result = experimentMapper.updateExperiment(updatedExperiment);
        return result > 0 ? "更新成功" : "更新失败";
    }

    @DeleteMapping("/{experimentName}")
    public String deleteExperiment(@PathVariable String experimentName) {
        int result = experimentMapper.deleteExperiment(experimentName);
        return result > 0 ? "删除成功" : "删除失败";
    }
}
