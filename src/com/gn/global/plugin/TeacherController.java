package com.gn.global.plugin;

import com.gn.global.bean.intity.User;
import com.gn.global.bean.intity.Teacher;
import com.gn.global.util.Utilities;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * @author ChaosWong
 * @date 2019/12/27 16:32
 * @title Teacher Controller
 */
public class TeacherController {
	public static boolean createTeacher( int operatorId, Teacher teacher ){
		if( operatorId == 0 ) {

			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.save( teacher );
			transaction1.commit();

			Criteria criteria = session.createCriteria(Teacher.class);
			criteria.add( Restrictions.eq("name",teacher.getName() ));
			User u = new User();

			Teacher s = (Teacher)(criteria.list().get( 0 ));
			u.setConnectedId( s.getId() );
			u.setUserName( teacher.getName() );
			u.setPassword( teacher.getName() );
			u.setGrade( "Teacher" );
			u.setFullName( "Teacher" + s.getId() );
			u.setEmail( "teacher" + s.getId() + "@hainanu.edu.cn" );

			Transaction transaction2 = session.getTransaction();
			transaction2.begin();
			session.save( u );
			transaction2.commit();

			//关闭Session
			session.close();
			return true;
		}
		else
			return false;
	}
	public static boolean deleteTeacher ( int operatorId, int deletID ) throws IllegalAccessException {
		if ( operatorId == 0 && deletID != 0 ) {
			Configuration configuration = new Configuration();
			//不给参数就默认加载hibernate.cfg.xml文件，
			configuration.configure();
			//创建Session工厂对象
			SessionFactory factory = configuration.buildSessionFactory();
			//得到Session对象
			Session session = factory.openSession();
			//使用Hibernate操作数据库，都要开启事务,得到事务对象
			Transaction transaction1 = session.getTransaction();
			Transaction transaction2 = session.getTransaction();
			User user=new User();
			user.setConnectedId( deletID );
			transaction1.begin();
			transaction2.begin();

			user = (User)Utilities.filter( user ).get( 0 );
			user = (User)session.get( User.class,user.getId() );

			Teacher s = (Teacher) session.get( Teacher.class, deletID );
			session.delete( user );
			session.delete( s );
			//提交事务
			transaction2.commit();
			transaction1.commit();
			//关闭Session
			session.close();
			return true;
		}
		else
			return false;
	}

}
