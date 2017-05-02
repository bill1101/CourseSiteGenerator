/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.CSGeneratorProp;
import csg.data.CSData;
import csg.data.Recitation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author tyx
 */
public class RecitationWorkspace {
    CSGeneratorApp app;
    RecitationController controller;

    
    Tab recitationTab;
    
    VBox recitationContent;
    
    //VBox recitationPositionPane;
    HBox recitationHeaderPane;
    Label recitationHeader;
    Button recitationDeleteButton;
    TableView<Recitation> recitationsTable;
    TableColumn<Recitation, String> sectionColumn;
    TableColumn<Recitation, String> instructorColumn;
    TableColumn<Recitation, String> dayTimeColumn;
    TableColumn<Recitation, String> locationColumn;
    TableColumn<Recitation, String> TA1Column;
    TableColumn<Recitation, String> TA2Column;
    
    //VBox recitationAddEditPositionPane;
    GridPane recitationAddEditPane;
    Label recitationAddEditHeader;
    Label sectionLabel;
    TextField sectionTextField;
    Label instructorLabel;
    TextField instructorTextField;
    Label dayTimeLabel;
    TextField dayTimeTextField;
    Label locationLabel;
    TextField locationTextField;
    Label TA1Label;
    ComboBox TA1ComboBox;
    Label TA2Label;
    ComboBox TA2ComboBox;
    
    Button recitationAddEditButton;
    Button recitationClear;
    ObservableList<String> options_TA;
    boolean isAdd;
    String initSection;
    String initInstructor;
    String initDayTime;
    String initLocation;
    String initTA1;
    String initTA2;
    public RecitationWorkspace(CSGeneratorApp initApp){
        isAdd = true;
        app = initApp;
        controller = new RecitationController(app);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        recitationTab = new Tab();
        recitationTab.setText(props.getProperty(CSGeneratorProp.TAB_TITLE_RECITATION_DATA.toString()));
        recitationTab.setClosable(false);
         
        recitationHeader = new Label(props.getProperty(CSGeneratorProp.RECITATION_HEADER_TEXT));
        recitationDeleteButton = new Button(props.getProperty(CSGeneratorProp.RECITATION_DELETE_BUTTON_TEXT));
        recitationHeaderPane = new HBox(20);
        recitationHeaderPane.getChildren().addAll(recitationHeader,recitationDeleteButton);    
        recitationsTable = new TableView();
        recitationsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSData data = (CSData) app.getDataComponent();
        ObservableList<Recitation> tableData = data.getRecitations();
        recitationsTable.setItems(tableData);
        sectionColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.SECTION_COLUMN_TEXT.toString()));
        sectionColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("section"));
        instructorColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.INSTRUCTOR_COLUMN_TEXT.toString()));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("instructor"));
        dayTimeColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.DAY_TIME_COLUMN_TEXT.toString()));
        dayTimeColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("dayTime"));
        locationColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.LOCATION_COLUMN_TEXT.toString()));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("location"));
        TA1Column = new TableColumn<>(props.getProperty(CSGeneratorProp.TA1_COLUMN_TEXT.toString()));
        TA1Column.setCellValueFactory(new PropertyValueFactory<Recitation, String>("TA1"));
        TA2Column = new TableColumn<>(props.getProperty(CSGeneratorProp.TA2_COLUMN_TEXT.toString()));
        TA2Column.setCellValueFactory(new PropertyValueFactory<Recitation, String>("TA2"));
        recitationsTable.getColumns().add(sectionColumn);
        recitationsTable.getColumns().add(instructorColumn);
        recitationsTable.getColumns().add(dayTimeColumn);
        recitationsTable.getColumns().add(locationColumn);
        recitationsTable.getColumns().add(TA1Column);
        recitationsTable.getColumns().add(TA2Column);
        
        
        //recitationPositionPane = new VBox();
        //recitationPositionPane.getChildren().addAll(recitationHeaderPane,recitationsTable);
    
        recitationAddEditPane = new GridPane();
        recitationAddEditHeader = new Label(props.getProperty(CSGeneratorProp.RECITATION_ADD_EDIT_HEADER_TEXT));
        sectionLabel = new Label(props.getProperty(CSGeneratorProp.SECTION_TEXT));
        sectionTextField = new TextField();
        instructorLabel = new Label(props.getProperty(CSGeneratorProp.INSTRUCTOR_TEXT.toString()));
        instructorTextField = new TextField();
        dayTimeLabel = new Label(props.getProperty(CSGeneratorProp.DAY_TIME_TEXT.toString()));
        dayTimeTextField = new TextField();
        locationLabel = new Label(props.getProperty(CSGeneratorProp.LOCATION_TEXT.toString()));
        locationTextField = new TextField();
        TA1Label = new Label(props.getProperty(CSGeneratorProp.TA1_TEXT.toString()));
        options_TA = FXCollections.observableArrayList();
        TA1ComboBox = new ComboBox(options_TA);       
        TA2Label = new Label(props.getProperty(CSGeneratorProp.TA2_TEXT.toString()));
        TA2ComboBox = new ComboBox(options_TA);
        recitationAddEditButton = new Button(props.getProperty(CSGeneratorProp.RECITATION_ADD_EDIT_BUTTON_TEXT.toString()));
        recitationClear = new Button(props.getProperty(CSGeneratorProp.RECITATION_CLEAR_BUTTON_TEXT.toString()));
        recitationAddEditPane.add(recitationAddEditHeader, 0, 0);
        recitationAddEditPane.add(sectionLabel, 0, 1);
        recitationAddEditPane.add(sectionTextField, 1, 1);
        recitationAddEditPane.add(instructorLabel, 0, 2);
        recitationAddEditPane.add(instructorTextField, 1, 2);
        recitationAddEditPane.add(dayTimeLabel, 0, 3);
        recitationAddEditPane.add(dayTimeTextField, 1, 3);
        recitationAddEditPane.add(locationLabel, 0, 4);
        recitationAddEditPane.add(locationTextField, 1, 4);
        recitationAddEditPane.add(TA1Label, 0, 5);
        recitationAddEditPane.add(TA1ComboBox, 1, 5);
        recitationAddEditPane.add(TA2Label, 0, 6);
        recitationAddEditPane.add(TA2ComboBox, 1, 6);
        recitationAddEditPane.add(recitationAddEditButton, 0, 7);
        recitationAddEditPane.add(recitationClear, 1, 7);
        //recitationAddEditPositionPane = new VBox(10);
        //recitationAddEditPositionPane.getChildren().add(recitationAddEditPane);
        
        recitationContent = new VBox(20);
        recitationContent.setStyle("-fx-padding: 20;");
        recitationContent.getChildren().addAll(recitationHeaderPane,recitationsTable,recitationAddEditPane);
        recitationTab.setContent(recitationContent);
        
        recitationsTable.setOnKeyPressed(e -> {
            controller.handleKeyPress(e.getCode());
        });
        recitationDeleteButton.setOnAction(e -> {
            controller.handleDeleteRecitation();
        });
        recitationContent.setOnKeyPressed(e -> {
            controller.handleKeyPress(e);
        });
        recitationAddEditButton.setOnAction(e -> {
            if(isAdd){
                controller.handleAddRecitation();
            }
            else{
                controller.handleEditRecitation(initSection,initInstructor,initDayTime,initLocation,initTA1,initTA2);
                isAdd = true;
                recitationsTable.refresh();
                recitationAddEditButton.setText(props.getProperty(CSGeneratorProp.RECITATION_ADD_EDIT_BUTTON_TEXT.toString()));
            }
        });
        recitationClear.setOnAction(e -> {
            sectionTextField.setText("");
            instructorTextField.setText("");
            dayTimeTextField.setText("");
            locationTextField.setText("");
            TA1ComboBox.setValue("");
            TA2ComboBox.setValue("");
            isAdd = true;
            recitationAddEditButton.setText(props.getProperty(CSGeneratorProp.RECITATION_ADD_EDIT_BUTTON_TEXT.toString()));
        });
        recitationsTable.setRowFactory(event -> {
            TableRow<Recitation> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (! row.isEmpty() && e.getButton()== MouseButton.PRIMARY) {
                    Recitation clickedRow = row.getItem();
                    sectionTextField.setText(clickedRow.getSection());
                    instructorTextField.setText(clickedRow.getInstructor());
                    dayTimeTextField.setText(clickedRow.getDayTime());
                    locationTextField.setText(clickedRow.getLocation());
                    TA1ComboBox.setValue(clickedRow.getTA1());
                    TA2ComboBox.setValue(clickedRow.getTA2());
                    recitationAddEditButton.setText(props.getProperty(CSGeneratorProp.UPDATE_BUTTON_TEXT.toString()));
                    isAdd = false;                   
                    initSection = clickedRow.getSection();
                    initInstructor = clickedRow.getInstructor();
                    initDayTime = clickedRow.getDayTime();
                    initLocation = clickedRow.getLocation();
                    initTA1 = clickedRow.getTA1();
                    initTA2 = clickedRow.getTA2();
                }
            });
            return row ;
        });
    }

    public ObservableList<String> getOptions_TA() {
        return options_TA;
    }

    public void setOptions_TA(ObservableList<String> options_TA) {
        this.options_TA = options_TA;
    }

    public Tab getRecitationTab() {
        return recitationTab;
    }

    public CSGeneratorApp getApp() {
        return app;
    }

    public void setApp(CSGeneratorApp app) {
        this.app = app;
    }

    public VBox getRecitationContent() {
        return recitationContent;
    }

    public void setRecitationContent(VBox recitationContent) {
        this.recitationContent = recitationContent;
    }

    public HBox getRecitationHeaderPane() {
        return recitationHeaderPane;
    }

    public void setRecitationHeaderPane(HBox recitationHeaderPane) {
        this.recitationHeaderPane = recitationHeaderPane;
    }

    public Label getRecitationHeader() {
        return recitationHeader;
    }

    public void setRecitationHeader(Label recitationHeader) {
        this.recitationHeader = recitationHeader;
    }

    public Button getRecitationDeleteButton() {
        return recitationDeleteButton;
    }

    public void setRecitationDeleteButton(Button recitationDeleteButton) {
        this.recitationDeleteButton = recitationDeleteButton;
    }

    public TableView<Recitation> getRecitationsTable() {
        return recitationsTable;
    }

    public void setRecitationsTable(TableView<Recitation> recitationsTable) {
        this.recitationsTable = recitationsTable;
    }

    public TableColumn<Recitation, String> getSectionColumn() {
        return sectionColumn;
    }

    public void setSectionColumn(TableColumn<Recitation, String> sectionColumn) {
        this.sectionColumn = sectionColumn;
    }

    public TableColumn<Recitation, String> getInstructorColumn() {
        return instructorColumn;
    }

    public void setInstructorColumn(TableColumn<Recitation, String> instructorColumn) {
        this.instructorColumn = instructorColumn;
    }

    public TableColumn<Recitation, String> getDayTimeColumn() {
        return dayTimeColumn;
    }

    public void setDayTimeColumn(TableColumn<Recitation, String> dayTimeColumn) {
        this.dayTimeColumn = dayTimeColumn;
    }

    public TableColumn<Recitation, String> getLocationColumn() {
        return locationColumn;
    }

    public void setLocationColumn(TableColumn<Recitation, String> locationColumn) {
        this.locationColumn = locationColumn;
    }

    public TableColumn<Recitation, String> getTA1Column() {
        return TA1Column;
    }

    public void setTA1Column(TableColumn<Recitation, String> TA1Column) {
        this.TA1Column = TA1Column;
    }

    public TableColumn<Recitation, String> getTA2Column() {
        return TA2Column;
    }

    public void setTA2Column(TableColumn<Recitation, String> TA2Column) {
        this.TA2Column = TA2Column;
    }

    public GridPane getRecitationAddEditPane() {
        return recitationAddEditPane;
    }

    public void setRecitationAddEditPane(GridPane recitationAddEditPane) {
        this.recitationAddEditPane = recitationAddEditPane;
    }

    public Label getRecitationAddEditHeader() {
        return recitationAddEditHeader;
    }

    public void setRecitationAddEditHeader(Label recitationAddEditHeader) {
        this.recitationAddEditHeader = recitationAddEditHeader;
    }

    public Label getSectionLabel() {
        return sectionLabel;
    }

    public void setSectionLabel(Label sectionLabel) {
        this.sectionLabel = sectionLabel;
    }

    public TextField getSectionTextField() {
        return sectionTextField;
    }

    public void setSectionTextField(TextField sectionTextField) {
        this.sectionTextField = sectionTextField;
    }

    public Label getInstructorLabel() {
        return instructorLabel;
    }

    public void setInstructorLabel(Label instructorLabel) {
        this.instructorLabel = instructorLabel;
    }

    public TextField getInstructorTextField() {
        return instructorTextField;
    }

    public void setInstructorTextField(TextField instructorTextField) {
        this.instructorTextField = instructorTextField;
    }

    public Label getDayTimeLabel() {
        return dayTimeLabel;
    }

    public void setDayTimeLabel(Label dayTimeLabel) {
        this.dayTimeLabel = dayTimeLabel;
    }

    public TextField getDayTimeTextField() {
        return dayTimeTextField;
    }

    public void setDayTimeTextField(TextField dayTimeTextField) {
        this.dayTimeTextField = dayTimeTextField;
    }

    public Label getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(Label locationLabel) {
        this.locationLabel = locationLabel;
    }

    public TextField getLocationTextField() {
        return locationTextField;
    }

    public void setLocationTextField(TextField locationTextField) {
        this.locationTextField = locationTextField;
    }

    public Label getTA1Label() {
        return TA1Label;
    }

    public void setTA1Label(Label TA1Label) {
        this.TA1Label = TA1Label;
    }

    public ComboBox getTA1ComboBox() {
        return TA1ComboBox;
    }

    public void setTA1ComboBox(ComboBox TA1ComboBox) {
        this.TA1ComboBox = TA1ComboBox;
    }

    public Label getTA2Label() {
        return TA2Label;
    }

    public void setTA2Label(Label TA2Label) {
        this.TA2Label = TA2Label;
    }

    public ComboBox getTA2ComboBox() {
        return TA2ComboBox;
    }

    public void setTA2ComboBox(ComboBox TA2ComboBox) {
        this.TA2ComboBox = TA2ComboBox;
    }

    public Button getRecitationAddEditButton() {
        return recitationAddEditButton;
    }

    public void setRecitationAddEditButton(Button recitationAddEditButton) {
        this.recitationAddEditButton = recitationAddEditButton;
    }

    public Button getRecitationClear() {
        return recitationClear;
    }

    public void setRecitationClear(Button recitationClear) {
        this.recitationClear = recitationClear;
    }
    
    public RecitationController getController() {
        return controller;
    }

    public void setController(RecitationController controller) {
        this.controller = controller;
    }
}
