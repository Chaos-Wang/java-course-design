package com.gn.global.bean.factory;

import com.gn.global.bean.intity.Teacher;
import javafx.beans.property.SimpleStringProperty;

public class TeacherFactory {
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");

    public TeacherFactory(Teacher teacher){
        setId(String.valueOf(teacher.getId()));
        setName(teacher.getName());
    }

    public String getId() {
        return id.get();
    }
    public void setId(String id){
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name){
        this.name.set(name);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
}
