package com.gn.module.course;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.GradeFactory;
import com.gn.global.bean.intity.Grade;
import com.gn.global.plugin.GradeController;
import com.gn.global.util.Alerts;
import com.gn.global.util.CsvUtils;
import com.gn.global.util.Utilities;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * Create on  29/12/2019
 * Version 1.0
 */

public class StudentGradeTableController implements Initializable {

	@FXML private TableView<GradeFactory> gradeTable;
	@FXML private TableColumn<GradeFactory,String> gc1;
	@FXML private TableColumn<GradeFactory,String> gc2;
	@FXML private TableColumn<GradeFactory,String> gc3;
	@FXML private TableColumn<GradeFactory,String> gc4;
	@FXML private TableColumn<GradeFactory,String> gc5;
	@FXML private TableColumn<GradeFactory,String> gc6;
	@FXML private TableColumn<GradeFactory,String> gc7;
	@FXML private TextField studentIdField = new TextField();
	@FXML private TextField timeField = new TextField();
	private int studentId;
	private String time;

	private ObservableList<GradeFactory> gradeItemFactories = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initColumn();
		initTable();
		initField();
	}
	@FXML
	public void onDelete() {
		TableSelectionModel<GradeFactory> a = gradeTable.getSelectionModel();
		if(a.getSelectedIndices().size() != 1) {
			GradeController.deleteGrade( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
			gradeTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
			onDelete();
		}else{
			GradeController.deleteGrade( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
			gradeTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
			return;
		}
	}
	@FXML
	public void onSearch() throws IllegalAccessException {
		gradeItemFactories = FXCollections.observableArrayList();
		Grade grade = new Grade();
		grade.setTime(time);
		grade.setStudentId( studentId );
		List list = Utilities.filter( grade );
		for(Object object:list) {
			GradeFactory gradeFactory = new GradeFactory( (Grade)object );
			gradeItemFactories.add( gradeFactory );
		}
		gradeTable.setEditable( true );
		gradeTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
		gradeTable.setItems( gradeItemFactories );
	}
	@FXML
	public void onRecover(){
		gradeItemFactories = FXCollections.observableArrayList();
		gradeTable.setItems( gradeItemFactories );
	}

	public void initColumn(){
		gc1.setCellValueFactory( new PropertyValueFactory<>( "id" ));

		gc2.setCellValueFactory(new PropertyValueFactory<>("courseId"));
		gc2.setEditable( true );
		gc2.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).courseIdProperty().setValue(t.getNewValue());
			Grade grade = new Grade(t.getRowValue());
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( grade );
			transaction1.commit();
			session.close();
		});
		gc2.setCellFactory(TextFieldTableCell.forTableColumn());

		gc3.setCellValueFactory(new PropertyValueFactory<>("studentId"));
		gc3.setEditable( true );
		gc3.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).studentIdProperty().setValue(t.getNewValue());
			Grade grade = new Grade(t.getRowValue());
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( grade );
			transaction1.commit();
			session.close();
		});
		gc3.setCellFactory(TextFieldTableCell.forTableColumn());

		gc4.setCellValueFactory(new PropertyValueFactory<>("status"));

		gc5.setCellValueFactory(new PropertyValueFactory<>("grade"));
		gc5.setEditable( true );
		gc5.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).gradeProperty().setValue(t.getNewValue());
			Grade grade = new Grade(t.getRowValue());
			grade.setGPA(0);
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( grade );
			transaction1.commit();
			session.close();
		});
		gc5.setCellFactory(TextFieldTableCell.forTableColumn());

		gc6.setCellValueFactory(new PropertyValueFactory<>("time"));
		gc6.setEditable( true );
		gc6.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).timeProperty().setValue(t.getNewValue());
			Grade grade = new Grade(t.getRowValue());
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( grade );
			transaction1.commit();
			session.close();
		});
		gc6.setCellFactory(TextFieldTableCell.forTableColumn());

		gc7.setCellValueFactory(new PropertyValueFactory<>("GPA"));
		gc7.setEditable( true );
		gc7.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).GPAProperty().setValue(t.getNewValue());
			Grade grade = new Grade(t.getRowValue());
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( grade );
			transaction1.commit();
			session.close();
		});
		gc7.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	public void initTable() {
		gradeTable.setItems( gradeItemFactories );
	}

	public void initField(){
		studentIdField.textProperty().addListener( ( observable, oldValue, newValue ) -> studentId=Integer.valueOf( newValue ) );
		timeField.textProperty().addListener( ( observable, oldValue, newValue ) -> time= newValue );
	}

	public void exportAsCsv(){
		try {
			String[] header = { "ID", "课程ID", "学生ID", "状态", "成绩", "学期", "绩点" };
			CsvUtils.writeCSV( gradeTable.getItems(), GlobalConfig.EXPORT_DIR + "StudentGradeTable_" + time + "_" + studentId + ".csv", header );
			Alerts.success( "成功", "导出成功" );

		}catch ( Exception e ){
			Alerts.error( "失败", e.getCause().toString() );

		}
	}
}
