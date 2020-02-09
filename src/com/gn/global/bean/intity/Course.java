package com.gn.global.bean.intity;

import com.gn.global.bean.factory.CourseFactory;
import com.gn.global.bean.factory.StudentFactory;

import java.io.Serializable;

/**
 * @author ChaosWong
 * @date 2019/12/27 17:28
 * @title JavaBean Course
 */
public class Course implements Serializable {
	private int id;
	private String name;
	private String time;
	private int teacherId;
	private int credit;

	public Course () {

	}
	public Course( CourseFactory cf ){
		this.id = Integer.valueOf(cf.idProperty().get());
		this.name = cf.nameProperty().get();
		this.time = cf.timeProperty().get();
		this.teacherId = Integer.valueOf(cf.teacherIdProperty().get());
		this.credit = Integer.valueOf(cf.creditProperty().get());
	}

	public void setCredit ( int credit ) {
		this.credit = credit;
	}
	public void setTime ( String time ) {
		this.time = time;
	}
	public void setName ( String name ) {
		this.name = name;
	}
	public void setId ( int id ) {
		this.id = id;
	}
	public void setTeacherId ( int teacherId ) {
		this.teacherId = teacherId;
	}
	public int getCredit () {
		return credit;
	}
	public String getTime () {
		return time;
	}
	public String getName () {
		return name;
	}
	public int getId () {
		return id;
	}
	public int getTeacherId () {
		return teacherId;
	}

}
