package com.gn.module.student;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.intity.Student;
import com.gn.global.bean.factory.StudentFactory;
import com.gn.global.plugin.StudentController;
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

public class StudentTableController implements Initializable {

    @FXML private TableView<StudentFactory> studentTable;
    @FXML private TableColumn<StudentFactory,String> sc1;
    @FXML private TableColumn<StudentFactory,String> sc2;
    @FXML private TableColumn<StudentFactory,String> sc3;
    @FXML private TableColumn<StudentFactory,String> sc4;
    @FXML private TableColumn<StudentFactory,String> sc5;
    @FXML private TableColumn<StudentFactory,String> sc6;
    @FXML private TableColumn<StudentFactory,String> sc7;
    @FXML private TableColumn<StudentFactory,String> sc8;
    @FXML private ChoiceBox<String> filterChoice = new ChoiceBox<>();
    @FXML private TextField filterChoiceValueField =new TextField();
    private StringBuilder filterColumn = new StringBuilder("id");
    private StringBuilder filterChoiceValue;
    private ObservableList<StudentFactory> filtedItemFactories = FXCollections.observableArrayList();
    private ObservableList<StudentFactory> studentItemFactories = FXCollections.observableArrayList();
    private Map<String,String> columnMap = new HashMap<>();
    private String[] columnName = {"ID","姓名","状态","入学时间","学级","专业","学院","班级"};

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
        TableSelectionModel<StudentFactory> a = studentTable.getSelectionModel();
        if(a.getSelectedIndices().size() != 1) {
            StudentController.deleteStudent( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
            studentTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
            onDelete();
            }else{
                StudentController.deleteStudent( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
                studentTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
                return;
            }
    }
    @FXML
    public void onFilter(){
        ObservableList items = studentTable.getItems();
        List list = Utilities.tableFilter( items,filterColumn,filterChoiceValue );
        studentItemFactories = (ObservableList<StudentFactory>) list.get( 0 );
        filtedItemFactories.addAll( (ObservableList<StudentFactory>)list.get( 1 ) );
        studentTable.setItems( studentItemFactories );
        filterChoiceValueField.setText( "" );
    }
    @FXML
    public void onRecover(){
        ObservableList items = studentTable.getItems();
        studentItemFactories = FXCollections.observableArrayList();
        studentItemFactories.addAll( items );
        studentItemFactories.addAll( filtedItemFactories );
        filtedItemFactories = FXCollections.observableArrayList();
        studentTable.setItems( studentItemFactories );
    }

    public void initColumn(){
        sc1.setCellValueFactory( new PropertyValueFactory<>( "id" ));
        sc2.setCellValueFactory(new PropertyValueFactory<>("name"));

        sc2.setEditable( true );
        sc2.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).nameProperty().setValue(t.getNewValue());
            Student student = new Student(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( student );
            transaction1.commit();
            session.close();
        });
        sc2.setCellFactory(TextFieldTableCell.forTableColumn());

        sc3.setCellValueFactory(new PropertyValueFactory<>("status"));
        sc3.setEditable( true );
        sc3.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).statusProperty().setValue(t.getNewValue());
            Student student = new Student(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( student );
            transaction1.commit();
            session.close();
        });
        sc3.setCellFactory(TextFieldTableCell.forTableColumn());

        sc4.setCellValueFactory(new PropertyValueFactory<>("checkInTime"));
        sc4.setEditable( true );
        sc4.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).checkInTimeProperty().setValue(t.getNewValue());
            Student student = new Student(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( student );
            transaction1.commit();
            session.close();
        });
        sc4.setCellFactory(TextFieldTableCell.forTableColumn());

        sc5.setCellValueFactory(new PropertyValueFactory<>("level"));
        sc5.setEditable( true );
        sc5.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).levelProperty().setValue(t.getNewValue());
            Student student = new Student(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( student );
            transaction1.commit();
            session.close();
        });
        sc5.setCellFactory(TextFieldTableCell.forTableColumn());

        sc6.setCellValueFactory(new PropertyValueFactory<>("subject"));
        sc6.setEditable( true );
        sc6.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).subjectProperty().setValue(t.getNewValue());
            Student student = new Student(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( student );
            transaction1.commit();
            session.close();
        });
        sc6.setCellFactory(TextFieldTableCell.forTableColumn());

        sc7.setCellValueFactory(new PropertyValueFactory<>("college"));
        sc7.setEditable( true );
        sc7.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).collegeProperty().setValue(t.getNewValue());
            Student student = new Student(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( student );
            transaction1.commit();
            session.close();
        });
        sc7.setCellFactory(TextFieldTableCell.forTableColumn());

        sc8.setCellValueFactory(new PropertyValueFactory<>("classes"));
        sc8.setEditable( true );
        sc8.setOnEditCommit( t ->{
            t.getTableView().getItems().get( t.getTablePosition().getRow()).classesProperty().setValue(t.getNewValue());
            Student student = new Student(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( student );
            transaction1.commit();
            session.close();
        });
        sc8.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initTable() throws IllegalAccessException {
        List list = Utilities.filter( new Student() );
        for(Object object:list) {
            StudentFactory studentFactory = new StudentFactory( (Student)object );
            studentItemFactories.add( studentFactory );
        }
        studentTable.setEditable( true );
        studentTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        studentTable.setItems( studentItemFactories );
    }

    public void initFilter(){
        columnMap.put( "ID","id" );
        columnMap.put( "姓名","name" );
        columnMap.put( "状态","status" );
        columnMap.put( "入学时间","checkInTime" );
        columnMap.put( "学级","level" );
        columnMap.put( "专业","subject" );
        columnMap.put( "学院","college" );
        columnMap.put( "班级","classes" );
        filterChoice.getItems().addAll(columnName);
        filterChoice.getSelectionModel().selectFirst();//默认选中第一个选项
        filterChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed ( ObservableValue observable, Object oldValue, Object newValue ) {
                filterColumn = new StringBuilder(columnMap.get( columnName[ ((Integer) newValue).intValue() ] ));
            }
        });
        filterChoiceValueField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterChoiceValue = new StringBuilder(newValue);
            }
        });
    }

    public void refresh() throws IllegalAccessException {
        studentItemFactories =FXCollections.observableArrayList();
        List list = Utilities.filter( new Student() );
        for(Object object:list) {
            StudentFactory studentFactory = new StudentFactory( (Student)object );
            studentItemFactories.add( studentFactory );
        }
        studentTable.setEditable( true );
        studentTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        studentTable.setItems( studentItemFactories );
    }

    @FXML
    public void exportAsCsv(){
        try {
            CsvUtils.writeCSV( studentTable.getItems(), GlobalConfig.EXPORT_DIR + "Student" + ".csv", columnName );
            Alerts.success( "成功", "导出成功" );
        }catch ( Exception e ){
            Alerts.error( "失败", e.getCause().toString() );
        }
    }
}
