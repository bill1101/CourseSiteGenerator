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
public class EditEndingFriday_Transaction implements jTPS_Transaction {
    CSGeneratorApp app;
    String initDate;
    String date;
    
    public EditEndingFriday_Transaction(CSGeneratorApp initApp, String initDate, String endingDate) {
        this.app = initApp;
        this.initDate = initDate;
        this.date = endingDate;
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.setEndingFriday(date);
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getEndingFridayDatePicker().setOnAction(e -> {});
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getEndingFridayDatePicker().setValue(LocalDate.parse(date));
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getEndingFridayDatePicker().setOnAction(e -> {
            ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getController().handleEditEnding();
        });
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getStartingMondayDatePicker()
                .setDayCellFactory(((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getDayCellFactoryStarting());
    }

    @Override
    public void undoTransaction() {
        
        CSData data = (CSData)app.getDataComponent();
        data.setEndingFriday(initDate);
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getEndingFridayDatePicker().setOnAction(e -> {});
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getEndingFridayDatePicker().setValue(LocalDate.parse(initDate));
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getEndingFridayDatePicker().setOnAction(e -> {
            ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getController().handleEditEnding();
        });
        ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getStartingMondayDatePicker()
                .setDayCellFactory(((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent().getDayCellFactoryStarting());
    }

}
