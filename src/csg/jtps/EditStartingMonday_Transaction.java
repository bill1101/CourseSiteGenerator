/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.workspace.CSWorkspace;
import java.time.LocalDate;

/**
 *
 * @author tyx
 */
public class EditStartingMonday_Transaction implements jTPS_Transaction {
    CSGeneratorApp app;
    String initDate;
    String date;
    
    public EditStartingMonday_Transaction(CSGeneratorApp initApp, String initDate, String startingDate) {
        this.app = initApp;
        this.initDate = initDate;
        this.date = startingDate;
    }

    @Override
    public void doTransaction() {
        //System.out.println("do:" + initDate + date);
        CSData data = (CSData)app.getDataComponent();
        data.setStartingMonday(date);
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getStartingMondayDatePicker().setOnAction(e -> {});
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getStartingMondayDatePicker().setValue(LocalDate.parse(date));
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getStartingMondayDatePicker().setOnAction(e -> {
            ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getController().handleEditStarting();
        });
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getEndingFridayDatePicker()
                .setDayCellFactory(((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getDayCellFactoryEnding());
    }

    @Override
    public void undoTransaction() {
        //System.out.println("undo" + initDate + date);
        CSData data = (CSData)app.getDataComponent();
        data.setStartingMonday(initDate);
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getStartingMondayDatePicker().setOnAction(e -> {});
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getStartingMondayDatePicker().setValue(LocalDate.parse(initDate));
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getStartingMondayDatePicker().setOnAction(e -> {
            ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getController().handleEditStarting();
        });
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getEndingFridayDatePicker()
                .setDayCellFactory(((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getDayCellFactoryEnding());
    }
    
}
