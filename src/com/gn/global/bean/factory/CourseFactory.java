package com.gn.global.bean.factory;

import com.gn.global.bean.intity.Course;
import javafx.beans.property.SimpleStringProperty;

public class CourseFactory {
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty time = new SimpleStringProperty("");
    private final SimpleStringProperty teacherId = new SimpleStringProperty("");
    private final SimpleStringProperty credit = new SimpleStringProperty("");

    public CourseFactory(Course course){
        setId(String.valueOf(course.getId()));
        setName(course.getName());
        setTime(course.getTime());
        setTeacherId(String.valueOf(course.getTeacherId()));
        setCredit(String.valueOf(course.getCredit()));
    }
    public String getId() {
        return this.id.get();
    }
    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public String getTime() {
        return time.get();
    }
    public void setTime(String time) {
        this.time.set(time);
    }

    public String getTeacherId() {
        return teacherId.get();
    }
    public void setTeacherId(String teacherId) {
        this.teacherId.set(teacherId);
    }

    public String getCredit() {
        return credit.get();
    }
    public void setCredit(String credit) {
        this.credit.set(credit);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public SimpleStringProperty teacherIdProperty() {
        return teacherId;
    }

    public SimpleStringProperty creditProperty() {
        return credit;
    }
}
