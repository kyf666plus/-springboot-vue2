package com.example.shujuku_learn_12_1.controller;

import com.example.shujuku_learn_12_1.entity.Term;
import com.example.shujuku_learn_12_1.mapper.TermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/term")
public class TermController {

    @Autowired
    private TermMapper termMapper;

    @GetMapping
    public List<Term> query() {
        return termMapper.selectList();
    }

    @PostMapping
    public String save(@RequestBody Term term) {
        int result = termMapper.insert(term);
        return result > 0 ? "插入成功" : "插入失败";
    }

    @PutMapping("/{termName}")
    public String updateTerm(@PathVariable String termName, @RequestBody Term updatedTerm) {
        int result = termMapper.updateTerm(updatedTerm);
        return result > 0 ? "更新成功" : "更新失败";
    }

    @DeleteMapping("/{termName}")
    public String deleteTerm(@PathVariable String termName) {
        int result = termMapper.deleteTerm(termName);
        return result > 0 ? "删除成功" : "删除失败";
    }
}
