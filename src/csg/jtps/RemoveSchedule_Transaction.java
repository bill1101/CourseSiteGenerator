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
public class RemoveSchedule_Transaction implements jTPS_Transaction  {
    CSGeneratorApp app;
    private String type;
    private String date;
    private String time;
    private String title;
    private String topic;
    private String link;
    private String criteria;

    public RemoveSchedule_Transaction(CSGeneratorApp initApp, String type, String date, String time, String title, String topic, String link, String criteria) {
        app = initApp;
        this.type = type;
        this.date = date;
        this.time = time;
        this.title = title;
        this.topic = topic;
        this.link = link;
        this.criteria = criteria;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.removeSchedule(type, date);
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.addSchedule(type, date, time, title, topic, link, criteria);
    }
    
}
