package com.gn.module.system;

import com.gn.global.GlobalConfig;
import com.gn.global.bean.factory.UserFactory;
import com.gn.global.bean.intity.User;
import com.gn.global.plugin.UserController;
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

public class UserTableController implements Initializable {

    @FXML private TableView<UserFactory> userTable;
    @FXML private TableColumn<UserFactory,String> sc1;
    @FXML private TableColumn<UserFactory,String> sc2;
    @FXML private TableColumn<UserFactory,String> sc3;
    @FXML private TableColumn<UserFactory,String> sc4;
    @FXML private TableColumn<UserFactory,String> sc5;
    @FXML private TableColumn<UserFactory,String> sc6;
    @FXML private TableColumn<UserFactory,String> sc7;
    @FXML private TableColumn<UserFactory,String> sc8;
    @FXML private ChoiceBox<String> filterChoice = new ChoiceBox<>();
    @FXML private TextField filterChoiceValueField =new TextField();
    private StringBuilder filterColumn = new StringBuilder("id");
    private StringBuilder filterChoiceValue;
    private ObservableList<UserFactory> filtedItemFactories = FXCollections.observableArrayList();
    private ObservableList<UserFactory> userItemFactories = FXCollections.observableArrayList();
    private Map<String,String> columnMap = new HashMap<>();
    private String[] columnName = {"ID","用户名","密码","角色","权限","关联教师ID","姓名","邮箱"};

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
        TableSelectionModel<UserFactory> a = userTable.getSelectionModel();
        if(a.getSelectedIndices().size() != 1) {
            UserController.deleteUser( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
            userTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
            onDelete();
            }else{
                UserController.deleteUser( 0, Integer.valueOf( a.getSelectedItems().get( a.getSelectedIndices().size()-1 ).idProperty().get() ) );
                userTable.getItems().remove( a.getSelectedIndices().get( a.getSelectedIndices().size()-1 ).intValue() );
                return;
            }
    }
    @FXML
    public void onFilter(){
        ObservableList items = userTable.getItems();
        List list = Utilities.tableFilter( items,filterColumn,filterChoiceValue );
        userItemFactories = (ObservableList<UserFactory>) list.get( 0 );
        filtedItemFactories.addAll( (ObservableList<UserFactory>)list.get( 1 ) );
        userTable.setItems( userItemFactories );
        filterChoiceValueField.setText( "" );
    }
    @FXML
    public void onRecover(){
        ObservableList items = userTable.getItems();
        userItemFactories = FXCollections.observableArrayList();
        userItemFactories.addAll( items );
        userItemFactories.addAll( filtedItemFactories );
        filtedItemFactories = FXCollections.observableArrayList();
        userTable.setItems( userItemFactories );
    }

    public void initColumn(){
        sc1.setCellValueFactory( new PropertyValueFactory<>( "id" ));

        sc2.setCellValueFactory(new PropertyValueFactory<>("userName"));
        sc2.setEditable( true );
        sc2.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).userNameProperty().setValue(t.getNewValue());
            User user = new User(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( user );
            transaction1.commit();
            session.close();
        });
        sc2.setCellFactory(TextFieldTableCell.forTableColumn());

        sc3.setCellValueFactory(new PropertyValueFactory<>("password"));
        sc3.setEditable( true );
        sc3.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).passwordProperty().setValue(t.getNewValue());
            User user = new User(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( user );
            transaction1.commit();
            session.close();
        });
        sc3.setCellFactory(TextFieldTableCell.forTableColumn());

        sc4.setCellValueFactory(new PropertyValueFactory<>("grade"));

        sc5.setCellValueFactory(new PropertyValueFactory<>("limits"));

        sc6.setCellValueFactory(new PropertyValueFactory<>("connectedId"));

        sc7.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        sc7.setEditable( true );
        sc7.setOnEditCommit( t -> {
            t.getTableView().getItems().get( t.getTablePosition().getRow()).fullNameProperty().setValue(t.getNewValue());
            User user = new User(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( user );
            transaction1.commit();
            session.close();
        });
        sc7.setCellFactory(TextFieldTableCell.forTableColumn());

        sc8.setCellValueFactory(new PropertyValueFactory<>("email"));
        sc8.setEditable( true );
        sc8.setOnEditCommit( t ->{
            t.getTableView().getItems().get( t.getTablePosition().getRow()).emailProperty().setValue(t.getNewValue());
            User user = new User(t.getRowValue());
            Configuration configuration = new Configuration();
            configuration.configure();
            SessionFactory factory = configuration.buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction1 = session.getTransaction();
            transaction1.begin();
            session.saveOrUpdate( user );
            transaction1.commit();
            session.close();
        });
        sc8.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initTable() throws IllegalAccessException {
        List list = Utilities.filter( new User() );
        for(Object object:list) {
            UserFactory userFactory = new UserFactory( (User)object );
            userItemFactories.add( userFactory );
        }
        userTable.setEditable( true );
        userTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        userTable.setItems( userItemFactories );
    }

    public void initFilter(){
        columnMap.put( "ID","id" );
        columnMap.put( "用户名","userName" );
        columnMap.put( "密码","password" );
        columnMap.put( "角色","grade" );
        columnMap.put( "权限","limits" );
        columnMap.put( "关联教师ID","connectedId" );
        columnMap.put( "姓名","fullName" );
        columnMap.put( "邮箱","email" );
        filterChoice.getItems().addAll(columnName);
        filterChoice.getSelectionModel().selectFirst();//默认选中第一个选项
        filterChoice.getSelectionModel().selectedIndexProperty().addListener( (ChangeListener) ( observable, oldValue, newValue ) -> filterColumn = new StringBuilder(columnMap.get( columnName[ ((Integer) newValue).intValue() ] )) );
        filterChoiceValueField.textProperty().addListener( ( observable, oldValue, newValue ) -> filterChoiceValue = new StringBuilder(newValue) );
    }

    public void refresh() throws IllegalAccessException {
        userItemFactories =FXCollections.observableArrayList();
        List list = Utilities.filter( new User() );
        for(Object object:list) {
            UserFactory userFactory = new UserFactory( (User)object );
            userItemFactories.add( userFactory );
        }
        userTable.setEditable( true );
        userTable.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        userTable.setItems( userItemFactories );
    }
    @FXML
    public void exportAsCsv(){
        try{
            CsvUtils.writeCSV( userTable.getItems(), GlobalConfig.EXPORT_DIR+"User"+".csv",columnName);
            Alerts.success( "成功", "导出成功" );
        }catch ( Exception e ){
            Alerts.error( "失败", e.getCause().toString() );
        }
    }
}
