package com.gn.global.plugin;

import com.gn.global.bean.intity.Course;
import com.gn.global.bean.intity.Grade;
import com.gn.global.bean.intity.Student;
import com.gn.global.util.Utilities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * @author ChaosWong
 * @date 2019/12/27 17:40
 * @title GradeController and Choose Course Model
 */
public class GradeController {
	public static boolean deleteGrade( int operatorId, int deletID){
		if(operatorId==0&&deletID!=0) {
			Configuration configuration = new Configuration();
			//不给参数就默认加载hibernate.cfg.xml文件，
			configuration.configure();
			//创建Session工厂对象
			SessionFactory factory = configuration.buildSessionFactory();
			//得到Session对象
			Session session = factory.openSession();
			//使用Hibernate操作数据库，都要开启事务,得到事务对象
			Transaction transaction = session.getTransaction();
			//开启事务
			transaction.begin();
			//把对象添加到数据库中
			Grade s =(Grade) session.get(Grade.class, deletID);
			session.delete(s);
			//提交事务
			transaction.commit();
			//关闭Session
			session.close();
			return true;
		}
		else
			return false;
	}
	public static boolean chooseCourse(int courseId,int studentId) throws IllegalAccessException {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction1 = session.getTransaction();
		transaction1.begin();
		Course course = new Course();
		course.setId( courseId );
		course = (Course)Utilities.filter( course ).get( 0 );
		Grade grade = new Grade();
		grade.setCourseId( courseId );
		grade.setStudentId( studentId );
		grade.setTime( course.getTime());
		grade.setStatus( "0" );
		session.save( grade );
		transaction1.commit();
		//关闭Session
		session.close();
		return true;
	}
	public static boolean chooseCourseByClasses(int courseId, String subject, String classes) throws IllegalAccessException {
		Student student = new Student();
		student.setClasses( classes );
		student.setSubject( subject );
		List list = Utilities.filter( student );

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		for(Object object:list) {
			int studentId = ((Student)object).getId();
			chooseCourse( courseId,studentId );
		}
		return true;
	}

}
