/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.jtps.EditEndingFriday_Transaction;
import csg.jtps.EditStartingMonday_Transaction;
import csg.jtps.jTPS_Transaction;
import djf.ui.AppGUI;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
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
}
