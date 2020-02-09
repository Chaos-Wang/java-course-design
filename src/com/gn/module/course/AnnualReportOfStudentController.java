package com.gn.module.course;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.GradeReportItemFactory;
import com.gn.global.bean.form.GradeReportItem;
import com.gn.global.bean.intity.Grade;
import com.gn.global.util.Alerts;
import com.gn.global.util.CsvUtils;
import com.gn.global.util.Utilities;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.nashorn.internal.objects.Global;
import sun.util.calendar.LocalGregorianCalendar;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * Create on  29/12/2019
 * Version 1.0
 */

public class AnnualReportOfStudentController implements Initializable {

	@FXML private TableView<GradeReportItemFactory> gradeReportTable;
	@FXML private TableColumn<GradeReportItemFactory,String> gc1;
	@FXML private TableColumn<GradeReportItemFactory,String> gc2;
	@FXML private TableColumn<GradeReportItemFactory,String> gc3;
	@FXML private TableColumn<GradeReportItemFactory,String> gc4;
	@FXML private TableColumn<GradeReportItemFactory,String> gc5;
	@FXML private TableColumn<GradeReportItemFactory,String> gc6;
	@FXML private TableColumn<GradeReportItemFactory,String> gc7;
	@FXML private TableColumn<GradeReportItemFactory,String> gc8;
	@FXML private TableColumn<GradeReportItemFactory,String> gc9;
	@FXML private Label GPALabel = new Label();
	@FXML private TextField studentIdField = new TextField();
	@FXML private TextField timeField = new TextField();

	private double annualGPA;

	private int studentId;
	private String time;

	private ObservableList<GradeReportItemFactory> gradeReportItemFactoryFactories = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GPALabel.setVisible( false );
		initColumn();
		initTable();
		initField();
	}
	@FXML
	public void onSearch() throws IllegalAccessException {
		gradeReportItemFactoryFactories = FXCollections.observableArrayList();
		Grade grade = new Grade();
		grade.setTime(String.valueOf( Integer.valueOf(time)*10+1 ));
		grade.setStudentId( studentId );
		List list1 = Utilities.filter( grade );
		grade.setTime(String.valueOf( Integer.valueOf(time)*10+2 ));
		List list2 = Utilities.filter( grade );
		for(Object object:list1) {
			GradeReportItemFactory gradeReportItemFactory = new GradeReportItemFactory( new GradeReportItem((Grade)object) );
			gradeReportItemFactoryFactories.add( gradeReportItemFactory );
		}
		for(Object object:list2) {
			GradeReportItemFactory gradeReportItemFactory = new GradeReportItemFactory( new GradeReportItem((Grade)object) );
			gradeReportItemFactoryFactories.add( gradeReportItemFactory );
		}
		gradeReportTable.setEditable( true );
		gradeReportTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
		gradeReportTable.setItems( gradeReportItemFactoryFactories );
		double sumOfCredit = 0;
		double sum=0;
		for( GradeReportItemFactory item: gradeReportTable.getItems()){
			sum+=Double.valueOf(item.GPAProperty().get())*Double.valueOf( item.creditProperty().get() );
			sumOfCredit+=Double.valueOf( item.creditProperty().get() );
		}
		GPALabel.setVisible( true );
		GPALabel.setText( "学年平均绩点：" + sum/sumOfCredit );
	}
	@FXML
	public void onRecover(){
		gradeReportItemFactoryFactories = FXCollections.observableArrayList();
		GPALabel.setVisible( false );
		gradeReportTable.setItems( gradeReportItemFactoryFactories );
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
		gradeReportTable.setItems( gradeReportItemFactoryFactories );
	}

	public void initField(){
		studentIdField.textProperty().addListener( ( observable, oldValue, newValue ) -> studentId=Integer.valueOf( newValue ) );
		timeField.textProperty().addListener( ( observable, oldValue, newValue ) -> time= newValue );
	}

	@FXML
	public void exportAsCsv() {
		try {
			String[] header = { "学生名", "课程名", "班级", "专业", "学院", "成绩", "状态", "绩点", "学分" };
			CsvUtils.writeCSV( gradeReportTable.getItems(), GlobalConfig.EXPORT_DIR + "AnnualReportOfStudent" + studentId + "_" + time + ".csv", header );
			Alerts.success( "成功", "导出成功" );
		}catch(Exception e ){
			Alerts.error( "失败", e.getCause().toString() );
		}
	}
}
