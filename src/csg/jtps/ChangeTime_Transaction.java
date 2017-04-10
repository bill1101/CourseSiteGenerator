/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import java.util.HashMap;
import javafx.scene.control.Label;
import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.workspace.CSWorkspace;
import csg.workspace.TAWorkspace;

/**
 *
 * @author tyx
 */
public class ChangeTime_Transaction implements jTPS_Transaction{
    int oldStart;
    int oldEnd;
    int newStart;
    int newEnd;
    CSGeneratorApp app;
    HashMap<String, String> officeHoursGridTACellLabels;
    
    public ChangeTime_Transaction(CSGeneratorApp initApp, int oldStart, int oldEnd, int newStart, int newEnd) {
        this.oldStart = oldStart;
        this.oldEnd = oldEnd;
        this.newStart = newStart;
        this.newEnd = newEnd;
        
        app = initApp;
        officeHoursGridTACellLabels = new HashMap<String,String>();
        HashMap<String, Label> originalLabels = ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().getOfficeHoursGridTACellLabels();       
        
        for (String s : originalLabels.keySet())
        {
            String content = originalLabels.get(s).getText();
            officeHoursGridTACellLabels.put(s, content);
        }
    }

    @Override
    public void doTransaction() {
        CSData taData = (CSData)app.getDataComponent();
        taData.setStartHour(newStart);
        taData.setEndHour(newEnd);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().reloadOfficeHoursGrid2(taData,oldStart,oldEnd);
        app.getGUI().getFileController().markAsEdited(app.getGUI());    }

    @Override
    public void undoTransaction() {
        CSData taData = (CSData)app.getDataComponent();
        taData.setStartHour(oldStart);
        taData.setEndHour(oldEnd);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().reloadOfficeHoursGrid3(taData, officeHoursGridTACellLabels);
    }
    
    
}
