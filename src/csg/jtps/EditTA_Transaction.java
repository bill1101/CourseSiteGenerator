/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.workspace.CSWorkspace;

/**
 *
 * @author tyx
 */
public class EditTA_Transaction implements jTPS_Transaction{
    private String initName;
    private String name;
    private String initEmail;
    private String email;   
    CSGeneratorApp app;
    HashMap<String, String> officeHoursGridTACellLabels;
    
    public EditTA_Transaction(CSGeneratorApp initApp, String initName, String name, String initEmail, String email) {
        this.initName = initName;
        this.name = name;
        this.initEmail = initEmail;
        this.email = email;
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
        CSData data = (CSData)app.getDataComponent();
        data.editTA(initName, name, email);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().getTATable().refresh();
        for (Pane pane : ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().getOfficeHoursGridTACellPanes().values()) {
            String cellKey = pane.getId();
            data.replaceTAOfficeHours(cellKey,initName,name);
        }
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editTA(name, initName, initEmail);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().reloadOfficeHoursGrid3(data, officeHoursGridTACellLabels);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().getTATable().refresh();
    }
    
    
}
