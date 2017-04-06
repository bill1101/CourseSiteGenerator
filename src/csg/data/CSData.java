/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;


import csg.CSGeneratorApp;
import csg.CSGeneratorProp;
import csg.workspace.CSWorkspace;
import djf.components.AppDataComponent;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
/**
 *
 * @author tyx
 */
public class CSData implements AppDataComponent{
    CSGeneratorApp app;
    ///////////////////////////////////////////////////////////////////Course Site tab    
    String subject;
    String number;
    String semester;
    String year;
    String instructorName;
    String instructorHome;
    String exportDir;
    
    String templateDir;
    ObservableList<SitePage> sitePages;
    
    String bannerSchoolImage;
    String leftFooterImage;
    String rightFooterImage;
    String styleSheet;
    ///////////////////////////////////////////////////////////////////TA tab
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<TeachingAssistant> teachingAssistants;
    
    // THIS WILL STORE ALL THE OFFICE HOURS GRID DATA, WHICH YOU
    // SHOULD NOTE ARE StringProperty OBJECTS THAT ARE CONNECTED
    // TO UI LABELS, WHICH MEANS IF WE CHANGE VALUES IN THESE
    // PROPERTIES IT CHANGES WHAT APPEARS IN THOSE LABELS
    HashMap<String, StringProperty> officeHours;
   
    // THESE ARE THE LANGUAGE-DEPENDENT VALUES FOR
    // THE OFFICE HOURS GRID HEADERS. NOTE THAT WE
    // LOAD THESE ONCE AND THEN HANG ON TO THEM TO
    // INITIALIZE OUR OFFICE HOURS GRID
    ArrayList<String> gridHeaders;

    // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
    // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
    // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
    // NO MEANS FOR CHANGING THESE VALUES
    int startHour;
    int endHour;
    
    // DEFAULT VALUES FOR START AND END HOURS IN MILITARY HOURS
    public static final int MIN_START_HOUR = 0;
    public static final int MAX_END_HOUR = 24;
    
    ///////////////////////////////////////////////////////////////////Recitation tab
    ObservableList<Recitation> recitations;
    
    ///////////////////////////////////////////////////////////////////Schedule tab
    StringProperty startingMonday;
    StringProperty endingFriday;
    ObservableList<ScheduleItem> scheduleItems;
    
    ///////////////////////////////////////////////////////////////////Project tab
    ObservableList<Team> teams;
    ObservableList<Student> students;
    
    public CSData(CSGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;

        
        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        teachingAssistants = FXCollections.observableArrayList();

        // THESE ARE THE DEFAULT OFFICE HOURS
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        //THIS WILL STORE OUR OFFICE HOURS
        officeHours = new HashMap();
        // THESE ARE THE LANGUAGE-DEPENDENT OFFICE HOURS GRID HEADERS
        /*
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> timeHeaders = props.getPropertyOptionsList(TAManagerProp.OFFICE_HOURS_TABLE_HEADERS);
        ArrayList<String> dowHeaders = props.getPropertyOptionsList(TAManagerProp.DAYS_OF_WEEK);
        gridHeaders = new ArrayList();
        gridHeaders.addAll(timeHeaders);
        gridHeaders.addAll(dowHeaders);
        */
    }

    @Override
    public void resetData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
