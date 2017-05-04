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
public class EditSchedule_Transaction implements jTPS_Transaction {
    CSGeneratorApp app;
    private String initType;
    private String initDate;
    private String initTime;
    private String initTitle;
    private String initTopic;
    private String initLink;
    private String initCriteria;
    
    private String type;
    private String date;
    private String time;
    private String title;
    private String topic;
    private String link;
    private String criteria;

    public EditSchedule_Transaction(CSGeneratorApp initApp, String initType, String initDate, String initTime, String initTitle, String initTopic, String initLink, String initCriteria, String type, String date, String time, String title, String topic, String link, String criteria) {
        app = initApp;
        this.initType = initType;
        this.initDate = initDate;
        this.initTime = initTime;
        this.initTitle = initTitle;
        this.initTopic = initTopic;
        this.initLink = initLink;
        this.initCriteria = initCriteria;
        
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
        data.editSchedule(initType,initDate,type,date,time,title,topic,link,criteria);
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getScheduleItemsTable().refresh();
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.editSchedule(type, date, initType, initDate, initTime, initTitle, initTopic, initLink, initCriteria);
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getScheduleItemsTable().refresh();
    }
    
}
