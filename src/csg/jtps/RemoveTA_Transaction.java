/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import java.util.HashMap;
import javafx.scene.control.Label;
import csg.data.CSData;
import csg.CSGeneratorApp;
import csg.workspace.CSWorkspace;

/**
 *
 * @author tyx
 */
public class RemoveTA_Transaction implements jTPS_Transaction {
    private String name;
    private String email;
    CSGeneratorApp app;
    HashMap<String, String> officeHoursGridTACellLabels;
    public RemoveTA_Transaction(CSGeneratorApp initApp, String name, String email) {
        this.name = name;
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
        data.removeTA(name);
        
        HashMap<String, Label> labels = ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().getOfficeHoursGridTACellLabels();
                for (Label label : labels.values()) {
                    if (label.getText().equals(name)
                    || (label.getText().contains(name + "\n"))
                    || (label.getText().contains("\n" + name))) {
                        data.removeTAFromCell(label.textProperty(), name);
                    }
        }
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.addTA(name, email);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().reloadOfficeHoursGrid3(data, officeHoursGridTACellLabels);
    }
    
    
}
