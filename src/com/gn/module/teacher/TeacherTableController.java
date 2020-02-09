package com.gn.module.teacher;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.TeacherFactory;
import com.gn.global.bean.intity.Teacher;
import com.gn.global.plugin.TeacherController;
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

public class TeacherTableController implements Initializable {

    @FXML private TableView<TeacherFactory> teacherTable;
    @FXML private TableColumn<TeacherFactory,String> tc1;
    @FXML private TableColumn<TeacherFactory,String> tc2;

    @FXML private ChoiceBox<String> filterChoice = new ChoiceBox<>();
    @FXML private TextField filterChoiceValueField =new TextField();
    private StringBuilder filterColumn = new StringBuilder("id");
    private StringBuilder filterChoiceValue;
    private ObservableList<TeacherFactory> filtedItemFactories = FXCollections.observableArrayList();
    private ObservableList<TeacherFactory> teacherItemFactories = FXCollections.observableArrayList();
    private Map<String,String> columnMap = new HashMap<>();
    private String[] columnName = {"ID","姓名"};

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
    public void onDelete() throws IllegalAccessException {
        TableSelectionModel<TeacherFactory> a = teacherTable.getSelectionModel();
        if(a.getSelectedIndices().size() != 1) {
            TeacherController.deleteTeacher( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
            teacherTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
            onDelete();
            }else{
                TeacherController.deleteTeacher( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
                teacherTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
                return;
            }
    }
    @FXML
    public void onFilter(){
        ObservableList items = teacherTable.getItems();
        List list = Utilities.tableFilter( items,filterColumn,filterChoiceValue );
        teacherItemFactories = (ObservableList<TeacherFactory>) list.get( 0 );
        filtedItemFactories.addAll( (ObservableList<TeacherFactory>)list.get( 1 ) );
        teacherTable.setItems( teacherItemFactories );
        filterChoiceValueField.setText( "" );
    }
    @FXML
    public void onRecover(){
        ObservableList items = teacherTable.getItems();
        teacherItemFactories = FXCollections.observableArrayList();
        teacherItemFactories.addAll( items );
        teacherItemFactories.addAll( filtedItemFactories );
        filtedItemFactories = FXCollections.observableArrayList();
        teacherTable.setItems( teacherItemFactories );
    }

    public void initColumn(){
        tc1.setCellValueFactory( new PropertyValueFactory<>( "id" ));

        tc2.setCellValueFactory(new PropertyValueFactory<>("name"));
        tc2.setEditable( true );
        tc2.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).nameProperty().setValue(t.getNewValue());
            Teacher teacher = new Teacher(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( teacher );
            transaction1.commit();
            session.close();
        });
        tc2.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initTable() throws IllegalAccessException {
        List list = Utilities.filter( new Teacher() );
        for(Object object:list) {
            TeacherFactory teacherFactory = new TeacherFactory( (Teacher)object );
            teacherItemFactories.add( teacherFactory );
        }
        teacherTable.setEditable( true );
        teacherTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        teacherTable.setItems( teacherItemFactories );
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
        teacherItemFactories =FXCollections.observableArrayList();
        List list = Utilities.filter( new Teacher() );
        for(Object object:list) {
            TeacherFactory teacherFactory = new TeacherFactory( (Teacher)object );
            teacherItemFactories.add( teacherFactory );
        }
        teacherTable.setEditable( true );
        teacherTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        teacherTable.setItems( teacherItemFactories );
    }

    @FXML
    public void exportAsCsv(){
        try{
            CsvUtils.writeCSV( teacherTable.getItems(), GlobalConfig.EXPORT_DIR+"Teacher"+".csv",columnName);
            Alerts.success( "成功", "导出成功" );
        }catch ( Exception e ){
            Alerts.error( "失败", e.getCause().toString() );
        }
    }
}
