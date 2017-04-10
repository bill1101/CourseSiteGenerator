/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.data.CSData;
import csg.CSGeneratorApp;
/**
 *
 * @author tyx
 */
public class AddTA_Transaction implements jTPS_Transaction {
    private String name;
    private String email;
    CSGeneratorApp app;
    public AddTA_Transaction(CSGeneratorApp initApp,String name,String email) {
        this.name = name;
        this.email = email;
        app = initApp;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.addTA(name, email);
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.removeTA(name);
    }
    
}
