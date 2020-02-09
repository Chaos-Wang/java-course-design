package com.gn.module.course;

import com.gn.global.bean.intity.Grade;
import com.gn.global.plugin.GradeController;
import com.gn.global.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * @date 2020/1/1 11:25
 * @title
 */
public class ChooseCourseController implements Initializable {
	@FXML private ToggleButton tb1 = new ToggleButton();
	@FXML private ToggleButton tb2 = new ToggleButton();
	@FXML private Button chooseButton = new Button();

	@FXML private TextField studentIdField =new TextField();
	@FXML private TextField courseIdField =new TextField();
	@FXML private TextField subjectField =new TextField();
	@FXML private TextField classesField =new TextField();
	private int studentId;
	private int courseId;
	private String subject;
	private String classes;
	private boolean byClasses=true;

	private Grade grade = new Grade();
	private final ToggleGroup group = new ToggleGroup();


	@Override
	public void initialize( URL location, ResourceBundle resources) {
		initToggleButton();
		initField();
	}
	private void initToggleButton(){
		tb1.setToggleGroup(group);
		tb1.setSelected(true);
		tb2.setToggleGroup(group);
	}
	@FXML
	public void tb1Function(){
		byClasses=true;
		studentIdField.setDisable( true );
		subjectField.setDisable( false );
		classesField.setDisable( false );

	}
	@FXML
	public void tb2Function(){
		byClasses=false;
		studentIdField.setDisable( false );
		subjectField.setDisable( true );
		classesField.setDisable( true );
	}

	private void initField(){
		studentIdField.setDisable( true );
		studentIdField.textProperty().addListener( ( observable, oldValue, newValue ) -> studentId=Integer.valueOf( newValue ) );
		studentIdField.setPromptText( "1" );
		courseIdField.textProperty().addListener( ( observable, oldValue, newValue ) -> courseId=Integer.valueOf( newValue ) );
		courseIdField.setPromptText( "1" );
		subjectField.textProperty().addListener( ( observable, oldValue, newValue ) -> subject=new String(newValue) );
		subjectField.setPromptText( "计算机科学与技术" );
		classesField.textProperty().addListener( ( observable, oldValue, newValue ) -> classes=new String(newValue) );
		classesField.setPromptText( "2班" );


	}
	@FXML
	private void createStudent(){
		try {
			if ( byClasses )
				GradeController.chooseCourseByClasses( courseId, subject, classes );
			else
				GradeController.chooseCourse( courseId, studentId );
			Alerts.success( "成功", "选课成功" );

		}catch ( Exception e ){
			Alerts.error( "失败", e.getCause().toString() );
		}
	}

}
