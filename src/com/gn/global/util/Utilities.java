/**
 * @author ChaosWong
 * @date 2019/12/27 13:27
 * @title Common Utilities Used in Program
 */
package com.gn.global.util;
import com.gn.global.GlobalConfig;
import com.gn.global.bean.intity.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import java.io.File;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utilities {
	private static String dbHost="cdb-be25f4tj.gz.tencentcdb.com";               // 数据库地址
	private static String dbName="testdb";                      // 数据库名称
	private static String userName = "root";                // 登录名称
	private static String userPassword = "w1549346071";         // 登录密码
	private static String dbPort="10031";                      // 数据库端口号

	public static void intilize() {
		try {
			Connection conn =getMySqlConnection();
			ScriptRunner runner = new ScriptRunner(conn);
			Resources.setCharset(Charset.forName("UTF-8")); //设置字符集,不然中文乱码插入错误
			runner.setLogWriter(null);//设置是否输出日志
			// 从class目录下直接读取
			Reader read = Resources.getResourceAsReader( "testdb.sql" );
			runner.runScript(read);
			runner.closeConnection();
			conn.close();
			System.out.println("sql脚本执行完毕");
		} catch (Exception e) {
			System.out.println("sql脚本执行发生异常");
			e.printStackTrace();
		}
	}

	public static List<Object> filter ( Object obj) throws IllegalAccessException {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria( obj.getClass().getName() );
		Field[] fs = obj.getClass().getDeclaredFields();
		for(Field f:fs) {
			f.setAccessible( true );
			if(f.getModifiers()!=16+8)
				if(f.get( obj )!=null && !Integer.valueOf( 0 ).equals( f.get( obj ) ) &&f.get( obj )!=""&&!Double.valueOf( 0 ).equals( f.get( obj ) ))
					criteria.add( Restrictions.like( f.getName(), f.get( obj ) ) );
		}
		List list =criteria.list();
		session.close();
		return list;
	}
	public static void backup(boolean[] bool) throws IllegalAccessException, IOException {
		if(bool[0]) {
			List listOfCourse = filter( new Course() );
			for ( Object object : listOfCourse ) {
				FileUtilities.writeObj( new File( GlobalConfig.BACK_UP_DIR + "course_" + new Date().getMonth() + '_' + new Date().getDate() + ".properties" ), object );
			}
		}
		if(bool[1]) {
			List listOfStudent = filter( new Student() );
			for ( Object object : listOfStudent ) {
				FileUtilities.writeObj( new File( GlobalConfig.BACK_UP_DIR + "student_" + new Date().getMonth() + '_' + new Date().getDate() + ".properties" ), object );
			}
		}
		if(bool[2]) {
			List listOfGrade = filter( new Grade() );
			for ( Object object : listOfGrade ) {
				FileUtilities.writeObj( new File( GlobalConfig.BACK_UP_DIR + "grade_" + new Date().getMonth() + '_' + new Date().getDate() + ".properties" ), object );
			}
		}
		if(bool[3]) {
			List listOfTeacher = filter( new Teacher() );
			for ( Object object : listOfTeacher ) {
				FileUtilities.writeObj( new File( GlobalConfig.BACK_UP_DIR + "teacher_" + new Date().getMonth() + '_' + new Date().getDate() + ".properties" ), object );
			}
		}
		if(bool[4]) {
			List listOfUser = filter( new User() );
			for ( Object object : listOfUser ) {
				FileUtilities.writeObj( new File( GlobalConfig.BACK_UP_DIR + "user_" + new Date().getMonth() + '_' + new Date().getDate() + ".properties" ), object );
			}
		}
	}
	public static boolean recover( int operatorId,File file ){
		if( operatorId == 0 ) {
			Configuration configuration = new Configuration().configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();

			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			List list = FileUtilities.readObj( file );
			for(Object obj:list) session.saveOrUpdate(obj);
			transaction1.commit();
			//关闭Session
			session.close();
			return true;
		}
		else
			return false;
	}
	public static List<ObservableList<Object>> tableFilter( ObservableList<Object> oldList , StringBuilder columName , StringBuilder columnValue){
		ObservableList<Object> filtedItemFactories = FXCollections.observableArrayList();
		ObservableList<Object> unfiltedItemFactories = FXCollections.observableArrayList();
		List <ObservableList<Object>> list = new ArrayList<>();

		for(Object object:oldList){
			Field field = null;
			try{
				field = object.getClass().getDeclaredField( new String(columName) );
				field.setAccessible( true );
				SimpleStringProperty a= (SimpleStringProperty) field.get( object );
				if( a.getValue().equals( new String(columnValue)) ) {
					unfiltedItemFactories.add( object );
				}else {
					filtedItemFactories.add( object );
				}
			}catch ( Exception e ){}
		}
		list.add( unfiltedItemFactories);
		list.add( filtedItemFactories);
		return list;
	}
	public static Connection getMySqlConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8&port="+dbPort+"&autoReconnect=true";
		return DriverManager.getConnection(url,userName,userPassword);
	}

}