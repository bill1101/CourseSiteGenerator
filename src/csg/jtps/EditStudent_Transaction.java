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
public class EditStudent_Transaction implements jTPS_Transaction {
    CSGeneratorApp app;
    private String initFirstName;
    private String initLastName;
    private String initTeam;
    private String initRole;
    private String firstName;
    private String lastName;
    private String team;
    private String role;

    public EditStudent_Transaction(CSGeneratorApp initApp, String initFirstName, String initLastName, String initTeam, String initRole, String firstName, String lastName, String team, String role) {
        app = initApp;
        this.initFirstName = initFirstName;
        this.initLastName = initLastName;
        this.initTeam = initTeam;
        this.initRole = initRole;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.role = role;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editStudent(initFirstName,initLastName, firstName,lastName,team,role);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getStudentsTable().refresh();
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editStudent(firstName,lastName, initFirstName,initLastName,initTeam,initRole);
        ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent().getStudentsTable().refresh();
    }
}
