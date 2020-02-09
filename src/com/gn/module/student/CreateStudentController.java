package com.gn.module.student;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.StudentFactory;
import com.gn.global.bean.intity.Student;
import com.gn.global.plugin.StudentController;
import com.gn.global.util.Alerts;
import com.gn.global.util.Utilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * @date 2020/1/1 11:25
 * @title
 */
public class CreateStudentController implements Initializable {
	@FXML private ToggleButton tb1 = new ToggleButton();
	@FXML private ToggleButton tb2 = new ToggleButton();
    @FXML private Button createButton = new Button();

	@FXML private TextField nameField =new TextField();
	@FXML private TextField collegeField =new TextField();
	@FXML private TextField collegeDemoField =new TextField();

	@FXML private TextField subjectField =new TextField();
	@FXML private TextField subjectDemoField =new TextField();

	@FXML private TextField classesField =new TextField();
	@FXML private TextField classesDemoField =new TextField();

	private Student student = GlobalConfig.STUDENTDEMO;
	private Student studentDemo = GlobalConfig.STUDENTDEMO;
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
		collegeField.setDisable( false );
		collegeField.setPromptText( student.getCollege() );
		subjectField.setDisable( false );
		subjectField.setPromptText( student.getSubject() );
		classesField.setDisable( false );
		classesField.setPromptText( student.getClasses() );
	}
	public void tb2Function(){
		collegeField.setDisable( true );
		collegeField.setPromptText( studentDemo.getCollege() );
		subjectField.setDisable( true );
		subjectField.setPromptText( studentDemo.getSubject() );
		classesField.setDisable( true );
		classesField.setPromptText( studentDemo.getClasses() );
		student = studentDemo;
	}
	private void initField(){
		nameField.textProperty().addListener( ( observable, oldValue, newValue ) -> student.setName( newValue ) );

		collegeField.textProperty().addListener( ( observable, oldValue, newValue ) -> studentDemo.setCollege( newValue ) );
		collegeField.setPromptText( studentDemo.getCollege() );
		collegeDemoField.textProperty().addListener( ( observable, oldValue, newValue ) -> studentDemo.setCollege( newValue ) );

		subjectField.textProperty().addListener( ( observable, oldValue, newValue ) -> student.setSubject( newValue ) );
		subjectField.setPromptText( studentDemo.getSubject() );
		subjectDemoField.textProperty().addListener( ( observable, oldValue, newValue ) -> studentDemo.setSubject( newValue ) );

		classesField.textProperty().addListener( ( observable, oldValue, newValue ) -> student.setClasses( newValue ) );
		classesField.setPromptText( studentDemo.getClasses() );
		classesDemoField.textProperty().addListener( ( observable, oldValue, newValue ) -> studentDemo.setClasses( newValue ) );

	}
	@FXML
	private void createStudent() {
		try {
			StudentController.createStudent( 0, student );
			Alerts.success( "成功", "创建成功" );

		} catch ( Exception e ) {
			Alerts.error( "失败", e.getCause().toString() );
		}
	}

}
