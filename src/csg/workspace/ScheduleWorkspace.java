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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
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
    ScheduleController controller;
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
        CSData data = (CSData)initApp.getDataComponent();
        controller = new ScheduleController(app);
        scheduleTab = new Tab();
        scheduleTab.setText(props.getProperty(CSGeneratorProp.TAB_TITLE_SCHEDULE_DATA.toString()));
        scheduleTab.setClosable(false);
        
        
        scheduleHeaderLabel = new Label(props.getProperty(CSGeneratorProp.SCHEDULE_HEADER_TEXT.toString()));
        scheduleHeaderPane = new HBox();
        scheduleHeaderPane.getChildren().add(scheduleHeaderLabel);
        
        calendarBoundariesHeader = new Label(props.getProperty(CSGeneratorProp.CALENDAR_BOUNDARIES_HEADER.toString()));
        startingMondayLabel = new Label(props.getProperty(CSGeneratorProp.STARTING_MONDAY_LABEL.toString()));
        startingMondayDatePicker = new DatePicker();
        startingMondayDatePicker.setEditable(true);
        startingMondayDatePicker.setOnAction(e->{
            controller.handleEditStarting();
        });
        endingFridayLabel = new Label(props.getProperty(CSGeneratorProp.ENDING_FRIDAY_LABEL.toString()));
        endingFridayDatePicker = new DatePicker();
        endingFridayDatePicker.setEditable(true);
        endingFridayDatePicker.setOnAction(e->{
            controller.handleEditEnding();
        });
        calendarBoundariesDate = new HBox(20);
        calendarBoundariesDate.getChildren().addAll(startingMondayLabel,startingMondayDatePicker,endingFridayLabel,endingFridayDatePicker);
        calendarBoundariesPane = new VBox(8);
        calendarBoundariesPane.getChildren().addAll(calendarBoundariesHeader,calendarBoundariesDate);
        //VBox calendarBoundariesPositionPane = new VBox();
        //calendarBoundariesPositionPane.getChildren().add(calendarBoundariesPane);
        
        scheduleItemsHeader = new Label(props.getProperty(CSGeneratorProp.SCHEDULE_ITEMS_HEADER.toString()));
        removeSchedule = new Button(props.getProperty(CSGeneratorProp.REMOVE_SCHEDULE_BUTTON_TEXT.toString()));
        scheduleItemsHeaderPane = new HBox(20);
        scheduleItemsHeaderPane.getChildren().addAll(scheduleItemsHeader,removeSchedule);        
        scheduleItemsTable = new TableView<>();
        scheduleItemsTable .getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //CSData data = (CSData) app.getDataComponent();        
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
        scheduleItemsPane = new VBox(8);
               
        scheduleItemsAddEditHeader = new Label(props.getProperty(CSGeneratorProp.SCHEDULE_ITEMS_ADD_EDIT_HEADER.toString()));
        typeLabel = new Label(props.getProperty(CSGeneratorProp.TYPE_LABEL.toString()));
        ObservableList<String> options_type = 
            FXCollections.observableArrayList(
                "Holiday",
                "Lecture",
                "HW",
                "Reference",
                "Recitation"              
            );
        typeComboBox = new ComboBox(options_type);
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
        scheduleItemPane.add(titleTextField, 1, 4, 2, 1);
        scheduleItemPane.add(topicLabel, 0, 5);
        scheduleItemPane.add(topicTextField, 1, 5 ,2, 1);
        scheduleItemPane.add(linkLabel, 0, 6);
        scheduleItemPane.add(linkTextField, 1, 6 ,2 ,1);
        scheduleItemPane.add(criteriaLabel, 0, 7);
        scheduleItemPane.add(criteriaTextField, 1, 7, 2, 1);
        scheduleItemPane.add(scheduleAddEditButton, 0, 8);
        scheduleItemPane.add(scheduleClear, 1, 8);

        scheduleItemsPane.getChildren().addAll(scheduleItemsHeaderPane,scheduleItemsTable,scheduleItemPane);

        scheduleContent = new VBox(10);
        scheduleContent.getChildren().addAll(scheduleHeaderPane,calendarBoundariesPane,scheduleItemsPane);
        scheduleTab.setContent(scheduleContent);
        
        scheduleContent.setOnKeyPressed(e -> {
            controller.handleKeyPress(e);
        });
    }

    public Tab getScheduleTab() {
        return scheduleTab;
    }

    public CSGeneratorApp getApp() {
        return app;
    }

    public void setApp(CSGeneratorApp app) {
        this.app = app;
    }

    public HBox getScheduleHeaderPane() {
        return scheduleHeaderPane;
    }

    public void setScheduleHeaderPane(HBox scheduleHeaderPane) {
        this.scheduleHeaderPane = scheduleHeaderPane;
    }

    public Label getScheduleHeaderLabel() {
        return scheduleHeaderLabel;
    }

    public void setScheduleHeaderLabel(Label scheduleHeaderLabel) {
        this.scheduleHeaderLabel = scheduleHeaderLabel;
    }

    public VBox getCalendarBoundariesPane() {
        return calendarBoundariesPane;
    }

    public void setCalendarBoundariesPane(VBox calendarBoundariesPane) {
        this.calendarBoundariesPane = calendarBoundariesPane;
    }

    public Label getCalendarBoundariesHeader() {
        return calendarBoundariesHeader;
    }

    public void setCalendarBoundariesHeader(Label calendarBoundariesHeader) {
        this.calendarBoundariesHeader = calendarBoundariesHeader;
    }

    public HBox getCalendarBoundariesDate() {
        return calendarBoundariesDate;
    }

    public void setCalendarBoundariesDate(HBox calendarBoundariesDate) {
        this.calendarBoundariesDate = calendarBoundariesDate;
    }

    public Label getStartingMondayLabel() {
        return startingMondayLabel;
    }

    public void setStartingMondayLabel(Label startingMondayLabel) {
        this.startingMondayLabel = startingMondayLabel;
    }

    public DatePicker getStartingMondayDatePicker() {
        return startingMondayDatePicker;
    }

    public void setStartingMondayDatePicker(DatePicker startingMondayDatePicker) {
        this.startingMondayDatePicker = startingMondayDatePicker;
    }

    public Label getEndingFridayLabel() {
        return endingFridayLabel;
    }

    public void setEndingFridayLabel(Label endingFridayLabel) {
        this.endingFridayLabel = endingFridayLabel;
    }

    public DatePicker getEndingFridayDatePicker() {
        return endingFridayDatePicker;
    }

    public void setEndingFridayDatePicker(DatePicker endingFridayDatePicker) {
        this.endingFridayDatePicker = endingFridayDatePicker;
    }

    public VBox getScheduleItemsPane() {
        return scheduleItemsPane;
    }

    public void setScheduleItemsPane(VBox scheduleItemsPane) {
        this.scheduleItemsPane = scheduleItemsPane;
    }

    public HBox getScheduleItemsHeaderPane() {
        return scheduleItemsHeaderPane;
    }

    public void setScheduleItemsHeaderPane(HBox scheduleItemsHeaderPane) {
        this.scheduleItemsHeaderPane = scheduleItemsHeaderPane;
    }

    public Label getScheduleItemsHeader() {
        return scheduleItemsHeader;
    }

    public void setScheduleItemsHeader(Label scheduleItemsHeader) {
        this.scheduleItemsHeader = scheduleItemsHeader;
    }

    public Button getRemoveSchedule() {
        return removeSchedule;
    }

    public void setRemoveSchedule(Button removeSchedule) {
        this.removeSchedule = removeSchedule;
    }

    public TableView<ScheduleItem> getScheduleItemsTable() {
        return scheduleItemsTable;
    }

    public void setScheduleItemsTable(TableView<ScheduleItem> scheduleItemsTable) {
        this.scheduleItemsTable = scheduleItemsTable;
    }

    public TableColumn<ScheduleItem, String> getTypeColumn() {
        return typeColumn;
    }

    public void setTypeColumn(TableColumn<ScheduleItem, String> typeColumn) {
        this.typeColumn = typeColumn;
    }

    public TableColumn<ScheduleItem, String> getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(TableColumn<ScheduleItem, String> dateColumn) {
        this.dateColumn = dateColumn;
    }

    public TableColumn<ScheduleItem, String> getTitleColumn() {
        return titleColumn;
    }

    public void setTitleColumn(TableColumn<ScheduleItem, String> titleColumn) {
        this.titleColumn = titleColumn;
    }

    public TableColumn<ScheduleItem, String> getTopicColumn() {
        return topicColumn;
    }

    public void setTopicColumn(TableColumn<ScheduleItem, String> topicColumn) {
        this.topicColumn = topicColumn;
    }

    public GridPane getScheduleItemPane() {
        return scheduleItemPane;
    }

    public void setScheduleItemPane(GridPane scheduleItemPane) {
        this.scheduleItemPane = scheduleItemPane;
    }

    public Label getScheduleItemsAddEditHeader() {
        return scheduleItemsAddEditHeader;
    }

    public void setScheduleItemsAddEditHeader(Label scheduleItemsAddEditHeader) {
        this.scheduleItemsAddEditHeader = scheduleItemsAddEditHeader;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(Label typeLabel) {
        this.typeLabel = typeLabel;
    }

    public ComboBox getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(ComboBox typeComboBox) {
        this.typeComboBox = typeComboBox;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public DatePicker getDateDatePicker() {
        return dateDatePicker;
    }

    public void setDateDatePicker(DatePicker dateDatePicker) {
        this.dateDatePicker = dateDatePicker;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public TextField getTimeTextField() {
        return timeTextField;
    }

    public void setTimeTextField(TextField timeTextField) {
        this.timeTextField = timeTextField;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    public TextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(TextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public Label getTopicLabel() {
        return topicLabel;
    }

    public void setTopicLabel(Label topicLabel) {
        this.topicLabel = topicLabel;
    }

    public TextField getTopicTextField() {
        return topicTextField;
    }

    public void setTopicTextField(TextField topicTextField) {
        this.topicTextField = topicTextField;
    }

    public Label getLinkLabel() {
        return linkLabel;
    }

    public void setLinkLabel(Label linkLabel) {
        this.linkLabel = linkLabel;
    }

    public TextField getLinkTextField() {
        return linkTextField;
    }

    public void setLinkTextField(TextField linkTextField) {
        this.linkTextField = linkTextField;
    }

    public Label getCriteriaLabel() {
        return criteriaLabel;
    }

    public void setCriteriaLabel(Label criteriaLabel) {
        this.criteriaLabel = criteriaLabel;
    }

    public TextField getCriteriaTextField() {
        return criteriaTextField;
    }

    public void setCriteriaTextField(TextField criteriaTextField) {
        this.criteriaTextField = criteriaTextField;
    }

    public Button getScheduleAddEditButton() {
        return scheduleAddEditButton;
    }

    public void setScheduleAddEditButton(Button scheduleAddEditButton) {
        this.scheduleAddEditButton = scheduleAddEditButton;
    }

    public Button getScheduleClear() {
        return scheduleClear;
    }

    public void setScheduleClear(Button scheduleClear) {
        this.scheduleClear = scheduleClear;
    }

    public VBox getScheduleContent() {
        return scheduleContent;
    }

    public void setScheduleContent(VBox scheduleContent) {
        this.scheduleContent = scheduleContent;
    }

    void reloadWorkspace(CSData csData) {
        if(csData.getStartingMonday()!=null){
            LocalDate start = LocalDate.parse(csData.getStartingMonday());
            startingMondayDatePicker.setValue(start);
        }
        if(csData.getEndingFriday()!=null){
            LocalDate end = LocalDate.parse(csData.getEndingFriday());
            endingFridayDatePicker.setValue(end);
        }
    }
       
    
    public ScheduleController getController() {
        return controller;
    }

    public void setController(ScheduleController controller) {
        this.controller = controller;
    }
}
