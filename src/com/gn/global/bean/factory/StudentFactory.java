/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.global.bean.factory;

import com.gn.global.bean.intity.Student;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author ChaosWong
 * Create on  29/12/2019
 * Version 1.0
 */
public class StudentFactory {
    private Student student =new Student();
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final SimpleStringProperty checkInTime = new SimpleStringProperty("");
    private final SimpleStringProperty level = new SimpleStringProperty("");
    private final SimpleStringProperty subject = new SimpleStringProperty("");
    private final SimpleStringProperty college = new SimpleStringProperty("");
    private final SimpleStringProperty classes = new SimpleStringProperty("");

    public StudentFactory ( Student student ) {
        setId( String.valueOf( student.getId() ) );
        setName( student.getName() );
        setStatus( student.getStatus() );
        setCheckInTime( student.getCheckInTime() );
        setLevel( String.valueOf( student.getLevel() ) );
        setSubject( student.getSubject() );
        setCollege( student.getCollege() );
        setClasses( student.getClasses() );
    }

    public String getId() {
        return id.get();
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
    public String getStatus(){ return status.get(); }
    public void setStatus(String status){ this.status.set( status ); }
    public String getCheckInTime() {
        return checkInTime.get();
    }
    public void setCheckInTime(String checkInTime) {
        this.checkInTime.set(checkInTime);
    }
    public String getLevel() {
        return level.get();
    }
    public void setLevel(String level) {
        this.level.set(level);
    }
    public String getSubject() {
        return subject.get();
    }
    public void setSubject(String subject) {
        this.subject.set(subject);
    }
    public String getCollege() {
        return college.get();
    }
    public void setCollege(String college) {
        this.college.set(college);
    }
    public String getClasses() {
        return classes.get();
    }
    public void setClasses(String classes) {
        this.classes.set(classes);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
    public SimpleStringProperty statusProperty() {
        return status;
    }
    public SimpleStringProperty checkInTimeProperty() {
        return checkInTime;
    }
    public SimpleStringProperty levelProperty() {
        return level;
    }
    public SimpleStringProperty subjectProperty() {
        return subject;
    }
    public SimpleStringProperty collegeProperty() {
        return college;
    }
    public SimpleStringProperty classesProperty() {
        return classes;
    }

}
