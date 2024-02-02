
package com.example.shujuku_learn_12_1.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("Laboratory")
public class Laboratory {

    @TableId("classroom_id")
    private Integer id;

    @TableField("laboratory_name")
    private String laboratoryName;

    @TableField("brief")
    private String brief;

    public Laboratory(int id, String laboratoryName, String brief) {
        this.id = id;
        this.laboratoryName = laboratoryName;
        this.brief = brief;
    }


    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public int getClassroomId() {
        return id;
    }

    public void setClassroomId(int classroomId) {
        this.id = classroomId;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "laboratoryName='" + laboratoryName + '\'' +
                ", classroomId=" + id +
                ", brief='" + brief + '\'' +
                '}';
    }
}

