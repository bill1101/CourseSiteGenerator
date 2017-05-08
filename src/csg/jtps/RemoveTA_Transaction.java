/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import java.util.HashMap;
import javafx.scene.control.Label;
import csg.data.CSData;
import csg.CSGeneratorApp;
import csg.data.Recitation;
import csg.workspace.CSWorkspace;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author tyx
 */
public class RemoveTA_Transaction implements jTPS_Transaction {
    private String name;
    private String email;
    CSGeneratorApp app;
    HashMap<String, String> officeHoursGridTACellLabels;
//    String removedSection;
//    Recitation removedRecitation;
    HashMap<String,Recitation> removedRecitations;
    public RemoveTA_Transaction(CSGeneratorApp initApp, String name, String email) {
        this.name = name;
        this.email = email;
        app = initApp;
        officeHoursGridTACellLabels = new HashMap<String,String>();
        removedRecitations = new HashMap();
        HashMap<String, Label> originalLabels = ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().getOfficeHoursGridTACellLabels();       
        
        for (String s : originalLabels.keySet())
        {
            String content = originalLabels.get(s).getText();
            officeHoursGridTACellLabels.put(s, content);
        }
    }

    @Override
    public void doTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.removeTA(name);
        
        HashMap<String, Label> labels = ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().getOfficeHoursGridTACellLabels();
                for (Label label : labels.values()) {
                    if (label.getText().equals(name)
                    || (label.getText().contains(name + "\n"))
                    || (label.getText().contains("\n" + name))) {
                        data.removeTAFromCell(label.textProperty(), name);
                    }
        }
        
        for(Recitation recitation: data.getRecitations()){
            if(name.equals(recitation.getTA1())){
                String removedSection = recitation.getSection();
                Recitation removedRecitation = new Recitation(recitation.getSection(),recitation.getInstructor(),
                    recitation.getDayTime(),recitation.getLocation(),recitation.getTA1(),recitation.getTA2());
                removedRecitations.put(removedSection, removedRecitation);
                recitation.setTA1("");
            }else if(name.equals(recitation.getTA2())){
                String removedSection = recitation.getSection();
                Recitation removedRecitation = new Recitation(recitation.getSection(),recitation.getInstructor(),
                    recitation.getDayTime(),recitation.getLocation(),recitation.getTA1(),recitation.getTA2());
                removedRecitations.put(removedSection, removedRecitation);
                recitation.setTA1("");
            }
            Collections.sort(data.getRecitations());
            ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getRecitationsTable().refresh();
        }

        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getOptions_TA().remove(name);
    }

    @Override
    public void undoTransaction() {
        CSData data = (CSData)app.getDataComponent();
        data.addTA(name, email);
        ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent().reloadOfficeHoursGrid3(data, officeHoursGridTACellLabels);
        
        for(String section: removedRecitations.keySet()){
            Recitation newRecitation = removedRecitations.get(section);
            //System.out.println(newRecitation.getTA1() + ": " + newRecitation.getTA2());
            for(Recitation recitation: data.getRecitations()){
                if(recitation.getSection().equals(section)){     
                    //System.out.println("section:" + section);
                    recitation.setTA1(newRecitation.getTA1());
                    recitation.setTA2(newRecitation.getTA2());
                }
            }
        }
        Collections.sort(data.getRecitations());
        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getRecitationsTable().refresh();
        ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent().getOptions_TA().add(name);
    }
    
    
}
