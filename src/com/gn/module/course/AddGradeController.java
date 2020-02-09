package com.gn.module.course;

import com.gn.global.bean.intity.Grade;
import com.gn.global.util.Alerts;
import com.gn.global.util.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
public class AddGradeController implements Initializable {

	@FXML
	private Button submitButton = new Button();

	@FXML
	private TextField studentIdField = new TextField();
	private int studentId;
	@FXML
	private TextField gradeField = new TextField();
	private int grade;
	@FXML
	private TextField courseIdField = new TextField();
	private int courseId;
	@FXML
	private TextField timeField = new TextField();
	private String time;
	@FXML
	private TextField statusField = new TextField();
	private String status;



	@Override
	public void initialize ( URL location, ResourceBundle resources ) {
		initField();
	}

	private void initField () {
		studentIdField.textProperty().addListener( ( observable, oldValue, newValue ) -> studentId=Integer.valueOf( newValue ) );
		studentIdField.setPromptText( "1" );
		gradeField.textProperty().addListener( ( observable, oldValue, newValue ) ->  grade=Integer.valueOf( newValue ) );
		gradeField.setPromptText( "90" );
		courseIdField.textProperty().addListener( ( observable, oldValue, newValue ) -> courseId=Integer.valueOf( newValue ) );
		courseIdField.setPromptText( "1" );
		timeField.textProperty().addListener( ( observable, oldValue, newValue ) -> time = newValue  );
		timeField.setPromptText( "20181" );
		statusField.textProperty().addListener( ( observable, oldValue, newValue ) -> status = newValue  );
		statusField.setPromptText( "补考通过" );

	}

	@FXML
	private void submit () throws IllegalAccessException {
		try {
			Grade grade = new Grade();
			grade.setStudentId( studentId );
			grade.setCourseId( courseId );
			grade.setTime( time );
			grade = (Grade) Utilities.filter( grade ).get( 0 );
			grade.setStatus( this.status );
			grade.setGrade( this.grade );
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.update( grade );
			transaction1.commit();
			//关闭Session
			session.close();

			Alerts.success( "成功", "修改成功" );
		}catch ( Exception e ){
			Alerts.error( "失败", e.getCause().toString() );
		}
	}

}
