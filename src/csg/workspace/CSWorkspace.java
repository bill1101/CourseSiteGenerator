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
    
    TAWorkspace TAworkspaceComponent;
    CourseDetailsWorkspace courseDetailsWorkspaceComponent;
    RecitationWorkspace recitationWorkspaceComponent;
    ScheduleWorkspace scheduleWorkspaceComponent;
    ProjectsWorkspace projectWorkspaceComponent;
    public CSWorkspace(CSGeneratorApp initApp) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        tabPane = new TabPane();
        app = initApp;
        
        courseDetailsWorkspaceComponent = new CourseDetailsWorkspace(initApp);
        courseDetailsTab = courseDetailsWorkspaceComponent.getCourseDetailsTab();
              
        TAworkspaceComponent = new TAWorkspace(initApp);
        TAData = TAworkspaceComponent.getTAData();     
        
        recitationWorkspaceComponent = new RecitationWorkspace(initApp);
        recitationData = recitationWorkspaceComponent.getRecitationTab();
        
        scheduleWorkspaceComponent = new ScheduleWorkspace(initApp);
        scheduleData = scheduleWorkspaceComponent.getScheduleTab();
             
        projectWorkspaceComponent = new ProjectsWorkspace(initApp);
        projectData = projectWorkspaceComponent.getProjectsTab();
           
        tabPane.getTabs().addAll(courseDetailsTab,TAData,recitationData,scheduleData,projectData);      
        workspace = new BorderPane();       
        // AND PUT EVERYTHING IN THE WORKSPACE
        ((BorderPane) workspace).setCenter(tabPane);
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public Tab getCourseDetailsTab() {
        return courseDetailsTab;
    }

    public ScheduleWorkspace getScheduleWorkspaceComponent() {
        return scheduleWorkspaceComponent;
    }

    public ProjectsWorkspace getProjectWorkspaceComponent() {
        return projectWorkspaceComponent;
    }

    public CourseDetailsWorkspace getCourseDetailsWorkspaceComponent() {
        return courseDetailsWorkspaceComponent;
    }

    public RecitationWorkspace getRecitationWorkspaceComponent() {
        return recitationWorkspaceComponent;
    }

    public Tab getTAData() {
        return TAData;
    }
    
    public TAWorkspace getTAworkspaceComponent() {
        return TAworkspaceComponent;
    }
    
    @Override
    public void resetWorkspace() {
        
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        
    }
    
}
