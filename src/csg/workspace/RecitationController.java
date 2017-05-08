/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import static csg.CSGeneratorProp.*;
import csg.data.CSData;
import csg.data.Recitation;
import csg.jtps.AddRecitation_Transaction;
import csg.jtps.EditRecitation;
import csg.jtps.RemoveRecitation_Transaction;
import csg.jtps.jTPS_Transaction;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import javafx.scene.control.ComboBox;
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
public class RecitationController {
    CSGeneratorApp app;
    /**
     * Constructor, note that the app must already be constructed.
     */
    public RecitationController(CSGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
    }
    
    /**
     * This helper method should be called every time an edit happens.
     */    
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }

    public void handleAddRecitation() {
        RecitationWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent();
        TextField sectionTextField = workspace.getSectionTextField();
        TextField instructorTextField = workspace.getInstructorTextField();
        TextField dayTimeTextField = workspace.getDayTimeTextField();
        TextField locationTextField = workspace.getLocationTextField();
        ComboBox ta1ComboBox = workspace.getTA1ComboBox();
        ComboBox ta2ComboBox = workspace.getTA2ComboBox();
        String section = sectionTextField.getText();
        String instructor = instructorTextField.getText();
        String dayTime = dayTimeTextField.getText();
        String location = locationTextField.getText();
        String ta1 = ta1ComboBox.getValue()==null?"":ta1ComboBox.getValue().toString();
        String ta2 = ta2ComboBox.getValue()==null?"":ta2ComboBox.getValue().toString();
                        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSData data = (CSData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(section.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_SECTION_TITLE), props.getProperty(MISSING_RECITATION_SECTION_MESSAGE)); 
        }else if(dayTime.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_DAYTIME_TITLE), props.getProperty(MISSING_RECITATION_DAYTIME_MESSAGE));
        }else if(location.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_LOCATION_TITLE), props.getProperty(MISSING_RECITATION_LOCATION_MESSAGE));
        }else if(data.containsRecitation(section)){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(RECITATION_NOT_UNIQUE_TITLE), props.getProperty(RECITATION_NOT_UNIQUE_MESSAGE));
        }else{
            jTPS_Transaction addRecitationTransaction = new AddRecitation_Transaction(app,section,instructor,dayTime,location,ta1,ta2);
            CSWorkspace.jTPS.addTransaction(addRecitationTransaction);
            
            // CLEAR THE TEXT FIELDS
            sectionTextField.setText("");
            instructorTextField.setText("");
            dayTimeTextField.setText("");
            locationTextField.setText("");
            ta1ComboBox.setValue("");
            ta2ComboBox.setValue("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            sectionTextField.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }

    public void handleKeyPress(KeyCode code) {
        if (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE) {
            handleDeleteRecitation();
            markWorkAsEdited();
        }
    }
    public void handleDeleteRecitation() {
        RecitationWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent();
            TableView recitationTable = workspace.getRecitationsTable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = recitationTable.getSelectionModel().getSelectedItem();
            //System.out.println(selectedItem);
            if (selectedItem != null) {
                // GET THE RECITATION AND REMOVE IT
                Recitation recitation = (Recitation)selectedItem;
                CSData data = (CSData)app.getDataComponent();
                
                jTPS_Transaction removeTransaction = new RemoveRecitation_Transaction(app,recitation.getSection(),
                        recitation.getInstructor(),recitation.getDayTime(),recitation.getLocation(),recitation.getTA1(),recitation.getTA2());
                CSWorkspace.jTPS.addTransaction(removeTransaction);                              
                // WE'VE CHANGED STUFF
                markWorkAsEdited();
            }
    }
    
    public void handleEditRecitation(String initSection, String initInstructor, String initDayTime, String initLocation, String initTA1, String initTA2) {
        RecitationWorkspace workspace = ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent();
        TextField sectionTextField = workspace.getSectionTextField();
        TextField instructorTextField = workspace.getInstructorTextField();
        TextField dayTimeTextField = workspace.getDayTimeTextField();
        TextField locationTextField = workspace.getLocationTextField();
        ComboBox ta1ComboBox = workspace.getTA1ComboBox();
        ComboBox ta2ComboBox = workspace.getTA2ComboBox();
        String section = sectionTextField.getText();
        String instructor = instructorTextField.getText();
        String dayTime = dayTimeTextField.getText();
        String location = locationTextField.getText();
        String ta1 = ta1ComboBox.getValue()==null?"":ta1ComboBox.getValue().toString();
        String ta2 = ta2ComboBox.getValue()==null?"":ta2ComboBox.getValue().toString();
                        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSData data = (CSData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        if(section.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_SECTION_TITLE), props.getProperty(MISSING_RECITATION_SECTION_MESSAGE)); 
        }else if(dayTime.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_DAYTIME_TITLE), props.getProperty(MISSING_RECITATION_DAYTIME_MESSAGE));
        }else if(location.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_RECITATION_LOCATION_TITLE), props.getProperty(MISSING_RECITATION_LOCATION_MESSAGE));
        }else if(!section.equals(initSection) && data.containsRecitation(section)){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(RECITATION_NOT_UNIQUE_TITLE), props.getProperty(RECITATION_NOT_UNIQUE_MESSAGE));
        }else{
            jTPS_Transaction editTransaction = new EditRecitation(app,initSection, initInstructor, initDayTime, initLocation, initTA1, initTA2,
                section,instructor,dayTime,location,ta1,ta2);
            CSWorkspace.jTPS.addTransaction(editTransaction);
            sectionTextField.setText("");
            instructorTextField.setText("");
            dayTimeTextField.setText("");
            locationTextField.setText("");
            ta1ComboBox.setValue("");
            ta2ComboBox.setValue("");
            sectionTextField.requestFocus();
            
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

    

    
}
