package com.gn.global.bean.intity;
import com.gn.global.bean.factory.TeacherFactory;

import java.io.Serializable;
/**
 * @author ChaosWong
 * @date 2019/12/27 15:24
 * @title JavaBean Teacher
 */
public class Teacher implements Serializable {
	private int id;
	private String name;

	public Teacher(){}
	public Teacher( TeacherFactory tf ){
		this.id = Integer.valueOf( tf.idProperty().get() );
		this.name = tf.nameProperty().get();
	}
	public int getId () {
		return id;
	}
	public String getName () {
		return name;
	}
	public void setId ( int id ) {
		this.id = id;
	}
	public void setName ( String name ) {
		this.name = name;
	}
}
