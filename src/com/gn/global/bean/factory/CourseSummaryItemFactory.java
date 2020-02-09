package com.gn.global.bean.factory;

import com.gn.global.bean.form.CourseSummaryItem;
import javafx.beans.property.SimpleStringProperty;

public class CourseSummaryItemFactory {
    public final SimpleStringProperty courseId = new SimpleStringProperty("");
    public final SimpleStringProperty courseName = new SimpleStringProperty("");
    public final SimpleStringProperty averageGrade = new SimpleStringProperty("");
    public final SimpleStringProperty averageGPA = new SimpleStringProperty("");
    public final SimpleStringProperty passRate = new SimpleStringProperty("");

    public CourseSummaryItemFactory(CourseSummaryItem courseSummaryItem){
        setCourseId(String.valueOf(courseSummaryItem.courseId));
        setCourseName(courseSummaryItem.courseName);
        setAverageGrade(String.valueOf(courseSummaryItem.averageGrade));
        setAverageGPA(String.valueOf(courseSummaryItem.averageGPA));
        setPassRate(String.valueOf(courseSummaryItem.passRate));
    }

    public String getCourseId() {
        return courseId.get();
    }
    public void setCourseId(String courseId){
        this.courseId.set(courseId);
    }

    public String getCourseName() {
        return courseName.get();
    }
    public void setCourseName(String courseName){
        this.courseName.set(courseName);
    }

    public String getAverageGrade() {
        return averageGrade.get();
    }
    public void setAverageGrade(String averageGrade){
        this.averageGrade.set(averageGrade);
    }

    public String getAverageGPA() {
        return averageGPA.get();
    }
    public void setAverageGPA(String averageGPA){
        this.averageGPA.set(averageGPA);
    }

    public String getPassRate() {
        return passRate.get();
    }
    public void setPassRate(String passRate){
        this.passRate.set(passRate);
    }

    public SimpleStringProperty courseIdProperty() {
        return courseId;
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public SimpleStringProperty averageGradeProperty() {
        return averageGrade;
    }

    public SimpleStringProperty averageGPAProperty() {
        return averageGPA;
    }

    public SimpleStringProperty passRateProperty() {
        return passRate;
    }
}
