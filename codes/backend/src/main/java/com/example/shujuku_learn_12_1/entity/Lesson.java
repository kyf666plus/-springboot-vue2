package com.example.shujuku_learn_12_1.entity;

public class Lesson {
    private int id;
    private String faculty;
    private String courseName;
    private String classname; // 改变此处
    private int studentsNumber;
    private String termName;
    private String environment;
    private String subject;
    private String teacherName;
    private String courseNature;

    public Lesson(int id, String faculty, String courseName, String classname, int studentsNumber,
                  String termName, String environment, String subject, String teacherName, String courseNature) {
        this.id = id;
        this.faculty = faculty;
        this.courseName = courseName;
        this.classname = classname;
        this.studentsNumber = studentsNumber;
        this.termName = termName;
        this.environment = environment;
        this.subject = subject;
        this.teacherName = teacherName;
        this.courseNature = courseNature;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getClassname() {
        return classname;
    }

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public String getTermName() {
        return termName;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getCourseNature() {
        return courseNature;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setCourseNature(String courseNature) {
        this.courseNature = courseNature;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", faculty='" + faculty + '\'' +
                ", courseName='" + courseName + '\'' +
                ", classname='" + classname + '\'' +
                ", studentsNumber=" + studentsNumber +
                ", termName='" + termName + '\'' +
                ", environment='" + environment + '\'' +
                ", subject='" + subject + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseNature='" + courseNature + '\'' +
                '}';
    }
}
