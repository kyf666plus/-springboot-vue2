package com.example.shujuku_learn_12_1.controller;

import com.example.shujuku_learn_12_1.mapper.TimeTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/timetable")
public class TimeTableController {

    private final TimeTableMapper timeTableMapper;

    @Autowired
    public TimeTableController(TimeTableMapper timeTableMapper) {
        this.timeTableMapper = timeTableMapper;
    }

    @GetMapping("/{labName}/{week}/{workday}")
    public List<Map<String, Object>> getTimeTable(@PathVariable String labName, @PathVariable int week, @PathVariable int workday) {
        return timeTableMapper.getTimeTable(labName, week, workday);
    }
}
