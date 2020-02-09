package com.gn.module.course;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.GradeReportItemFactory;
import com.gn.global.bean.form.GradeReportItem;
import com.gn.global.bean.intity.Grade;
import com.gn.global.util.Alerts;
import com.gn.global.util.CsvUtils;
import com.gn.global.util.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * Create on  29/12/2019
 * Version 1.0
 */

public class GradeReportOfCourse implements Initializable {

	@FXML private TableView<GradeReportItemFactory> gradeReportOfCourseTable;
	@FXML private TableColumn<GradeReportItemFactory,String> gc1;
	@FXML private TableColumn<GradeReportItemFactory,String> gc2;
	@FXML private TableColumn<GradeReportItemFactory,String> gc3;
	@FXML private TableColumn<GradeReportItemFactory,String> gc4;
	@FXML private TableColumn<GradeReportItemFactory,String> gc5;
	@FXML private TableColumn<GradeReportItemFactory,String> gc6;
	@FXML private TableColumn<GradeReportItemFactory,String> gc7;
	@FXML private TableColumn<GradeReportItemFactory,String> gc8;
	@FXML private TableColumn<GradeReportItemFactory,String> gc9;

	@FXML private TextField courseIdField = new TextField();
	@FXML private TextField timeField = new TextField();

	private int courseId;
	private String time;

	private ObservableList<GradeReportItemFactory> gradeReportItemFactoryFactories = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initColumn();
		initTable();
		initField();
	}
	@FXML
	public void onSearch() throws IllegalAccessException {
		gradeReportItemFactoryFactories = FXCollections.observableArrayList();
		Grade grade = new Grade();
		grade.setTime(time);
		grade.setCourseId( courseId );
		List list = Utilities.filter( grade );
		Collections.sort( list );
		for(Object object:list) {
			GradeReportItemFactory gradeReportItemFactory = new GradeReportItemFactory( new GradeReportItem((Grade)object) );
			gradeReportItemFactoryFactories.add( gradeReportItemFactory );
		}
		gradeReportOfCourseTable.setItems( gradeReportItemFactoryFactories );

	}
	@FXML
	public void onRecover(){
		gradeReportItemFactoryFactories = FXCollections.observableArrayList();
		gradeReportOfCourseTable.setItems( gradeReportItemFactoryFactories );
	}

	public void initColumn(){
		gc1.setCellValueFactory( new PropertyValueFactory<>( "studentName" ));
		gc2.setCellValueFactory( new PropertyValueFactory<>( "courseName" ));
		gc3.setCellValueFactory(new PropertyValueFactory<>("classes"));
		gc4.setCellValueFactory(new PropertyValueFactory<>("subject"));
		gc5.setCellValueFactory(new PropertyValueFactory<>("college"));
		gc6.setCellValueFactory(new PropertyValueFactory<>("grade"));
		gc7.setCellValueFactory(new PropertyValueFactory<>("status"));
		gc8.setCellValueFactory(new PropertyValueFactory<>("GPA"));
		gc9.setCellValueFactory(new PropertyValueFactory<>("credit"));
	}

	public void initTable() {
		gradeReportOfCourseTable.setItems( gradeReportItemFactoryFactories );
	}

	public void initField(){
		courseIdField.textProperty().addListener( ( observable, oldValue, newValue ) -> courseId=Integer.valueOf( newValue ) );
		timeField.textProperty().addListener( ( observable, oldValue, newValue ) -> time= newValue );
	}

	@FXML
	public void exportAsCsv(){
		try {
			String[] header = { "学生名", "课程名", "班级", "专业", "学院", "成绩", "状态", "绩点", "学分" };
			CsvUtils.writeCSV( gradeReportOfCourseTable.getItems(), GlobalConfig.EXPORT_DIR + "GradeReportOfCourse_" + time + "_" + courseId + ".csv", header );
			Alerts.success( "成功", "导出成功" );
		}catch ( Exception e ){
			Alerts.error( "失败", e.getCause().toString() );

		}
	}
}
