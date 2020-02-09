package com.gn.module.course;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.intity.Course;
import com.gn.global.bean.intity.Student;
import com.gn.global.plugin.StudentController;
import com.gn.global.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * @date 2020/1/1 11:25
 * @title
 */
public class CreateCourseController implements Initializable {

    @FXML private Button createButton = new Button();

	@FXML private TextField nameField =new TextField();
	@FXML private TextField teacherIdField =new TextField();
	@FXML private TextField timeField =new TextField();
	@FXML private TextField creditField =new TextField();
	private Course course = new Course();

	@Override
	public void initialize( URL location, ResourceBundle resources) {
			initField();
	}


	private void initField(){
		nameField.textProperty().addListener( ( observable, oldValue, newValue ) -> course.setName( newValue ) );
		nameField.setPromptText( "软件工程" );
		teacherIdField.textProperty().addListener( ( observable, oldValue, newValue ) -> course.setTeacherId( Integer.valueOf( newValue ) ) );
		teacherIdField.setPromptText( "1" );
		timeField.textProperty().addListener( ( observable, oldValue, newValue ) -> course.setTime( newValue ) );
		timeField.setPromptText( "20191" );
		creditField.textProperty().addListener( ( observable, oldValue, newValue ) -> course.setCredit( Integer.valueOf( newValue ) ) );
		creditField.setPromptText( "3" );
	}
	@FXML
	private void createStudent() {
		try {

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
			session.save( course );
			//提交事务
			transaction.commit();
			//关闭Session
			session.close();

			Alerts.success( "成功", "创建成功" );

		} catch ( Exception e ) {
			Alerts.error( "失败", e.getCause().toString() );
		}
	}

}
