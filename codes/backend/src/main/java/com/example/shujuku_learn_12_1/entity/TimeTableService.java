package com.example.shujuku_learn_12_1.entity;

import com.example.shujuku_learn_12_1.mapper.TimeTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TimeTableService {

    private final TimeTableMapper timeTableMapper;

    @Autowired
    public TimeTableService(TimeTableMapper timeTableMapper) {
        this.timeTableMapper = timeTableMapper;
    }

    public List<Map<String, Object>> getTimeTable(String labName, int week, int workday) {
        return timeTableMapper.getTimeTable(labName, week, workday);
    }
}
