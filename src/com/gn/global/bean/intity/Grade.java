package com.gn.global.bean.intity;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.GradeFactory;

import java.io.Serializable;

/**
 * @author ChaosWong
 * @date 2019/12/27 17:30
 * @title JavaBean Grade
 */
public class Grade implements Serializable,Comparable<Grade> {
	private int id;
	private int courseId;
	private int studentId;
	private String status;
	private int grade;
	private String time;
	private double GPA;

	public Grade () {

	}
	public Grade( GradeFactory gf ) {
		this.id = Integer.valueOf(gf.idProperty().get());
		this.courseId = Integer.valueOf(gf.courseIdProperty().get());
		this.studentId = Integer.valueOf(gf.studentIdProperty().get());
		this.grade = Integer.valueOf(gf.gradeProperty().get());
		this.GPA = Integer.valueOf(gf.GPAProperty().get());

		this.status = gf.statusProperty().get();
		this.time = gf.timeProperty().get();
	}

	public void setCourseId ( int courseId ) {
		this.courseId = courseId;
	}
	public void setGPA ( double GPA ) {
		switch ( this.status ){
			case "未考": this.GPA=0;break;
			case "通过": this.GPA=(this.grade>=90)?4.0:(1.0+(this.grade-60)*0.1);break;
			case "补考未通过":this.GPA=0;break;
			case "补考通过":this.GPA=1.0;break;
			case "重修通过":this.GPA=1.0;break;
			case "重修未通过": this.GPA = 0; break;
		}
	}
	public void setStatus ( String status ) {
		if(status.length()==1) {
			int index = status.toCharArray()[ 0 ] - '0';
			this.status = GlobalConfig.STATUS_CODE_OF_GRADE[ index ];
			return;
		}
		this.status=status;
	}
	public void setTime ( String time ) {
		this.time = time;
	}
	public void setGrade ( int grade ) {
		this.grade = grade;
		if(grade>=60&&this.status=="补考")
			this.setStatus( "3" );
		else if(grade>=60&&this.status=="重修")
			this.setStatus( "4" );
		else if(grade>=60&&this.status=="未考")
			this.setStatus( "1" );
		else if(grade<60&&this.status=="补考")
			this.setStatus( "5" );
		else if(grade<60&&this.status=="重修")
			this.setStatus( "5" );
		else if(grade<60&&this.status=="未考")
			this.setStatus( "2" );
		this.setGPA( 0 );
	}
	public void setStudentId ( int studentId ) {
		this.studentId = studentId;
	}
	public void setId ( int id ) {
		this.id = id;
	}
	public int getCourseId () {
		return courseId;
	}
	public double getGPA () {
		return GPA;
	}
	public String getTime () {
		return time;
	}
	public String getStatus () {
		return status;
	}
	public int getGrade () {
		return grade;
	}
	public int getStudentId () {
		return studentId;
	}
	public int getId () {
		return id;
	}

	@Override
	public int compareTo ( Grade o ) {
		if(this.getGPA()<o.getGPA())
			return 1;
		else if(this.getGPA()==o.getGPA())
			return 0;
		else
			return -1;
	}
}
