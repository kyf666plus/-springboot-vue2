package com.example.shujuku_learn_12_1.entity;



public class Experiment {
    private String experimentName;
    private String courseName;
    private String labUpdate;
    private String labType;
    private String nature;
    private String requirement;
    private int groupsNumber;
    private String labLocation;
    private String labTime;
    private String labTeacher;

    // New Columns
    private int week;
    private int workday;

    public Experiment(String experimentName, String courseName, String labUpdate, String labType,
                      String nature, String requirement, int groupsNumber, String labLocation,
                      String labTime, String labTeacher, int week, int workday) {
        this.experimentName = experimentName;
        this.courseName = courseName;
        this.labUpdate = labUpdate;
        this.labType = labType;
        this.nature = nature;
        this.requirement = requirement;
        this.groupsNumber = groupsNumber;
        this.labLocation = labLocation;
        this.labTime = labTime;
        this.labTeacher = labTeacher;
        this.week = week;
        this.workday = workday;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLabUpdate() {
        return labUpdate;
    }

    public void setLabUpdate(String labUpdate) {
        this.labUpdate = labUpdate;
    }

    public String getLabType() {
        return labType;
    }

    public void setLabType(String labType) {
        this.labType = labType;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public int getGroupsNumber() {
        return groupsNumber;
    }

    public void setGroupsNumber(int groupsNumber) {
        this.groupsNumber = groupsNumber;
    }

    public String getLabLocation() {
        return labLocation;
    }

    public void setLabLocation(String labLocation) {
        this.labLocation = labLocation;
    }

    public String getLabTime() {
        return labTime;
    }

    public void setLabTime(String labTime) {
        this.labTime = labTime;
    }

    public String getLabTeacher() {
        return labTeacher;
    }

    public void setLabTeacher(String labTeacher) {
        this.labTeacher = labTeacher;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWorkday() {
        return workday;
    }

    public void setWorkday(int workday) {
        this.workday = workday;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "experimentName='" + experimentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", labUpdate='" + labUpdate + '\'' +
                ", labType='" + labType + '\'' +
                ", nature='" + nature + '\'' +
                ", requirement='" + requirement + '\'' +
                ", groupsNumber=" + groupsNumber +
                ", labLocation='" + labLocation + '\'' +
                ", labTime='" + labTime + '\'' +
                ", labTeacher='" + labTeacher + '\'' +
                ", week=" + week +
                ", workday=" + workday +
                '}';
    }
}