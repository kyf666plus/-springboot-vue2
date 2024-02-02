package com.example.shujuku_learn_12_1.entity;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Term {
    private String termname;
    private Date date;
    private String week;
    private int courseload;  // 修改为 courseload


    public Term(String termname, Date date, String week, int courseload) {
        this.termname = termname;
        this.date = date;
        this.week = week;
        this.courseload = courseload;

    }


    @Override
    public String toString() {
        return "Term{" +
                "termname='" + termname + '\'' +
                ", date=" + date +
                ", week=" + week +
                ", courseload=" + courseload +
                '}';
    }

    public String getTermname() {
        return termname;
    }

    public void setTermname(String termname) {
        this.termname = termname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getCourseload() {
        return courseload;
    }

    public void setCourseload(int courseload) {
        this.courseload = courseload;
    }

}