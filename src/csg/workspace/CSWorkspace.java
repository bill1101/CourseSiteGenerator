/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.CSGeneratorProp;
import csg.data.CSData;
import csg.jtps.jTPS;
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
    static jTPS jTPS = new jTPS();
    
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
        app.getGUI().getUndoButton().setOnAction(e -> {
            jTPS.undoTransaction();
        });
        app.getGUI().getRedobutton().setOnAction(e -> {
            jTPS.doTransaction();
        });
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
        
        // CLEAR OUT THE GRID PANE
        TAworkspaceComponent.officeHoursGridPane.getChildren().clear();
        
        // AND THEN ALL THE GRID PANES AND LABELS
        TAworkspaceComponent.officeHoursGridTimeHeaderPanes.clear();
        TAworkspaceComponent.officeHoursGridTimeHeaderLabels.clear();
        TAworkspaceComponent.officeHoursGridDayHeaderPanes.clear();
        TAworkspaceComponent.officeHoursGridDayHeaderLabels.clear();
        TAworkspaceComponent.officeHoursGridTimeCellPanes.clear();
        TAworkspaceComponent.officeHoursGridTimeCellLabels.clear();
        TAworkspaceComponent.officeHoursGridTACellPanes.clear();
        TAworkspaceComponent.officeHoursGridTACellLabels.clear();
        
//        courseDetailsWorkspaceComponent.getSubjectComboBox().setValue("");
//        courseDetailsWorkspaceComponent.getNumberComboBox().setValue("");
//        courseDetailsWorkspaceComponent.getSemesterComboBox().setValue("");
//        courseDetailsWorkspaceComponent.getYearComboBox().setValue("");
//        courseDetailsWorkspaceComponent.getTitleTextField().setText("aa");
//        System.out.println(courseDetailsWorkspaceComponent.getTitleTextField().getText());
//        courseDetailsWorkspaceComponent.getInstructorNameTextField().setText("");
//        courseDetailsWorkspaceComponent.getInstructorHomeTextField().setText("");
//        courseDetailsWorkspaceComponent.getExportDirContent().setText("");
//        courseDetailsWorkspaceComponent.getSelectTemplateContent().setText("");
//        courseDetailsWorkspaceComponent.getBannerSchoolImage().setImage(null);
//        courseDetailsWorkspaceComponent.getLeftFooterImage().setImage(null);
//        courseDetailsWorkspaceComponent.getRightFooterImage().setImage(null);
//        courseDetailsWorkspaceComponent.getStylesheetComboBox().setValue("");
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        CSData csData = (CSData)dataComponent;
        courseDetailsWorkspaceComponent.reloadWorkspace(csData);
        TAworkspaceComponent.reloadOfficeHoursGrid(csData);
        scheduleWorkspaceComponent.reloadWorkspace(csData);
    }
    
}
