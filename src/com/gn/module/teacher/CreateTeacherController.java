package com.gn.module.teacher;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.intity.Student;
import com.gn.global.bean.intity.Teacher;
import com.gn.global.plugin.StudentController;
import com.gn.global.plugin.TeacherController;
import com.gn.global.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * @date 2020/1/1 11:25
 * @title
 */
public class CreateTeacherController implements Initializable {
    @FXML private Button createButton = new Button();

	@FXML private TextField nameField =new TextField();

	private Teacher teacher = new Teacher();


	@Override
	public void initialize( URL location, ResourceBundle resources) {
			initField();
	}

	private void initField(){
		nameField.textProperty().addListener( ( observable, oldValue, newValue ) -> teacher.setName( newValue ) );
	}
	@FXML
	private void createTeacher() {
		try {
			TeacherController.createTeacher( 0,teacher );
			Alerts.success( "成功", "创建成功" );

		} catch ( Exception e ) {
			Alerts.error( "失败", e.getCause().toString() );
		}
	}

}
