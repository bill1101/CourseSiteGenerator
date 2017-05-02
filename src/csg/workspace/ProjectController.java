/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import static csg.CSGeneratorProp.*;
import csg.data.CSData;
import csg.data.Student;
import csg.data.Team;
import csg.jtps.AddStudent_Transaction;
import csg.jtps.AddTeam_Transaction;
import csg.jtps.EditStudent_Transaction;
import csg.jtps.EditTeam_Transaction;
import csg.jtps.RemoveStudent_Transaction;
import csg.jtps.RemoveTeam_Transaction;
import csg.jtps.jTPS_Transaction;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import properties_manager.PropertiesManager;

/**
 *
 * @author tyx
 */
public class ProjectController {
    CSGeneratorApp app;
    
    ProjectController(CSGeneratorApp initApp) {
        app = initApp;
    }
    
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }
    
    public void handleAddTeam() {
        ProjectsWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent();
        
        TextField nameTextField = workspace.getNameTextField();
        ColorPicker colorColorPicker = workspace.getColorPicker();
        ColorPicker textColorColorPicker = workspace.getTextColorPicker();
        TextField linkTextField = workspace.getLinkTextField();
        String name = nameTextField.getText();
        String color = colorColorPicker.getValue()==null?"":colorColorPicker.getValue().toString();
        String textColor = textColorColorPicker.getValue()==null?"":textColorColorPicker.getValue().toString();
        String link = linkTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSData data = (CSData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(name.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_NAME_TITLE), props.getProperty(MISSING_TEAM_NAME_MESSAGE));
        }else if(color.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_COLOR_TITLE), props.getProperty(MISSING_TEAM_COLOR_MESSAGE));
        }else if(textColor.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_TEXTCOLOR_TITLE), props.getProperty(MISSING_TEAM_TEXTCOLOR_MESSAGE));
        }else if(link.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_LINK_TITLE), props.getProperty(MISSING_TEAM_LINK_MESSAGE));
        }else if(data.containsTeam(name)){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(TEAM_NOT_UNIQUE_TITLE), props.getProperty(TEAM_NOT_UNIQUE_MESSAGE));        
        }else{
            jTPS_Transaction addTeamTransaction = new AddTeam_Transaction(app,name,color,textColor,link);
            CSWorkspace.jTPS.addTransaction(addTeamTransaction);
            
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            linkTextField.setText("");
            colorColorPicker.setValue(Color.web("0xffffffff"));
            textColorColorPicker.setValue(Color.web("0xffffffff"));
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }
    
    public void handleKeyPress(KeyCode code) {
        if (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE) {
            handleDeleteTeam();
        }
    }

    public void handleDeleteTeam() {
        ProjectsWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent();
        TableView teamsTable = workspace.getTeamsTable();
            
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = teamsTable.getSelectionModel().getSelectedItem();
            //System.out.println(selectedItem);
        if (selectedItem != null) {
            // GET THE RECITATION AND REMOVE IT
            Team team = (Team)selectedItem;
            CSData data = (CSData)app.getDataComponent();

            jTPS_Transaction removeTransaction = new RemoveTeam_Transaction(app,team.getName(),team.getColor(),team.getTextColor(),team.getLink());
            CSWorkspace.jTPS.addTransaction(removeTransaction);                              
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }    
    }
    
    public void handleKeyPress(KeyEvent e) {
        
        final KeyCombination keyCombinationZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        final KeyCombination keyCombinationY = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
        if (keyCombinationZ.match(e)) {
            CSWorkspace.jTPS.undoTransaction();
        }
        else if(keyCombinationY.match(e)){
            CSWorkspace.jTPS.doTransaction();
        }
    }

    public void handleEditTeam(String initName, String initColor, String initTextColor, String initLink) {
        ProjectsWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent();
        
        TextField nameTextField = workspace.getNameTextField();
        ColorPicker colorColorPicker = workspace.getColorPicker();
        ColorPicker textColorColorPicker = workspace.getTextColorPicker();
        TextField linkTextField = workspace.getLinkTextField();
        String name = nameTextField.getText();
        String color = colorColorPicker.getValue()==null?"":colorColorPicker.getValue().toString();
        String textColor = textColorColorPicker.getValue()==null?"":textColorColorPicker.getValue().toString();
        String link = linkTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSData data = (CSData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(name.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_NAME_TITLE), props.getProperty(MISSING_TEAM_NAME_MESSAGE));
        }else if(color.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_COLOR_TITLE), props.getProperty(MISSING_TEAM_COLOR_MESSAGE));
        }else if(textColor.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_TEXTCOLOR_TITLE), props.getProperty(MISSING_TEAM_TEXTCOLOR_MESSAGE));
        }else if(link.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TEAM_LINK_TITLE), props.getProperty(MISSING_TEAM_LINK_MESSAGE));
        }else if(!name.equals(initName) && data.containsTeam(name)){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(TEAM_NOT_UNIQUE_TITLE), props.getProperty(TEAM_NOT_UNIQUE_MESSAGE));        
        }else{
            jTPS_Transaction editTeamTransaction = new EditTeam_Transaction(app,initName,initColor,initTextColor,initLink,
                name,color,textColor,link);
            CSWorkspace.jTPS.addTransaction(editTeamTransaction);
            
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            linkTextField.setText("");
            colorColorPicker.setValue(Color.web("0xffffffff"));
            textColorColorPicker.setValue(Color.web("0xffffffff"));
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }

    public void handleAddStudent() {
        ProjectsWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent();
        
        TextField firstNameTextField = workspace.getFirstNameTextField();
        TextField lasNameTextField = workspace.getLastNameTextField();
        ComboBox teamComboBox = workspace.getTeamComboBox();
        TextField roleTextField = workspace.getRoleTextField();
        
        String firstName = firstNameTextField.getText();
        String lastName = lasNameTextField.getText();
        String team = teamComboBox.getValue()==null?"":teamComboBox.getValue().toString();
        String role = roleTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSData data = (CSData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(firstName.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_STUDENT_FIRSTNAME_TITLE), props.getProperty(MISSING_STUDENT_FIRSTNAME_MESSAGE));
        }else if(lastName.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_STUDENT_LASTNAME_TITLE), props.getProperty(MISSING_STUDENT_LASTNAME_MESSAGE));       
        }else if(team.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_STUDENT_TEAM_TITLE), props.getProperty(MISSING_STUDENT_TEAM_MESSAGE));               
        }else if(role.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_STUDENT_ROLE_TITLE), props.getProperty(MISSING_STUDENT_ROLE_MESSAGE));
        }else if(data.containsStudent(firstName,lastName)){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(STUDENT_NOT_UNIQUE_TITLE), props.getProperty(STUDENT_NOT_UNIQUE_MESSAGE));
        }else{
            jTPS_Transaction addStudentTransaction = new AddStudent_Transaction(app,firstName,lastName,team,role);
            CSWorkspace.jTPS.addTransaction(addStudentTransaction);
            
            // CLEAR THE TEXT FIELDS
            firstNameTextField.setText("");
            lasNameTextField.setText("");
            teamComboBox.setValue("");
            roleTextField.setText("");
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            firstNameTextField.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }       
    }

    public void handleKeyPressStudents(KeyCode code) {
        if (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE) {
            handleDeleteStudent();
        }
    }

    public void handleDeleteStudent() {
        ProjectsWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent();
        TableView studentsTable = workspace.getStudentsTable();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = studentsTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            Student student = (Student)selectedItem;
            CSData data = (CSData)app.getDataComponent();
            
            jTPS_Transaction removeTransaction = new RemoveStudent_Transaction(app,student.getFirstName(),student.getLastName(),student.getTeam(),student.getRole());
            CSWorkspace.jTPS.addTransaction(removeTransaction);                              
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }

    public void handleEditStudent(String initFirstName, String initLastName, String initTeam, String initRole) {
        ProjectsWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent();
        
        TextField firstNameTextField = workspace.getFirstNameTextField();
        TextField lastNameTextField = workspace.getLastNameTextField();
        ComboBox teamComboBox = workspace.getTeamComboBox();
        TextField roleTextField = workspace.getRoleTextField();
        
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String team = teamComboBox.getValue()==null?"":teamComboBox.getValue().toString();
        String role = roleTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSData data = (CSData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(firstName.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_STUDENT_FIRSTNAME_TITLE), props.getProperty(MISSING_STUDENT_FIRSTNAME_MESSAGE));
        }else if(lastName.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_STUDENT_LASTNAME_TITLE), props.getProperty(MISSING_STUDENT_LASTNAME_MESSAGE));       
        }else if(team.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_STUDENT_TEAM_TITLE), props.getProperty(MISSING_STUDENT_TEAM_MESSAGE));               
        }else if(role.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_STUDENT_ROLE_TITLE), props.getProperty(MISSING_STUDENT_ROLE_MESSAGE));
        }else if((!firstName.equals(firstName) || !lastName.equals(lastName)) && data.containsStudent(firstName,lastName)){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(STUDENT_NOT_UNIQUE_TITLE), props.getProperty(STUDENT_NOT_UNIQUE_MESSAGE));
        }else{
            jTPS_Transaction editStudentTransaction = new EditStudent_Transaction(app,initFirstName,initLastName,initTeam,initRole,
                firstName,lastName,team,role);
            CSWorkspace.jTPS.addTransaction(editStudentTransaction);
            
            // CLEAR THE TEXT FIELDS
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            teamComboBox.setValue("");
            roleTextField.setText("");
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            firstNameTextField.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }

}
