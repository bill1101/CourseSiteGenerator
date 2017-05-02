/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.data.Student;
import csg.data.Team;
import csg.workspace.CSWorkspace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tyx
 */
public class RemoveTeam_Transaction implements jTPS_Transaction {
    
    CSGeneratorApp app;
    private String name;
    private String color;
    private String textColor;
    private String link;
    private ObservableList<Student> oldStudents;
    private ObservableList<Student> copyStudents;
    
    public RemoveTeam_Transaction(CSGeneratorApp initApp, String name, String color, String textColor, String link) {        
        app = initApp;
        CSData data = (CSData)app.getDataComponent();
        this.name = name;
        this.color = color;
        this.textColor = textColor;
        this.link = link;
        copyStudents = FXCollections.observableArrayList();
        oldStudents = FXCollections.observableArrayList();
        for(Student student: data.getStudents()){
            copyStudents.add(student);
            oldStudents.add(student);
        }        
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.removeTeam(name);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getOptions_team().remove(name);
        for(Student student: copyStudents){
            if(student.getTeam().equals(name)){
                data.getStudents().remove(student);
            }
        }       
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getStudentsTable().refresh();
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.addTeam(name, color, textColor, link);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getOptions_team().add(name);
        data.getStudents().clear();
        for(Student student: oldStudents){
            data.getStudents().add(student);
        }
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getStudentsTable().refresh();
    }
    
}
