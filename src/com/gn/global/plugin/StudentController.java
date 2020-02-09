package com.gn.global.plugin;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.intity.Student;
import com.gn.global.util.Utilities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * @author ChaosWong
 * @date 2019/12/27 16:32
 * @title Student Controller
 */
public class StudentController {
	public static boolean createStudent (int operatorId,Student student) {
			if ( operatorId == 0 ) {
				Configuration configuration = new Configuration();
				configuration.configure();
				SessionFactory factory = configuration.buildSessionFactory();
				Session session = factory.openSession();
				Transaction transaction1 = session.getTransaction();
				transaction1.begin();
				session.save( student );
				transaction1.commit();
				//关闭Session
				session.close();
				return true;
			}
			else
				return false;

	}
	public static boolean editStudent ( int operatorId, Student student ) {
		if ( operatorId == 0 ) {

			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.update( student );
			transaction1.commit();
			//关闭Session
			session.close();
			return true;
		}
		else
			return false;
	}
	public static boolean deleteStudent ( int operatorId, int deletID ) {
		if ( operatorId == 0 && deletID != 0 ) {
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
			Student s = (Student) session.get( Student.class, deletID );
			session.delete( s );
			//提交事务
			transaction.commit();
			//关闭Session
			session.close();
			return true;
		}
		else
			return false;
	}
}
