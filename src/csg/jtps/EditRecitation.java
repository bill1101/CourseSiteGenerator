/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.jtps.jTPS_Transaction;

/**
 *
 * @author tyx
 */
public class EditRecitation implements jTPS_Transaction {
    CSGeneratorApp app;
    private String initSection;
    private String initInstructor;
    private String initDayTime;
    private String initLocation;
    private String initTA1;
    private String initTA2;
    
    private String section;
    private String instructor;
    private String dayTime;
    private String location;
    private String TA1;
    private String TA2;

    public EditRecitation(CSGeneratorApp app, String initSection, String initInstructor, String initDayTime, String initLocation, String initTA1, String initTA2, 
            String section,String instructor,String dayTime,String location,String ta1,String ta2) {
        this.app = app;
        this.initSection = initSection;
        this.initInstructor = initInstructor;
        this.initDayTime = initDayTime;
        this.initLocation = initLocation;
        this.initTA1 = initTA1;
        this.initTA2 = initTA2;
        
        this.section = section;
        this.instructor = instructor;
        this.dayTime = dayTime;
        this.location = location;
        this.TA1 = ta1;
        this.TA2 = ta2;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editRecitation(initSection, section,instructor,dayTime,location,TA1,TA2);
        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getRecitationsTable().refresh();
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editRecitation(section, initSection,initInstructor,initDayTime,initLocation,initTA1,initTA2);
        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getRecitationsTable().refresh();
    }
    
}
