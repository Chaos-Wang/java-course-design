package com.gn.module.course;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.CourseSummaryItemFactory;
import com.gn.global.bean.form.CourseSummaryItem;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * Create on  29/12/2019
 * Version 1.0
 */

public class CourseSummaryController implements Initializable {

	@FXML private TableView<CourseSummaryItemFactory> courseSummaryTable;
	@FXML private TableColumn<CourseSummaryItemFactory,String> gc1;
	@FXML private TableColumn<CourseSummaryItemFactory,String> gc2;
	@FXML private TableColumn<CourseSummaryItemFactory,String> gc3;
	@FXML private TableColumn<CourseSummaryItemFactory,String> gc4;
	@FXML private TableColumn<CourseSummaryItemFactory,String> gc5;

	@FXML private TextField timeField = new TextField();

	private String time;

	private ObservableList<CourseSummaryItemFactory> courseSummaryItemFactoryFactories = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initColumn();
		initTable();
		initField();
	}
	@FXML
	public void onSearch() throws IllegalAccessException {
		courseSummaryItemFactoryFactories = FXCollections.observableArrayList();
		Grade grade = new Grade();
		grade.setTime(time);
		List list = Utilities.filter( grade );
		for(Object object:list) {
			CourseSummaryItemFactory courseSummaryItemFactory = new CourseSummaryItemFactory( new CourseSummaryItem(((Grade)object).getCourseId()) );
			courseSummaryItemFactoryFactories.add( courseSummaryItemFactory );
		}
		courseSummaryTable.setItems( courseSummaryItemFactoryFactories );
	}
	@FXML
	public void onRecover(){
		courseSummaryItemFactoryFactories = FXCollections.observableArrayList();
		courseSummaryTable.setItems( courseSummaryItemFactoryFactories  );
	}

	public void initColumn(){
		gc1.setCellValueFactory( new PropertyValueFactory<>( "courseId" ));
		gc2.setCellValueFactory( new PropertyValueFactory<>( "courseName" ));
		gc3.setCellValueFactory(new PropertyValueFactory<>("averageGrade"));
		gc4.setCellValueFactory(new PropertyValueFactory<>("averageGPA"));
		gc5.setCellValueFactory(new PropertyValueFactory<>("passRate"));

	}

	public void initTable() {
		courseSummaryTable.setItems( courseSummaryItemFactoryFactories );
	}

	public void initField(){
		timeField.textProperty().addListener( ( observable, oldValue, newValue ) -> time= newValue );
	}

	@FXML
	public void exportAsCsv(){
		try {
			String[] header = { "课程ID", "课程名", "平均成绩", "平均绩点", "通过率" };
			CsvUtils.writeCSV( courseSummaryTable.getItems(), GlobalConfig.EXPORT_DIR + "CourseSummary_" + time + ".csv", header );
			Alerts.success( "成功", "导出成功" );
		}catch ( Exception e){
			Alerts.error( "失败", e.getCause().toString() );
		}
	}

}
