/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.CSGeneratorProp;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import properties_manager.PropertiesManager;

/**
 *
 * @author tyx
 */
public class CSWorkspace extends AppWorkspaceComponent{
    CSGeneratorApp app;

    // THIS PROVIDES RESPONSES TO INTERACTIONS WITH THIS WORKSPACE
    CSController controller;
    
    TabPane tabPane;
    Tab courseDetailsTab;
    Tab TAData;
    Tab recitationData;
    Tab scheduleData;
    Tab projectData;
    
    public CSWorkspace(CSGeneratorApp initApp) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        tabPane = new TabPane();
        app = initApp;
        
        courseDetailsTab = new CourseDetailsWorkspace(initApp).getCourseDetailsTab();
              
        /////////
        TAData = new TAWorkspace(initApp).getTAData();     
        
        recitationData = new RecitationWorkspace(initApp).getRecitationTab();
               
        scheduleData = new ScheduleWorkspace(initApp).getScheduleTab();
              
        projectData = new ProjectsWorkspace(initApp).getProjectsTab();
           
        tabPane.getTabs().addAll(courseDetailsTab,TAData,recitationData,scheduleData,projectData);      
        workspace = new BorderPane();       
        // AND PUT EVERYTHING IN THE WORKSPACE
        ((BorderPane) workspace).setCenter(tabPane);
    }
    
    @Override
    public void resetWorkspace() {
        
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        
    }
    
}
