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
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class RecitationWorkspace {
    CSGeneratorApp app;
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
    TextField TA1TextField;
    Label TA2Label;
    TextField TA2textField;
    
    Button recitationAddEditButton;
    Button recitationClear;
            
    public RecitationWorkspace(CSGeneratorApp initApp){
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        recitationTab = new Tab();
        recitationTab.setText(props.getProperty(CSGeneratorProp.TAB_TITLE_RECITATION_DATA.toString()));
        recitationTab.setClosable(false);
         
        recitationHeader = new Label(props.getProperty(CSGeneratorProp.RECITATION_HEADER_TEXT));
        recitationDeleteButton = new Button(props.getProperty(CSGeneratorProp.RECITATION_DELETE_BUTTON_TEXT));
        recitationHeaderPane = new HBox();
        recitationHeaderPane.getChildren().addAll(recitationHeader,recitationDeleteButton);    
        recitationsTable = new TableView();
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
        TA1TextField = new TextField();
        TA2Label = new Label(props.getProperty(CSGeneratorProp.TA2_TEXT.toString()));
        TA2textField = new TextField();
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
        recitationAddEditPane.add(TA1TextField, 1, 5);
        recitationAddEditPane.add(TA2Label, 0, 6);
        recitationAddEditPane.add(TA2textField, 1, 6);
        recitationAddEditPane.add(recitationAddEditButton, 0, 7);
        recitationAddEditPane.add(recitationClear, 1, 7);
        //recitationAddEditPositionPane = new VBox(10);
        //recitationAddEditPositionPane.getChildren().add(recitationAddEditPane);
        
        recitationContent = new VBox(20);
        recitationContent.setStyle("-fx-padding: 20;");
        recitationContent.getChildren().addAll(recitationHeaderPane,recitationsTable,recitationAddEditPane);
        recitationTab.setContent(recitationContent);
    }

    public Tab getRecitationTab() {
        return recitationTab;
    }
    
    
}
