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
public class AddRecitation_Transaction implements jTPS_Transaction {
    
    private String section;
    private String instructor;
    private String dayTime;
    private String location;
    private String ta1;
    private String ta2;
    CSGeneratorApp app;

    public AddRecitation_Transaction(CSGeneratorApp initApp, String section, String instructor, String dayTime, String location, String ta1, String ta2) {
        this.section = section;
        this.instructor = instructor;
        this.dayTime = dayTime;
        this.location = location;
        this.ta1 = ta1;
        this.ta2 = ta2;
        this.app = initApp;
    }
    
    
    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.addRecitation(section,instructor,dayTime,location,ta1,ta2);
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.removeRecitation(section);
    }
    
}
