package com.gn.module.course;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.CourseFactory;
import com.gn.global.bean.intity.Course;
import com.gn.global.plugin.CourseController;
import com.gn.global.util.Alerts;
import com.gn.global.util.CsvUtils;
import com.gn.global.util.Utilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class CourseTableController implements Initializable {

	@FXML private TableView<CourseFactory> courseTable;
	@FXML private TableColumn<CourseFactory,String> cc1;
	@FXML private TableColumn<CourseFactory,String> cc2;
	@FXML private TableColumn<CourseFactory,String> cc3;
	@FXML private TableColumn<CourseFactory,String> cc4;
	@FXML private TableColumn<CourseFactory,String> cc5;

	@FXML private ChoiceBox<String> filterChoice = new ChoiceBox<>();
	@FXML private TextField filterChoiceValueField =new TextField();
	private StringBuilder filterColumn = new StringBuilder("id");
	private StringBuilder filterChoiceValue;
	private ObservableList<CourseFactory> filtedItemFactories = FXCollections.observableArrayList();
	private ObservableList<CourseFactory> courseItemFactories = FXCollections.observableArrayList();
	private Map<String,String> columnMap = new HashMap<>();
	private String[] columnName = {"ID","名称","教师ID","开课学期","学分"};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initColumn();
			initTable();
			initFilter();
		} catch ( IllegalAccessException e ) {
			e.printStackTrace();
		}
	}
	@FXML
	public void onDelete() {
		TableSelectionModel<CourseFactory> a = courseTable.getSelectionModel();
		if(a.getSelectedIndices().size() != 1) {
			CourseController.deleteCourse( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
			courseTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
			onDelete();
		}else{
			CourseController.deleteCourse( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
			courseTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
			return;
		}
	}
	@FXML
	public void onFilter(){
		ObservableList items = courseTable.getItems();
		List list = Utilities.tableFilter( items,filterColumn,filterChoiceValue );
		courseItemFactories = (ObservableList<CourseFactory>) list.get( 0 );
		filtedItemFactories.addAll( (ObservableList<CourseFactory>)list.get( 1 ) );
		courseTable.setItems( courseItemFactories );
		filterChoiceValueField.setText( "" );
	}
	@FXML
	public void onRecover(){
		ObservableList items = courseTable.getItems();
		courseItemFactories = FXCollections.observableArrayList();
		courseItemFactories.addAll( items );
		courseItemFactories.addAll( filtedItemFactories );
		filtedItemFactories = FXCollections.observableArrayList();
		courseTable.setItems( courseItemFactories );
	}

	public void initColumn(){
		cc1.setCellValueFactory( new PropertyValueFactory<>( "id" ));

		cc2.setCellValueFactory(new PropertyValueFactory<>("name"));
		cc2.setEditable( true );
		cc2.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).nameProperty().setValue(t.getNewValue());
			Course Course = new Course(t.getRowValue());
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( Course );
			transaction1.commit();
			session.close();
		});
		cc2.setCellFactory(TextFieldTableCell.forTableColumn());

		cc3.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
		cc3.setEditable( true );
		cc3.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).teacherIdProperty().setValue(t.getNewValue());
			Course Course = new Course(t.getRowValue());
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( Course );
			transaction1.commit();
			session.close();
		});
		cc3.setCellFactory(TextFieldTableCell.forTableColumn());

		cc4.setCellValueFactory(new PropertyValueFactory<>("time"));
		cc4.setEditable( true );
		cc4.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).timeProperty().setValue(t.getNewValue());
			Course Course = new Course(t.getRowValue());
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( Course );
			transaction1.commit();
			session.close();
		});
		cc4.setCellFactory(TextFieldTableCell.forTableColumn());

		cc5.setCellValueFactory(new PropertyValueFactory<>("credit"));
		cc5.setEditable( true );
		cc5.setOnEditCommit( t -> {
			t.getTableView().getItems().get( t.getTablePosition().getRow()).creditProperty().setValue(t.getNewValue());
			Course Course = new Course(t.getRowValue());
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction1 = session.getTransaction();
			transaction1.begin();
			session.saveOrUpdate( Course );
			transaction1.commit();
			session.close();
		});
		cc5.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	public void initTable() throws IllegalAccessException {
		List list = Utilities.filter( new Course() );
		for(Object object:list) {
			CourseFactory courseFactory = new CourseFactory( (Course)object );
			courseItemFactories.add( courseFactory );
		}
		courseTable.setEditable( true );
		courseTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

		courseTable.setItems( courseItemFactories );
	}

	public void initFilter(){
		columnMap.put( "ID","id" );
		columnMap.put( "名称","name" );
		columnMap.put( "教师ID","teacherId" );
		columnMap.put( "开课学期","time" );
		columnMap.put( "学分","credit" );
		filterChoice.getItems().addAll(columnName);
		filterChoice.getSelectionModel().selectFirst();//默认选中第一个选项
		filterChoice.getSelectionModel().selectedIndexProperty().addListener( (ChangeListener) ( observable, oldValue, newValue ) -> filterColumn = new StringBuilder(columnMap.get( columnName[ ((Integer) newValue).intValue() ] )) );
		filterChoiceValueField.textProperty().addListener( ( observable, oldValue, newValue ) -> filterChoiceValue = new StringBuilder(newValue) );
	}

	public void refresh() throws IllegalAccessException { 
		courseItemFactories =FXCollections.observableArrayList();
		List list = Utilities.filter( new Course() );
		for(Object object:list) {
			CourseFactory CourseFactory = new CourseFactory( (Course)object );
			courseItemFactories.add( CourseFactory );
		}
		courseTable.setEditable( true );
		courseTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

		courseTable.setItems( courseItemFactories );
	}

	@FXML
	public void exportAsCsv(){
		try {
			CsvUtils.writeCSV( courseTable.getItems(), GlobalConfig.EXPORT_DIR + "Course" + ".csv", columnName );
			Alerts.success( "成功", "导出成功" );

		}catch ( Exception e){
			Alerts.error( "失败", e.getCause().toString() );

		}
	}
}
