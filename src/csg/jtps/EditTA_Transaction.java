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
import csg.data.Recitation;
import csg.workspace.CSWorkspace;
import java.util.Collections;

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
        
        for(Recitation recitation: data.getRecitations()){
            if(initName.equals(recitation.getTA1())){                
                recitation.setTA1(name);
            }else if(initName.equals(recitation.getTA2())){               
                recitation.setTA2(name);
            }
            Collections.sort(data.getRecitations());
            ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getRecitationsTable().refresh();
        }
        
        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getOptions_TA().remove(initName);
        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getOptions_TA().add(name);
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editTA(name, initName, initEmail);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().reloadOfficeHoursGrid3(data, officeHoursGridTACellLabels);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().getTATable().refresh();
        
        for(Recitation recitation: data.getRecitations()){
            if(name.equals(recitation.getTA1())){                
                recitation.setTA1(initName);
            }else if(name.equals(recitation.getTA2())){               
                recitation.setTA2(initName);
            }
            Collections.sort(data.getRecitations());
            ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getRecitationsTable().refresh();
        }
        
        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getOptions_TA().remove(name);
        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getOptions_TA().add(initName);
    }
    
    
}
