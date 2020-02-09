package com.gn.global.bean.factory;

import javafx.beans.property.SimpleStringProperty;
import com.gn.global.bean.intity.Grade;


public class GradeFactory {
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty courseId = new SimpleStringProperty("");
    private final SimpleStringProperty studentId = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final SimpleStringProperty grade = new SimpleStringProperty("");
    private final SimpleStringProperty time = new SimpleStringProperty("");
    private final SimpleStringProperty GPA = new SimpleStringProperty("");

    public GradeFactory(Grade grade){
        setId(String.valueOf(grade.getId()));
        setCourseId(String.valueOf(grade.getCourseId()));
        setStudentId(String.valueOf(grade.getStudentId()));
        setStatus(grade.getStatus());
        setGrade(String.valueOf(grade.getGrade()));
        setTime(String.valueOf(grade.getTime()));
        setGPA(String.valueOf(grade.getGPA()));
    }


    public String getId() {
        return id.get();
    }
    public void setId(String id){
        this.id.set(id);
    }

    public String getCourseId() {
        return courseId.get();
    }
    public void setCourseId(String courseId){
        this.courseId.set(courseId);
    }

    public String getStudentId() {
        return studentId.get();
    }
    public void setStudentId(String studentId){
        this.studentId.set(studentId);
    }

    public String getStatus() {
        return status.get();
    }
    public void setStatus(String status){
        this.status.set(status);
    }

    public String getGrade() {
        return grade.get();
    }
    public void setGrade(String grade){
        this.grade.set(grade);
    }

    public String getTime() {
        return time.get();
    }
    public void setTime(String time){
        this.time.set(time);
    }

    public String getGPA() {
        return GPA.get();
    }
    public void setGPA(String gpa){
        this.GPA.set(gpa);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty courseIdProperty() {
        return courseId;
    }

    public SimpleStringProperty studentIdProperty() {
        return studentId;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public SimpleStringProperty GPAProperty() {
        return GPA;
    }
}
