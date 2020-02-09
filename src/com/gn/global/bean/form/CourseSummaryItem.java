package com.gn.global.bean.form;

import com.gn.global.bean.intity.Course;
import com.gn.global.bean.intity.Grade;
import com.gn.global.util.Utilities;

import java.util.List;

/**
 * @author ChaosWong
 * @date 2019/12/28 12:06
 * @title Course Summary Item Creator
 */
public class CourseSummaryItem {
	public int courseId;
	public String courseName;
	public double averageGrade;
	public double averageGPA;
	public double passRate;

	public CourseSummaryItem ( int courseId ) throws IllegalAccessException {
		//由courseId得到Course对象
		Course course = new Course();
		course.setId( courseId );
		course = (Course)Utilities.filter( course ).get( 0 );
		//由courseId得到Grade对象集合
		Grade grade = new Grade();
		grade.setCourseId( course.getId() );
		int countOfSpec=0;
		List list = Utilities.filter( grade );
		this.courseId = course.getId();
		this.courseName = course.getName();
		double sum=0;
		for(Object obj:list) {
			if ( ((Grade) obj).getStatus().equals( "留级" ) || ((Grade) obj).getStatus().equals( "退学" ) )
				countOfSpec++;
			else
				sum = sum + ((Grade) obj).getGrade();
		}
		this.averageGrade = sum /(list.size()-countOfSpec);

		sum=0;countOfSpec=0;
		int countOfPassed=0;
		for(Object obj:list) {
			sum = sum + ((Grade) obj).getGPA();
			if(((Grade) obj).getGPA()>=1&&((Grade) obj).getStatus().equals( "通过" ))
				countOfPassed++;
			if(((Grade) obj).getStatus().equals( "留级" ) ||((Grade) obj).getStatus().equals( "退学" ))
				countOfSpec++;
		}
		this.averageGPA = sum/(list.size()-countOfSpec);
		this.passRate = ((double)countOfPassed)/list.size();
	}
}
