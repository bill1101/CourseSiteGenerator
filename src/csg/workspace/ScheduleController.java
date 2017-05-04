/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.data.CSData;
import static csg.CSGeneratorProp.*;
import csg.data.ScheduleItem;
import csg.jtps.AddSchedule_Transaction;
import csg.jtps.EditEndingFriday_Transaction;
import csg.jtps.EditSchedule_Transaction;
import csg.jtps.EditStartingMonday_Transaction;
import csg.jtps.RemoveSchedule_Transaction;
import csg.jtps.jTPS_Transaction;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import properties_manager.PropertiesManager;

/**
 *
 * @author tyx
 */
public class ScheduleController {
    CSGeneratorApp app;

    public ScheduleController(CSGeneratorApp app) {
        this.app = app;
    }
    
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }

    public void handleEditStarting() {
        ScheduleWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent();
        CSData data = (CSData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        DatePicker startingDatePicker = workspace.getStartingMondayDatePicker();
        String startingDate = startingDatePicker.getValue().toString();
        
        jTPS_Transaction editStartingMondayTransaction = new EditStartingMonday_Transaction(app,data.getStartingMonday(),startingDate);
        CSWorkspace.jTPS.addTransaction(editStartingMondayTransaction);
        
        markWorkAsEdited();
    }

    public void handleEditEnding() {
        ScheduleWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent();
        CSData data = (CSData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        DatePicker endingDatePicker = workspace.getEndingFridayDatePicker();
        String endingDate = endingDatePicker.getValue().toString();
        
        jTPS_Transaction editEndingFridayTransaction = new EditEndingFriday_Transaction(app,data.getEndingFriday(),endingDate);
        CSWorkspace.jTPS.addTransaction(editEndingFridayTransaction);
        
        markWorkAsEdited();
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
    
    public void handleKeyPress(KeyCode code) {
        if (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE) {
            handleDeleteSchedule();
        }
    }

    public void handleAddSchedule() {
        ScheduleWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent();
        
        ComboBox typeComboBox = workspace.getTypeComboBox();
        DatePicker dateDatePicker = workspace.getDateDatePicker();
        TextField timeTextField = workspace.getTimeTextField();
        TextField titleTextField = workspace.getTitleTextField();
        TextField topicTextField = workspace.getTopicTextField();
        TextField linkTextField = workspace.getLinkTextField();
        TextField criteriaTextField = workspace.getCriteriaTextField();
        
        String type = typeComboBox.getValue()==null?"":typeComboBox.getValue().toString();
        String[] tempDate = dateDatePicker.getValue().toString().split("-");
        String date = tempDate.length!=3?"":Integer.parseInt(tempDate[1])+"/"+Integer.parseInt(tempDate[2])+"/"+Integer.parseInt(tempDate[0]);
        String time = timeTextField.getText();
        String title = titleTextField.getText();
        String topic = topicTextField.getText();
        String link = linkTextField.getText();
        String criteria = criteriaTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSData data = (CSData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(type.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_TYPE_TITLE), props.getProperty(MISSING_SCHEDULE_TYPE_MESSAGE));        
        }else if(date.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_DATE_TITLE), props.getProperty(MISSING_SCHEDULE_DATE_MESSAGE));        
        }else if(title.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_TITLE_TITLE), props.getProperty(MISSING_SCHEDULE_TITLE_MESSAGE));       
        }else if(data.containsSchedule(type,date)){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(SCHEDULE_NOT_UNIQUE_TITLE), props.getProperty(SCHEDULE_NOT_UNIQUE_MESSAGE));
        }else{
            jTPS_Transaction addScheduleTransaction = new AddSchedule_Transaction(app,type,date,time,title,topic,link,criteria);
            CSWorkspace.jTPS.addTransaction(addScheduleTransaction);
            
            // CLEAR THE TEXT FIELDS
            typeComboBox.setValue("");
            dateDatePicker.setValue(LocalDate.now());
            timeTextField.setText("");
            titleTextField.setText("");
            topicTextField.setText("");
            linkTextField.setText("");
            criteriaTextField.setText("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            typeComboBox.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }

    public void handleEditRecitation(String initType, String initDate, String initTime, String initTitle, String initTopic, String initLink, String initCriteria) {
        ScheduleWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent();
        
        ComboBox typeComboBox = workspace.getTypeComboBox();
        DatePicker dateDatePicker = workspace.getDateDatePicker();
        TextField timeTextField = workspace.getTimeTextField();
        TextField titleTextField = workspace.getTitleTextField();
        TextField topicTextField = workspace.getTopicTextField();
        TextField linkTextField = workspace.getLinkTextField();
        TextField criteriaTextField = workspace.getCriteriaTextField();
        
        String type = typeComboBox.getValue()==null?"":typeComboBox.getValue().toString();
        String[] tempDate = dateDatePicker.getValue().toString().split("-");
        String date = tempDate.length!=3?"":Integer.parseInt(tempDate[1])+"/"+Integer.parseInt(tempDate[2])+"/"+Integer.parseInt(tempDate[0]);
        String time = timeTextField.getText();
        String title = titleTextField.getText();
        String topic = topicTextField.getText();
        String link = linkTextField.getText();
        String criteria = criteriaTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSData data = (CSData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(type.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_TYPE_TITLE), props.getProperty(MISSING_SCHEDULE_TYPE_MESSAGE));        
        }else if(date.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_DATE_TITLE), props.getProperty(MISSING_SCHEDULE_DATE_MESSAGE));        
        }else if(title.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_SCHEDULE_TITLE_TITLE), props.getProperty(MISSING_SCHEDULE_TITLE_MESSAGE));       
        }else if((!type.equals(initType) || !date.equals(initDate)) && data.containsSchedule(type,date)){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(SCHEDULE_NOT_UNIQUE_TITLE), props.getProperty(SCHEDULE_NOT_UNIQUE_MESSAGE));
        }else{
            jTPS_Transaction editScheduleTransaction = new EditSchedule_Transaction(app
                    ,initType,initDate,initTime,initTitle,initTopic,initLink,initCriteria
                    ,type,date,time,title,topic,link,criteria);
            CSWorkspace.jTPS.addTransaction(editScheduleTransaction);
            
            // CLEAR THE TEXT FIELDS
            typeComboBox.setValue("");
            dateDatePicker.setValue(LocalDate.now());
            timeTextField.setText("");
            titleTextField.setText("");
            topicTextField.setText("");
            linkTextField.setText("");
            criteriaTextField.setText("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            typeComboBox.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }

    public void handleDeleteSchedule() {
        ScheduleWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent();
            TableView scheduleTable = workspace.getScheduleItemsTable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = scheduleTable.getSelectionModel().getSelectedItem();
            //System.out.println(selectedItem);
            if (selectedItem != null) {
                // GET THE RECITATION AND REMOVE IT
                ScheduleItem si = (ScheduleItem)selectedItem;
                CSData data = (CSData)app.getDataComponent();
                
                jTPS_Transaction removeTransaction = new RemoveSchedule_Transaction(app,si.getType(),si.getDate(),
                    si.getTime(),si.getTitle(),si.getTopic(),si.getLink(),si.getCriteria());
                CSWorkspace.jTPS.addTransaction(removeTransaction);                              
                // WE'VE CHANGED STUFF
                markWorkAsEdited();
            }
    }
}
