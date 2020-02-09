package com.gn.global.bean.form;

import com.gn.global.bean.factory.GradeReportItemFactory;
import com.gn.global.bean.intity.Course;
import com.gn.global.bean.intity.Grade;
import com.gn.global.bean.intity.Student;
import com.gn.global.util.Utilities;

/**
 * @author ChaosWong
 * @date 2019/12/28 12:06
 * @title Grade Report Item Creator
 */
public class GradeReportItem {
	public String studentName;
	public String courseName;
	public String classes;
	public String subject;
	public String college;
	public int grade;
	public String status;
	public double GPA;
	public double credit;
	public GradeReportItem(){};
	public GradeReportItem( GradeReportItemFactory grf ){
		studentName=grf.studentNameProperty().get();
		classes=grf.classesProperty().get();
		subject=grf.subjectProperty().get();
		college=grf.classesProperty().get();
		grade=Integer.valueOf(grf.gradeProperty().get());
		status=grf.statusProperty().get();
		GPA=Integer.valueOf( grf.GPAProperty().get() );
		credit = Integer.valueOf( grf.creditProperty().get() );
	}
	public GradeReportItem ( Grade grade ) throws IllegalAccessException {
		//由studentId得到Student对象
		Student student = new Student();
		student.setId(grade.getStudentId());
		student = (Student) Utilities.filter( student ).get( 0 );
		Course course = new Course();
		course.setId( grade.getCourseId() );
		course=(Course)Utilities.filter(course).get( 0 );
		this.courseName = course.getName();
		this.studentName = student.getName();
		this.classes = student.getClasses();
		this.subject = student.getSubject();
		this.college = student.getCollege();
		this.grade = grade.getGrade();
		this.status = grade.getStatus();
		this.GPA = grade.getGPA();
		this.credit = course.getCredit();
	}
}
