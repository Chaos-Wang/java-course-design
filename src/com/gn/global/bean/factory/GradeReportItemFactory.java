package com.gn.global.bean.factory;

import com.gn.global.bean.form.GradeReportItem;
import javafx.beans.property.SimpleStringProperty;

public class GradeReportItemFactory {
    public final SimpleStringProperty studentName = new SimpleStringProperty("");
    public final SimpleStringProperty courseName = new SimpleStringProperty("");
    public final SimpleStringProperty classes = new SimpleStringProperty("");
    public final SimpleStringProperty subject = new SimpleStringProperty("");
    public final SimpleStringProperty college = new SimpleStringProperty("");
    public final SimpleStringProperty grade = new SimpleStringProperty("");
    public final SimpleStringProperty status = new SimpleStringProperty("");
    public final SimpleStringProperty GPA = new SimpleStringProperty("");
    public final SimpleStringProperty credit = new SimpleStringProperty("");

    public GradeReportItemFactory(GradeReportItem gradeReportItem){
        setStudentName(gradeReportItem.studentName);
        setCourseName(gradeReportItem.courseName);
        setClasses(gradeReportItem.classes);
        setSubject(gradeReportItem.subject);
        setCollege(gradeReportItem.college);
        setGrade(String.valueOf(gradeReportItem.grade));
        setStatus(gradeReportItem.status);
        setGPA(String.valueOf(gradeReportItem.GPA));
        setCredit(String.valueOf(gradeReportItem.credit));
    }


    public String getCourseName () {
        return courseName.get();
    }
    public void setCourseName(String courseName){this.courseName.set(courseName);}

    public String getStudentName() {
        return studentName.get();
    }
    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public String getClasses() {
        return classes.get();
    }
    public void setClasses(String classes){
        this.classes.set(classes);
    }

    public String getSubject() {
        return subject.get();
    }
    public void setSubject(String subject){
        this.subject.set(subject);
    }

    public String getCollege() {
        return college.get();
    }
    public void setCollege(String college){
        this.college.set(college);
    }

    public String getGrade() {
        return grade.get();
    }
    public void setGrade(String grade){
        this.grade.set(grade);
    }

    public String getStatus() {
        return status.get();
    }
    public void setStatus(String status){
        this.status.set(status);
    }

    public String getGPA() {
        return GPA.get();
    }
    public void setGPA(String gpa){
        this.GPA.set(gpa);
    }

    public String getCredit() {
        return credit.get();
    }
    public void setCredit(String credit){
        this.credit.set(credit);
    }

    public SimpleStringProperty courseNameProperty(){return courseName; }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public SimpleStringProperty classesProperty() {
        return classes;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleStringProperty collegeProperty() {
        return college;
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public SimpleStringProperty GPAProperty() {
        return GPA;
    }

    public SimpleStringProperty creditProperty() {
        return credit;
    }
}
