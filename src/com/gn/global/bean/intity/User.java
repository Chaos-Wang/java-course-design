/**
 * @author ChaosWong
 * @date 2019/12/27 14:06
 * @title JavaBean User
 */
package com.gn.global.bean.intity;
import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.UserFactory;

import java.io.Serializable;
public class User implements Serializable{
	private int id;
	private String userName;
	private String password;
    private String grade;
	private String limits;
	private String email;
	private String fullName;
	private int connectedId;

	public User(){ };
	public User( UserFactory uf ){
		this.id = Integer.valueOf( uf.idProperty().get() );
		this.userName = uf.userNameProperty().get();
		this.password = uf.passwordProperty().get();
		this.grade = uf.gradeProperty().get();
		this.limits = uf.limitsProperty().get();
		this.email = uf.emailProperty().get();
		this.fullName = uf.fullNameProperty().get();
		this.connectedId = Integer.valueOf( uf.connectedIdProperty().get() );

	}
	public String getEmail () {
		return email;
	}
	public String getFullName () {
		return fullName;
	}
	public int getConnectedId () {
		return connectedId;
	}
	public String getGrade () { return grade; }
	public int getId () {
		return id;
	}
	public String getPassword () {
		return password;
	}
	public String getUserName () {
		return userName;
	}
	public String getLimits () {
		return limits;
	}
	public void setEmail ( String email ) {
		this.email = email;
	}
	public void setFullName ( String fullName ) {
		this.fullName = fullName;
	}
	public void setLimits ( String limit ) {
		this.limits=limits;
	}
	public void setConnectedId ( int connectedId ) {
		this.connectedId = connectedId;
	}
	public void setGrade ( String grade ) {
		this.grade = grade;
		switch(grade) {
			case "Root":limits = GlobalConfig.LIMITS_OF_ROOT;break;
			case "Teacher":limits = GlobalConfig.LIMITS_OF_TEACHER;break;
		}
	}
	public void setId ( int id ) {
		this.id = id;
	}
	public void setPassword ( String password ) {
		this.password = password;
	}
	public void setUserName ( String userName ) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "User[userName = " +userName+ ", " +
				"fullName = " +fullName +", " +
				"email = " +email+ ", password = " + password +"]";
	}
}
