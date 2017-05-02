/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.workspace.CSWorkspace;

/**
 *
 * @author tyx
 */
public class AddTeam_Transaction implements jTPS_Transaction {    
    CSGeneratorApp app;
    private String name;
    private String color;
    private String textColor;
    private String link;
    
    public AddTeam_Transaction(CSGeneratorApp initApp, String name, String color, String textColor, String link) {
        app = initApp;
        this.name = name;
        this.color = color;
        this.textColor = textColor;
        this.link = link;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.addTeam(name,color,textColor,link);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getOptions_team().add(name);
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.removeTeam(name);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getOptions_team().remove(name);
    }
    
}
