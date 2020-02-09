package com.gn.global;

import com.gn.global.bean.intity.Student;

/**
 * @author ChaosWong
 * @date 2019/12/28 13:19
 * @title Global Config
 */
public class GlobalConfig {
	public static String RECENT_TERM = "20191";
	public static String RECENT_YEAR = "2019";
	public static String BACK_UP_DIR = "export/backup/";
	public static String EXPORT_DIR = "export/csv/";
	public static String[] STATUS_CODE_OF_STUDENT = {"未知","优秀","合格","退学","留级","试读","毕业"};
	public static String LIMITS_OF_ROOT="111";
	public static String LIMITS_OF_TEACHER="011";
	public static String[] STATUS_CODE_OF_GRADE = {"未考","通过","补考","补考通过","重修通过","重修"};
	public static Student STUDENTDEMO = new StudentDemo().student;
}

class StudentDemo {
	public Student student=new Student();
	StudentDemo(){
		student.setLevel( 1 );
		student.setCheckInTime( GlobalConfig.RECENT_YEAR );
		student.setCollege( "计算机与网络空间安全学院" );
		student.setSubject( "计算机科学与技术" );
		student.setClasses( "2班" );
		student.setStatus( "0" );
	}
}