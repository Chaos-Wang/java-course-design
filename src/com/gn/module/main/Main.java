/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.module.main;

import com.gn.GNAvatarView;
import com.gn.global.plugin.ViewManager;
import com.gn.global.factory.AlertCell;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 2.0
 */
public class Main implements Initializable {

    @FXML private GNAvatarView avatar;
    @FXML private HBox searchBox;
    @FXML private VBox views;
    @FXML public  ScrollPane body;
    @FXML public  Label title;
    @FXML private TextField search;
    @FXML private ScrollPane scroll;
    @FXML private TitledPane student;
    @FXML private TitledPane grade;
    @FXML private TitledPane teacher;
    @FXML private TitledPane system;
    @FXML private Button home;
    @FXML private Button hamburger;
    @FXML private SVGPath searchIcon;
    @FXML private StackPane root;
    @FXML private Button clear;
    @FXML private JFXButton config;
    @FXML private VBox drawer;
    @FXML private JFXBadge bg_info;

    @FXML private RadioButton available;

    private FilteredList<Button> filteredList = null;

    public static  final PopOver popConfig = new PopOver();
    public static  final PopOver popup     = new PopOver();

    private ObservableList<Button> items           = FXCollections.observableArrayList();
    private ObservableList<Button> studentItems    = FXCollections.observableArrayList();
    private ObservableList<Button> gradeItems      = FXCollections.observableArrayList();
    private ObservableList<Button> teacherItems      = FXCollections.observableArrayList();
    private ObservableList<Button> systemItems      = FXCollections.observableArrayList();

    private JFXDialog       dialog          = new JFXDialog();
    private JFXDialogLayout dialog_layout   = new JFXDialogLayout();

    private String path = "/com/gn/theme/css/";
    boolean scrolling   = false;

    private Parent popContent;
    public static Main ctrl;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        ctrl = this;
        loadContentPopup();

        populateItems();
        filteredList = new FilteredList<>(items, s -> true);

        search.textProperty().addListener(obs -> {

            String filter = search.getText();
            if (filter == null || filter.length() == 0) {
                barInitial();
                clear.setMouseTransparent(true);
                searchIcon.setContent("M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z");
            } else {
                barFiltered(filter);
                clear.setMouseTransparent(false);
                searchIcon.setContent("M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z");

            }
        });
        body.setContent(ViewManager.getInstance().get("dashboard"));

        try {
            addSubPop();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        drawer.setPopStylesheet(getClass().getResource("/com/gn/theme/css/popover.css"));
    }


    @FXML
    private void altLayout() {


        int minimum = 70;
        int max = 250;

        if(drawer.getPrefWidth() == max){

            drawer.setPrefWidth(minimum);

            drawer.getChildren().remove(searchBox);

            for(Node node : views.getChildren()) {
                if(node instanceof Button){
                    ((Button) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    ((Button) node).setAlignment(Pos.BASELINE_CENTER);
                } else if(node instanceof TitledPane){
                    ((TitledPane) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    ((TitledPane) node).setAlignment(Pos.BASELINE_CENTER);
                    ((TitledPane) node).setExpanded(false);
                    ((TitledPane) node).setCollapsible(false);
                } else {
                    break;
                }
            }

            avatar.setStrokeWidth(0);
            addEvents();
        } else {
            drawer.setPrefWidth(max);

            searchBox.toBack();
            avatar.toBack();
            avatar.setStrokeWidth(2);
            for(Node node : views.getChildren()){
                if(node instanceof Button){
                    ((Button) node).setContentDisplay(ContentDisplay.LEFT);
                    ((Button) node).setAlignment(Pos.BASELINE_LEFT);
                } else if(node instanceof TitledPane){
                    ((TitledPane) node).setContentDisplay(ContentDisplay.RIGHT);
                    ((TitledPane) node).setAlignment(Pos.BASELINE_RIGHT);
                    ((TitledPane) node).setCollapsible(true);
                } else {
                    break;
                }
            }
        }
    }

    private void addEvents(){
        VBox drawerContent;



        for (Node node : drawer.getChildren()) { // root
            if (node instanceof ScrollPane){

                drawerContent = (VBox) ((ScrollPane) node).getContent();

                for(Node child : drawerContent.getChildren()){
                    if(child instanceof Button){
                        child.setOnMouseEntered(e -> {
                            popup.setAutoHide(true);
                            if(popup.isShowing())
                                popup.hide();
                        });
                    }

                    else if(child instanceof TitledPane){
                        addEvent(child);
                    }
                }
            }

            else {
                // for others layouts
            }
        }
    }

    private void addSubPop() throws Exception {
        popup.setContentNode(FXMLLoader.load(getClass().getResource("/com/gn/module/main/Popover.fxml")));

//        popup.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());

        popup.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
        popup.setArrowIndent(0);
        popup.setArrowSize(0);
        popup.setCornerRadius(0);
        popup.setAutoFix(true);
    }

    private void addEvent(Node node) {
        popup.setDetached(false);
        popup.setDetachable(false);
        popup.setCloseButtonEnabled(false);
        popup.setArrowSize(0);
        popup.setHeaderAlwaysVisible(false);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStylesheets().add(getClass().getResource("/com/gn/theme/css/custom-scroll.css").toExternalForm());

        VBox v = new VBox();
        v.setPrefWidth(200);

        TitledPane pane = (TitledPane) node;
        VBox vbox = (VBox) pane.getContent();

        for (Node btn : vbox.getChildren()) {
            EventHandler event = ((Button) btn).getOnMouseClicked();
            String text = ((Button) btn).getText();
            Button button = new Button(text);
            button.setPrefWidth(v.getPrefWidth());
            button.setOnMouseClicked(e -> {
                body.setContent(ViewManager.getInstance().get(button.getText().toLowerCase()));
                title.setText(button.getText());
                popup.hide();
            });
            button.setMinHeight(40);
            v.getChildren().add(button);
        }

//        Popover.ctrl.options.getChildren().clear();

        node.setOnMouseEntered((Event e) -> {
            if (drawer.getPrefWidth() == 70) {
                Popover.ctrl.options.getChildren().setAll(v);
                popup.show(pane, -1);
            }
        });
    }

    private void barInitial(){
        filteredList.setPredicate(s -> true);
        scroll.setContent(views);
        ( (VBox) student.getContent()).getChildren().setAll(studentItems);
        ( (VBox) teacher.getContent()).getChildren().setAll(teacherItems);
        ( (VBox) grade.getContent()).getChildren().setAll(gradeItems);
        ( (VBox) system.getContent()).getChildren().setAll(systemItems);

        views.getChildren().removeAll(home);

        views.getChildren().add(home);

        home.setContentDisplay(ContentDisplay.LEFT);
        home.setAlignment(Pos.CENTER_LEFT);

        home.toBack();

        hamburger.setMouseTransparent(false);
    }

    private void barFiltered(String filter){
        views.getChildren().removeAll(home);

        filteredList.setPredicate(s -> s.getText().toUpperCase().contains(filter.toUpperCase()));
        scroll.setContent(filter(filteredList));

        hamburger.setMouseTransparent(true);
    }

    private VBox filter(ObservableList<Button>  nodes){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("drawer-content");
        vBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        vBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        for (Button node : nodes){
            if (node.getGraphic() != null) node.setContentDisplay(ContentDisplay.TEXT_ONLY);
            node.setAlignment(Pos.CENTER_LEFT);
        }
        vBox.getChildren().setAll(nodes);
        return vBox;
    }

    private void populateItems() {

        for (Node node : views.getChildren()) {
            if (node instanceof Button) {
                items.add( (Button) node);
            }
        }

        for (Node node : ((VBox) teacher.getContent()).getChildren()) {
            teacherItems.add((Button) node);
            items.add((Button) node);
        }

        for (Node node : ((VBox) student.getContent()).getChildren()) {
            studentItems.add((Button) node);
            items.add((Button) node);
        }

        for (Node node : ((VBox) grade.getContent()).getChildren()) {
            gradeItems.add((Button) node);
            items.add((Button) node);
        }

        for (Node node : ((VBox) system.getContent()).getChildren()) {
            systemItems.add((Button) node);
            items.add((Button) node);
        }
    }

    private void loadContentPopup(){
        try {
            popContent = FXMLLoader.load(getClass().getResource("/com/gn/module/main/Config.fxml"));
            popConfig.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());
            popConfig.setContentNode(popContent);
            popConfig.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
            popConfig.setArrowIndent(0);
            popConfig.setArrowSize(0);
            popConfig.setCornerRadius(0);
            popConfig.setAutoFix(true);
            popConfig.setAnimated(false);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openConfig(){
        if(popConfig.isShowing()){
            popConfig.hide();
        } else {
            popConfig.show(config, 0);
            popConfig.getRoot().setFocusTraversable(true);
        }
    }

    @FXML
    private void clearText(){
        search.clear();
    }

    private PopOver pop = new PopOver();

    @FXML
    private void dashboard(){
        title.setText("Dashboard");
        body.setContent(ViewManager.getInstance().get("dashboard"));
    }

    @FXML
    private void studentTable(){
        title.setText("学生信息表");
        body.setContent(ViewManager.getInstance().get("studenttable"));
    }

    @FXML
    private void createStudent(){
        title.setText("创建学生");
        body.setContent(ViewManager.getInstance().get("createstudent"));
    }

    @FXML
    private void courseTable(){
        title.setText("课程信息表");
        body.setContent(ViewManager.getInstance().get("coursetable"));
    }

    @FXML
    private void gradeTable(){
        title.setText("选课（成绩）信息表");
        body.setContent(ViewManager.getInstance().get("gradetable"));
    }

    @FXML
    private void chooseCourse(){
        title.setText( "选课" );
        body.setContent( ViewManager.getInstance().get("choosecourse") );
    }

    @FXML
    private void addGrade(){
        title.setText( "添加成绩" );
        body.setContent( ViewManager.getInstance().get("addgrade") );
    }

    @FXML
    private void studentGradeTable(){
        title.setText( "成绩查询" );
        body.setContent( ViewManager.getInstance().get( "studentgradetable" ) );
    }

    @FXML
    private void createCourse(){
        title.setText( "添加课程" );
        body.setContent( ViewManager.getInstance().get( "createcourse" ) );
    }

    @FXML
    private void createTeacher(){
        title.setText( "创建教师" );
        body.setContent( ViewManager.getInstance().get( "createteacher" ) );
    }

    @FXML
    private void teacherTable(){
        title.setText( "教师表" );
        body.setContent( ViewManager.getInstance().get( "teachertable" ) );
    }

    @FXML
    private void termReportOfStudent(){
        title.setText( "学生学期成绩" );
        body.setContent( ViewManager.getInstance().get( "termreportofstudent" ) );
    }

    @FXML
    private void annualReportOfStudent(){
        title.setText( "学生学年成绩" );
        body.setContent( ViewManager.getInstance().get( "annualreportofstudent" ) );
    }

    @FXML
    private void courseSummary(){
        title.setText( "学期学科总结" );
        body.setContent( ViewManager.getInstance().get( "coursesummary" ) );
    }

    @FXML
    private void gradeReportOfCourse(){
        title.setText( "学科学期成绩单" );
        body.setContent( ViewManager.getInstance().get( "gradereportofcourse" ) );
    }

    @FXML
    private void userTable(){
        title.setText( "用户信息表" );
        body.setContent( ViewManager.getInstance().get( "usertable" ) );
    }

    @FXML
    private void backup(){
        title.setText( "备份数据" );
        body.setContent( ViewManager.getInstance().get( "backup" ) );
    }

    @FXML
    private void recover(){
        title.setText( "恢复数据" );
        body.setContent( ViewManager.getInstance().get( "recover" ) );
    }
}
