/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.CSGeneratorProp;
import csg.data.CSData;
import csg.data.ScheduleItem;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author tyx
 */
public class ScheduleWorkspace {
    CSGeneratorApp app;
    Tab scheduleTab;

    HBox scheduleHeaderPane;
    Label scheduleHeaderLabel;
    
    VBox calendarBoundariesPane;
    Label calendarBoundariesHeader;
    HBox calendarBoundariesDate;
    Label startingMondayLabel;
    DatePicker startingMondayDatePicker;
    Label endingFridayLabel;
    DatePicker endingFridayDatePicker;
    
    
    VBox scheduleItemsPane;
    HBox scheduleItemsHeaderPane;
    Label scheduleItemsHeader;
    Button removeSchedule;
    TableView<ScheduleItem> scheduleItemsTable;
    TableColumn<ScheduleItem, String> typeColumn;
    TableColumn<ScheduleItem, String> dateColumn;
    TableColumn<ScheduleItem, String> titleColumn;
    TableColumn<ScheduleItem, String> topicColumn;
    
    GridPane scheduleItemPane;
    Label scheduleItemsAddEditHeader;
    Label typeLabel;
    ComboBox typeComboBox;
    Label dateLabel;
    DatePicker dateDatePicker;
    Label timeLabel;
    TextField timeTextField;
    Label titleLabel;
    TextField titleTextField;
    Label topicLabel;
    TextField topicTextField;
    Label linkLabel;
    TextField linkTextField;
    Label criteriaLabel;
    TextField criteriaTextField;
    Button scheduleAddEditButton;
    Button scheduleClear;
    
    VBox scheduleContent;
    public ScheduleWorkspace(CSGeneratorApp initApp) {
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        scheduleTab = new Tab();
        scheduleTab.setText(props.getProperty(CSGeneratorProp.TAB_TITLE_SCHEDULE_DATA.toString()));
        scheduleTab.setClosable(false);
        
        
        scheduleHeaderLabel = new Label(props.getProperty(CSGeneratorProp.SCHEDULE_HEADER_TEXT.toString()));
        scheduleHeaderPane = new HBox();
        scheduleHeaderPane.getChildren().add(scheduleHeaderLabel);
        
        calendarBoundariesHeader = new Label(props.getProperty(CSGeneratorProp.CALENDAR_BOUNDARIES_HEADER.toString()));
        startingMondayLabel = new Label(props.getProperty(CSGeneratorProp.STARTING_MONDAY_LABEL.toString()));
        startingMondayDatePicker = new DatePicker();
        endingFridayLabel = new Label(props.getProperty(CSGeneratorProp.ENDING_FRIDAY_LABEL.toString()));
        endingFridayDatePicker = new DatePicker();
        calendarBoundariesDate = new HBox();
        calendarBoundariesDate.getChildren().addAll(startingMondayLabel,startingMondayDatePicker,endingFridayLabel,endingFridayDatePicker);
        calendarBoundariesPane = new VBox();
        calendarBoundariesPane.getChildren().addAll(calendarBoundariesHeader,calendarBoundariesDate);
        //VBox calendarBoundariesPositionPane = new VBox();
        //calendarBoundariesPositionPane.getChildren().add(calendarBoundariesPane);
        
        scheduleItemsHeader = new Label(props.getProperty(CSGeneratorProp.SCHEDULE_ITEMS_HEADER.toString()));
        removeSchedule = new Button(props.getProperty(CSGeneratorProp.REMOVE_SCHEDULE_BUTTON_TEXT.toString()));
        scheduleItemsHeaderPane = new HBox();
        scheduleItemsHeaderPane.getChildren().addAll(scheduleItemsHeader,removeSchedule);       
        scheduleItemsTable = new TableView<>();
        scheduleItemsTable .getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSData data = (CSData) app.getDataComponent();        
        ObservableList<ScheduleItem> tableData = data.getScheduleItems();
        scheduleItemsTable.setItems(tableData);
        typeColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.TYPE_COLUMN_TEXT.toString()));
        typeColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem, String>("type"));
        dateColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.DATE_COLUMN_TEXT.toString()));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem, String>("date"));
        titleColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.TITLE_COLUMN_TEXT.toString()));
        titleColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem, String>("title"));
        topicColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.TOPIC_COLUMN_TEXT));
        topicColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem, String>("topic"));
        scheduleItemsTable.getColumns().add(typeColumn);
        scheduleItemsTable.getColumns().add(dateColumn);
        scheduleItemsTable.getColumns().add(titleColumn);
        scheduleItemsTable.getColumns().add(topicColumn);
        scheduleItemsPane = new VBox();
               
        scheduleItemsAddEditHeader = new Label(props.getProperty(CSGeneratorProp.SCHEDULE_ITEMS_ADD_EDIT_HEADER.toString()));
        typeLabel = new Label(props.getProperty(CSGeneratorProp.TYPE_LABEL.toString()));
        typeComboBox = new ComboBox();
        dateLabel = new Label(props.getProperty(CSGeneratorProp.DATE_LABEL.toString()));
        dateDatePicker = new DatePicker();
        timeLabel = new Label(props.getProperty(CSGeneratorProp.TIME_TEXT.toString()));
        timeTextField = new TextField();
        titleLabel = new Label(props.getProperty(CSGeneratorProp.SCHEDULE_TITLE_TEXT.toString()));
        titleTextField = new TextField();
        topicLabel = new Label(props.getProperty(CSGeneratorProp.TOPIC_TEXT.toString()));
        topicTextField = new TextField();
        linkLabel = new Label(props.getProperty(CSGeneratorProp.LINK_TEXT.toString()));
        linkTextField = new TextField();
        criteriaLabel = new Label(props.getProperty(CSGeneratorProp.CRITERIA_TEXT.toString()));
        criteriaTextField = new TextField();
        scheduleAddEditButton = new Button(props.getProperty(CSGeneratorProp.SCHEDULE_ITEMS_ADD_EDIT_BUTTON_TEXT.toString()));
        scheduleClear = new Button(props.getProperty(CSGeneratorProp.SCHEDULE_CLEAR_BUTTON_TEXT.toString()));
        scheduleItemPane = new GridPane();
        scheduleItemPane.add(scheduleItemsAddEditHeader, 0, 0);
        scheduleItemPane.add(typeLabel, 0, 1);
        scheduleItemPane.add(typeComboBox, 1, 1);
        scheduleItemPane.add(dateLabel, 0, 2);
        scheduleItemPane.add(dateDatePicker, 1, 2);
        scheduleItemPane.add(timeLabel, 0, 3);
        scheduleItemPane.add(timeTextField, 1, 3);
        scheduleItemPane.add(titleLabel, 0, 4);
        scheduleItemPane.add(titleTextField, 1, 4);
        scheduleItemPane.add(topicLabel, 0, 5);
        scheduleItemPane.add(topicTextField, 1, 5);
        scheduleItemPane.add(linkLabel, 0, 6);
        scheduleItemPane.add(linkTextField, 1, 6);
        scheduleItemPane.add(criteriaLabel, 0, 7);
        scheduleItemPane.add(criteriaTextField, 1, 7);
        scheduleItemPane.add(scheduleAddEditButton, 0, 8);
        scheduleItemPane.add(scheduleClear, 1, 8);

        scheduleItemsPane.getChildren().addAll(scheduleItemsHeaderPane,scheduleItemsTable,scheduleItemPane);

        scheduleContent = new VBox();
        scheduleContent.setStyle("-fx-padding: 20;");
        scheduleContent.getChildren().addAll(scheduleHeaderPane,calendarBoundariesPane,scheduleItemsPane);
        scheduleTab.setContent(scheduleContent);
        
    }

    public Tab getScheduleTab() {
        return scheduleTab;
    }
       
}
