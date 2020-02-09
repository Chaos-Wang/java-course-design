package com.gn.global.plugin;

import com.gn.global.bean.intity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author ChaosWong
 * @date 2019/12/27 16:22
 * @title User Controller
 */
public class UserController {
	public boolean createUser( int operatorId,User user ){
		if( operatorId == 0 && user.getGrade()!="Root") {
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
			session.save( user );
			//提交事务
			transaction.commit();
			//关闭Session
			session.close();
			return true;
		}
		else
			return false;
	}
	public static boolean deleteUser ( int operatorId, int deletID ){
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
			User u =(User) session.get(User.class, deletID);
			session.delete(u);
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
