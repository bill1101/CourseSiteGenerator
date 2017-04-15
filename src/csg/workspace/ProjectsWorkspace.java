/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.CSGeneratorProp;
import csg.data.CSData;
import csg.data.Student;
import csg.data.Team;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
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
public class ProjectsWorkspace {
    CSGeneratorApp app;
    Tab projectsTab;
    
    Label projectsHeader;
    
    VBox teamsPane;
    HBox teamsHeaderPane;
    Label teamsHeaderLabel;
    Button removeTeamsButton;
    TableView<Team> teamsTable;
    TableColumn<Team, String> nameColumn;
    TableColumn<Team, String> colorColumn;
    TableColumn<Team, String> textColorColumn;
    TableColumn<Team, String> linkColumn;
    GridPane teamsAddEditPane;
    Label teamsAddEditHeader;
    Label nameLabel;
    TextField nameTextField;
    Label colorLabel;
    ColorPicker colorPicker;
    Label textColorPickerLabel;
    ColorPicker textColorPicker;
    Label linkLabel;
    TextField linkTextField;
    Button teamsAddEditButton;
    Button teamsClearButton;
    
    VBox studentsPane;
    HBox studentsHeaderPane;
    Label studentsHeaderLabel;
    Button removeStudentsButton;
    TableView<Student> studentsTable;
    TableColumn<Student, String> firstNameColumn;
    TableColumn<Student, String> lastNameColumn;
    TableColumn<Student, String> teamColumn;
    TableColumn<Student, String> roleColumn;
    GridPane studentsAddEditPane;
    Label studentsAddEditHeader;
    Label firstNameLabel;
    TextField firstNameTextField;
    Label lastNameLabel;
    TextField lastNameTextField;
    Label teamLabel;
    ComboBox teamComboBox;
    Label roleLabel;
    TextField roleTextField;
    Button studentsAddEditButton;
    Button studentsClear;
        
    VBox projectsContent;
    
    public ProjectsWorkspace(CSGeneratorApp initApp) {
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        projectsTab = new Tab();
        projectsTab.setText(props.getProperty(CSGeneratorProp.TAB_TITLE_PROJECT_DATA.toString()));
        projectsTab.setClosable(false);

        projectsHeader = new Label(props.getProperty(CSGeneratorProp.PROJECTS_HEADER.toString()));
        
        teamsHeaderLabel = new Label(props.getProperty(CSGeneratorProp.TEAMS_HEADER_LABEL.toString()));
        removeTeamsButton = new Button(props.getProperty(CSGeneratorProp.REMOVE_TEAMS_BUTTON_TEXT.toString()));
        teamsHeaderPane = new HBox(20);
        teamsHeaderPane.getChildren().addAll(teamsHeaderLabel,removeTeamsButton);
        teamsTable = new TableView<>();
        CSData data = (CSData) app.getDataComponent();
        ObservableList<Team> tableData = data.getTeams();
        teamsTable.setItems(tableData);
        nameColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.PROJECT_NAME_COLUMN_TEXT.toString()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
        colorColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.COLOR_COLUMN_TEXT.toString()));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("color"));
        textColorColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.TEXT_COLOR_COLUMN_TEXT.toString()));
        textColorColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("textColor"));
        linkColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.TEAM_LINK_TEXT.toString()));
        linkColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("link"));
        teamsTable.getColumns().add(nameColumn);
        teamsTable.getColumns().add(colorColumn);
        teamsTable.getColumns().add(textColorColumn);
        teamsTable.getColumns().add(linkColumn);
        teamsTable.setMaxHeight(150);
        teamsAddEditHeader = new Label(props.getProperty(CSGeneratorProp.TEAMS_ADD_EDIT_HEADER.toString()));
        nameLabel = new Label(props.getProperty(CSGeneratorProp.NAME_TEXT.toString()));
        nameTextField = new TextField();
        colorLabel = new Label(props.getProperty(CSGeneratorProp.COLOR_TEXT.toString()));
        colorPicker = new ColorPicker();
        colorPicker.setMinHeight(25);
        textColorPickerLabel = new Label(props.getProperty(CSGeneratorProp.TEXT_COLOR_TEXT.toString()));
        textColorPicker = new ColorPicker();
        linkLabel = new Label(props.getProperty(CSGeneratorProp.TEAM_LINK_TEXT.toString()));
        linkTextField = new TextField();
        teamsAddEditButton = new Button(props.getProperty(CSGeneratorProp.ADD_TEAM_BUTTON_TEXT.toString()));
        teamsClearButton = new Button(props.getProperty(CSGeneratorProp.TEAM_CLEAR_BUTTON_TEXT.toString()));
        teamsAddEditPane = new GridPane();
        teamsAddEditPane.add(teamsAddEditHeader, 0, 0);
        teamsAddEditPane.add(nameLabel, 0, 1);
        teamsAddEditPane.add(nameTextField, 1, 1);
        teamsAddEditPane.add(colorLabel, 0, 2);
        teamsAddEditPane.add(colorPicker, 1, 2);
        teamsAddEditPane.add(textColorPickerLabel, 2, 2);
        teamsAddEditPane.add(textColorPicker, 3, 2);
        teamsAddEditPane.add(linkLabel, 0, 3);
        teamsAddEditPane.add(linkTextField, 1, 3, 3, 1);
        teamsAddEditPane.add(teamsAddEditButton, 0, 4);
        teamsAddEditPane.add(teamsClearButton, 1, 4);
        teamsPane = new VBox(8);
        teamsPane.getChildren().addAll(teamsHeaderPane,teamsTable,teamsAddEditPane);

        studentsHeaderLabel = new Label(props.getProperty(CSGeneratorProp.STUDENTS_HEADER.toString()));
        removeStudentsButton = new Button(props.getProperty(CSGeneratorProp.REMOVE_STUDENTS_BUTTON_TEXT.toString()));
        studentsHeaderPane = new HBox(20);
        studentsHeaderPane.getChildren().addAll(studentsHeaderLabel,removeStudentsButton);
        studentsTable = new TableView();
        studentsTable.setMaxHeight(150);
        ObservableList<Student> tableData1 = data.getStudents();
        studentsTable.setItems(tableData1);
        firstNameColumn = new TableColumn(props.getProperty(CSGeneratorProp.FIRSTNAME_COLUMN_TEXT.toString()));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn = new TableColumn(props.getProperty(CSGeneratorProp.LASTNAME_COLUMN_TEXT.toString()));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        teamColumn = new TableColumn(props.getProperty(CSGeneratorProp.TEAM_COLUMN_TEXT.toString()));
        teamColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("team"));
        roleColumn = new TableColumn(props.getProperty(CSGeneratorProp.ROLE_COLUMN_TEXT.toString()));
        roleColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("role"));
        studentsTable.getColumns().add(firstNameColumn);
        studentsTable.getColumns().add(lastNameColumn);
        studentsTable.getColumns().add(teamColumn);
        studentsTable.getColumns().add(roleColumn);

        studentsAddEditHeader = new Label(props.getProperty(CSGeneratorProp.STUDENT_ADD_EDIT_HEADER.toString()));
        firstNameLabel = new Label(props.getProperty(CSGeneratorProp.FIRSTNAME_LABEL.toString()));
        firstNameTextField = new TextField();
        lastNameLabel = new Label(props.getProperty(CSGeneratorProp.LASTNAME_LABEL.toString()));
        lastNameTextField = new TextField();
        teamLabel = new Label(props.getProperty(CSGeneratorProp.TEAM_LABEL.toString()));
        teamComboBox = new ComboBox();
        roleLabel = new Label(props.getProperty(CSGeneratorProp.ROLE_LABEL.toString()));
        roleTextField = new TextField();
        studentsAddEditButton = new Button(props.getProperty(CSGeneratorProp.STUDENT_ADD_BUTTON_TEXT.toString()));
        studentsClear = new Button(props.getProperty(CSGeneratorProp.STUDENT_CLEAR_BUTTON_TEXT.toString()));
        studentsAddEditPane = new GridPane();
        studentsAddEditPane.add(studentsAddEditHeader, 0, 0);
        studentsAddEditPane.add(firstNameLabel, 0, 1);
        studentsAddEditPane.add(firstNameTextField, 1, 1);
        studentsAddEditPane.add(lastNameLabel, 0, 2);
        studentsAddEditPane.add(lastNameTextField, 1, 2);
        studentsAddEditPane.add(teamLabel, 0, 3);
        studentsAddEditPane.add(teamComboBox, 1, 3);
        studentsAddEditPane.add(roleLabel, 0, 4);
        studentsAddEditPane.add(roleTextField, 1, 4);
        studentsAddEditPane.add(studentsAddEditButton, 0, 5);
        studentsAddEditPane.add(studentsClear, 1, 5);
        studentsPane = new VBox(8);
        studentsPane.getChildren().addAll(studentsHeaderPane, studentsTable,studentsAddEditPane);
        
        projectsContent = new VBox(10);
        projectsContent.getChildren().addAll(projectsHeader, teamsPane, studentsPane);
        projectsContent.setStyle("-fx-padding: 20;");
        projectsTab.setContent(projectsContent);
    }

    public Tab getProjectsTab() {
        return projectsTab;
    }

    public CSGeneratorApp getApp() {
        return app;
    }

    public void setApp(CSGeneratorApp app) {
        this.app = app;
    }

    public Label getProjectsHeader() {
        return projectsHeader;
    }

    public void setProjectsHeader(Label projectsHeader) {
        this.projectsHeader = projectsHeader;
    }

    public VBox getTeamsPane() {
        return teamsPane;
    }

    public void setTeamsPane(VBox teamsPane) {
        this.teamsPane = teamsPane;
    }

    public HBox getTeamsHeaderPane() {
        return teamsHeaderPane;
    }

    public void setTeamsHeaderPane(HBox teamsHeaderPane) {
        this.teamsHeaderPane = teamsHeaderPane;
    }

    public Label getTeamsHeaderLabel() {
        return teamsHeaderLabel;
    }

    public void setTeamsHeaderLabel(Label teamsHeaderLabel) {
        this.teamsHeaderLabel = teamsHeaderLabel;
    }

    public Button getRemoveTeamsButton() {
        return removeTeamsButton;
    }

    public void setRemoveTeamsButton(Button removeTeamsButton) {
        this.removeTeamsButton = removeTeamsButton;
    }

    public TableView<Team> getTeamsTable() {
        return teamsTable;
    }

    public void setTeamsTable(TableView<Team> teamsTable) {
        this.teamsTable = teamsTable;
    }

    public TableColumn<Team, String> getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(TableColumn<Team, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public TableColumn<Team, String> getColorColumn() {
        return colorColumn;
    }

    public void setColorColumn(TableColumn<Team, String> colorColumn) {
        this.colorColumn = colorColumn;
    }

    public TableColumn<Team, String> getTextColorColumn() {
        return textColorColumn;
    }

    public void setTextColorColumn(TableColumn<Team, String> textColorColumn) {
        this.textColorColumn = textColorColumn;
    }

    public TableColumn<Team, String> getLinkColumn() {
        return linkColumn;
    }

    public void setLinkColumn(TableColumn<Team, String> linkColumn) {
        this.linkColumn = linkColumn;
    }

    public GridPane getTeamsAddEditPane() {
        return teamsAddEditPane;
    }

    public void setTeamsAddEditPane(GridPane teamsAddEditPane) {
        this.teamsAddEditPane = teamsAddEditPane;
    }

    public Label getTeamsAddEditHeader() {
        return teamsAddEditHeader;
    }

    public void setTeamsAddEditHeader(Label teamsAddEditHeader) {
        this.teamsAddEditHeader = teamsAddEditHeader;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public Label getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(Label colorLabel) {
        this.colorLabel = colorLabel;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public Label getTextColorPickerLabel() {
        return textColorPickerLabel;
    }

    public void setTextColorPickerLabel(Label textColorPickerLabel) {
        this.textColorPickerLabel = textColorPickerLabel;
    }

    public ColorPicker getTextColorPicker() {
        return textColorPicker;
    }

    public void setTextColorPicker(ColorPicker textColorPicker) {
        this.textColorPicker = textColorPicker;
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

    public Button getTeamsAddEditButton() {
        return teamsAddEditButton;
    }

    public void setTeamsAddEditButton(Button teamsAddEditButton) {
        this.teamsAddEditButton = teamsAddEditButton;
    }

    public Button getTeamsClearButton() {
        return teamsClearButton;
    }

    public void setTeamsClearButton(Button teamsClearButton) {
        this.teamsClearButton = teamsClearButton;
    }

    public VBox getStudentsPane() {
        return studentsPane;
    }

    public void setStudentsPane(VBox studentsPane) {
        this.studentsPane = studentsPane;
    }

    public HBox getStudentsHeaderPane() {
        return studentsHeaderPane;
    }

    public void setStudentsHeaderPane(HBox studentsHeaderPane) {
        this.studentsHeaderPane = studentsHeaderPane;
    }

    public Label getStudentsHeaderLabel() {
        return studentsHeaderLabel;
    }

    public void setStudentsHeaderLabel(Label studentsHeaderLabel) {
        this.studentsHeaderLabel = studentsHeaderLabel;
    }

    public Button getRemoveStudentsButton() {
        return removeStudentsButton;
    }

    public void setRemoveStudentsButton(Button removeStudentsButton) {
        this.removeStudentsButton = removeStudentsButton;
    }

    public TableView<Student> getStudentsTable() {
        return studentsTable;
    }

    public void setStudentsTable(TableView<Student> studentsTable) {
        this.studentsTable = studentsTable;
    }

    public TableColumn<Student, String> getFirstNameColumn() {
        return firstNameColumn;
    }

    public void setFirstNameColumn(TableColumn<Student, String> firstNameColumn) {
        this.firstNameColumn = firstNameColumn;
    }

    public TableColumn<Student, String> getLastNameColumn() {
        return lastNameColumn;
    }

    public void setLastNameColumn(TableColumn<Student, String> lastNameColumn) {
        this.lastNameColumn = lastNameColumn;
    }

    public TableColumn<Student, String> getTeamColumn() {
        return teamColumn;
    }

    public void setTeamColumn(TableColumn<Student, String> teamColumn) {
        this.teamColumn = teamColumn;
    }

    public TableColumn<Student, String> getRoleColumn() {
        return roleColumn;
    }

    public void setRoleColumn(TableColumn<Student, String> roleColumn) {
        this.roleColumn = roleColumn;
    }

    public GridPane getStudentsAddEditPane() {
        return studentsAddEditPane;
    }

    public void setStudentsAddEditPane(GridPane studentsAddEditPane) {
        this.studentsAddEditPane = studentsAddEditPane;
    }

    public Label getStudentsAddEditHeader() {
        return studentsAddEditHeader;
    }

    public void setStudentsAddEditHeader(Label studentsAddEditHeader) {
        this.studentsAddEditHeader = studentsAddEditHeader;
    }

    public Label getFirstNameLabel() {
        return firstNameLabel;
    }

    public void setFirstNameLabel(Label firstNameLabel) {
        this.firstNameLabel = firstNameLabel;
    }

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(TextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public Label getLastNameLabel() {
        return lastNameLabel;
    }

    public void setLastNameLabel(Label lastNameLabel) {
        this.lastNameLabel = lastNameLabel;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(TextField lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
    }

    public Label getTeamLabel() {
        return teamLabel;
    }

    public void setTeamLabel(Label teamLabel) {
        this.teamLabel = teamLabel;
    }

    public ComboBox getTeamComboBox() {
        return teamComboBox;
    }

    public void setTeamComboBox(ComboBox teamComboBox) {
        this.teamComboBox = teamComboBox;
    }

    public Label getRoleLabel() {
        return roleLabel;
    }

    public void setRoleLabel(Label roleLabel) {
        this.roleLabel = roleLabel;
    }

    public TextField getRoleTextField() {
        return roleTextField;
    }

    public void setRoleTextField(TextField roleTextField) {
        this.roleTextField = roleTextField;
    }

    public Button getStudentsAddEditButton() {
        return studentsAddEditButton;
    }

    public void setStudentsAddEditButton(Button studentsAddEditButton) {
        this.studentsAddEditButton = studentsAddEditButton;
    }

    public Button getStudentsClear() {
        return studentsClear;
    }

    public void setStudentsClear(Button studentsClear) {
        this.studentsClear = studentsClear;
    }

    public VBox getProjectsContent() {
        return projectsContent;
    }

    public void setProjectsContent(VBox projectsContent) {
        this.projectsContent = projectsContent;
    }
    
    
        
    
}
