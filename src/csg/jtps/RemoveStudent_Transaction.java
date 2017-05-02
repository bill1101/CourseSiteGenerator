/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSData;

/**
 *
 * @author tyx
 */
public class RemoveStudent_Transaction implements jTPS_Transaction  {
    CSGeneratorApp app;
    private String firstName;
    private String lastName;
    private String team;
    private String role;
    
    public RemoveStudent_Transaction(CSGeneratorApp initApp, String firstName, String lastName, String team, String role) {
        app = initApp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.role = role;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.removeStudent(firstName, lastName);
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.addStudent(firstName, lastName, team, role);
    }
    
}
