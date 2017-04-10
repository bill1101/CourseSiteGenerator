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
public class ToggleTA_Transaction implements jTPS_Transaction{
    String cellkey;
    String name;
    CSGeneratorApp app;
    public ToggleTA_Transaction(CSGeneratorApp initApp, String cellkey, String name) {
        this.cellkey = cellkey;
        this.name = name;
        app = initApp;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.toggleTAOfficeHours(cellkey, name);
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.toggleTAOfficeHours(cellkey, name);
    }
    
    
}
