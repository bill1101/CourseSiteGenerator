/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.data.Student;
import csg.workspace.CSWorkspace;

/**
 *
 * @author tyx
 */
public class EditTeam_Transaction implements jTPS_Transaction {
    CSGeneratorApp app;
    private String initName;
    private String initColor;
    private String initTextColor;
    private String initLink;
    private String name;
    private String color;
    private String textColor;
    private String link;
    public EditTeam_Transaction(CSGeneratorApp initApp, String initName, String initColor, String initTextColor, String initLink, String name, String color, String textColor, String link) {
        app = initApp;
        this.initName = initName;
        this.initColor = initColor;
        this.initTextColor = initTextColor;
        this.initLink = initLink;
        this.name = name;
        this.color = color;
        this.textColor = textColor;
        this.link = link;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editTeam(initName,name,color,textColor,link);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getTeamsTable().refresh();
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getOptions_team().remove(initName);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getOptions_team().add(name);
        for(Student student:data.getStudents()){
            if(student.getTeam().equals(initName)){
                student.setTeam(name);
            }
        }
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getStudentsTable().refresh();
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editTeam(name,initName,initColor,initTextColor,initLink);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getTeamsTable().refresh();
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getOptions_team().remove(name);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getOptions_team().add(initName);
        for(Student student:data.getStudents()){
            if(student.getTeam().equals(name)){
                student.setTeam(initName);
            }
        }
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getStudentsTable().refresh();
    }   
}
