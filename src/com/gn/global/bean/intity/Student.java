package com.gn.global.bean.intity;
import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.StudentFactory;
import com.gn.module.student.StudentTableController;

import java.io.Serializable;
/**
 * @author ChaosWong
 * @date 2019/12/27 15:24
 * @title JavaBean Student
 */
public class Student implements Serializable {
	private int id;
	private String name;
	public String status;
	private String checkInTime;
	private int level;
	private String subject;
	private String college;
	private String classes;

	public Student(){}
	public Student( StudentFactory sf ){
		this.id = Integer.valueOf(sf.idProperty().get());
		this.name = sf.nameProperty().get();
		this.status = sf.statusProperty().get();
		this.checkInTime = sf.checkInTimeProperty().get();
		this.level =Integer.valueOf( sf.levelProperty().get() );
		this.subject = sf.subjectProperty().get();
		this.classes = sf.classesProperty().get();
	}
	public String getCollege () {
		return college;
	}
	public String getSubject () {
		return subject;
	}
	public String getClasses () {
		return classes;
	}
	public int getId () {
		return id;
	}
	public String getName () {
		return name;
	}
	public String getStatus () {
		return status;
	}
	public String getCheckInTime () {
		return checkInTime;
	}
	public int getLevel () {
		return level;
	}
	public void setLevel ( int level ) {
		if(status=="留级")
			this.level = level-1;
		else
			this.level = level;
	}
	public void setClasses ( String classes ) {
		this.classes = classes;
	}
	public void setId ( int id ) {
		this.id = id;
	}
	public void setName ( String name ) {
		this.name = name;
	}
	public void setCheckInTime ( String checkInTime ) {
		this.checkInTime = checkInTime;
	}
	public void setCollege ( String college ) {
		this.college = college;
	}
	public void setSubject ( String subject ) {
		this.subject = subject;
	}
	public void setStatus ( String status ) {
		if ( status.length() == 1 ) {
			int index = status.toCharArray()[ 0 ] - '0';
			this.status = GlobalConfig.STATUS_CODE_OF_STUDENT[ index ];
			return;
		}
		this.status = status;
	}
}
