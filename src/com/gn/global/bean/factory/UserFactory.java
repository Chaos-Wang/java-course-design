package com.gn.global.bean.factory;

import com.gn.global.bean.intity.User;
import javafx.beans.property.SimpleStringProperty;

public class UserFactory {
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty userName = new SimpleStringProperty("");
    private final SimpleStringProperty password = new SimpleStringProperty("");
    private final SimpleStringProperty grade = new SimpleStringProperty("");
    private final SimpleStringProperty limits = new SimpleStringProperty("");
    private final SimpleStringProperty email = new SimpleStringProperty("");
    private final SimpleStringProperty fullName = new SimpleStringProperty("");
    private final SimpleStringProperty connectedId = new SimpleStringProperty("");

    public UserFactory(User user){
        setId(String.valueOf(user.getId()));
        setUserName(user.getUserName());
        setPassword(user.getPassword());
        setGrade(user.getGrade());
        setLimits(user.getLimits());
        setEmail(user.getEmail());
        setFullName(user.getFullName());
        setConnectedId(String.valueOf(user.getConnectedId()));
    }


    public String getId() {
        return id.get();
    }
    public void setId(String id){
        this.id.set(id);
    }

    public String getUserName() {
        return userName.get();
    }
    public void setUserName(String userName){
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }
    public void setPassword(String password){
        this.password.set(password);
    }

    public String getGrade() {
        return grade.get();
    }
    public void setGrade(String grade){
        this.grade.set(grade);
    }

    public String getLimits() {
        return limits.get();
    }
    public void setLimits(String limits){
        this.limits.set(limits);
    }

    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email){
        this.email.set(email);
    }

    public String getFullName() {
        return fullName.get();
    }
    public void setFullName(String fullName){
        this.fullName.set(fullName);
    }

    public String getConnectedId() {
        return connectedId.get();
    }
    public void setConnectedId(String connectedId){
        this.connectedId.set(connectedId);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public SimpleStringProperty limitsProperty() {
        return limits;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }

    public SimpleStringProperty connectedIdProperty() {
        return connectedId;
    }
}
