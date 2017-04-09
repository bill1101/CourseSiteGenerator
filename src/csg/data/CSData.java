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

        sitePages = FXCollections.observableArrayList();
        sitePages.add(new SitePage(true,"Home","index.html","HomeBuilder.js"));
        sitePages.add(new SitePage(true,"Syllabus","syllabus.html","SyllabusBuilder.js"));
        sitePages.add(new SitePage(true,"Schedule","schedule.html","ScheduleBuilder.js"));
        sitePages.add(new SitePage(true,"HWs","hws.html","HWsBuilder.js"));
        sitePages.add(new SitePage(false,"Projects","projects.html","ProjectsBuilder.js"));
        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        teachingAssistants = FXCollections.observableArrayList();

        // THESE ARE THE DEFAULT OFFICE HOURS
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        //THIS WILL STORE OUR OFFICE HOURS
        officeHours = new HashMap();
        // THESE ARE THE LANGUAGE-DEPENDENT OFFICE HOURS GRID HEADERS
       
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> timeHeaders = props.getPropertyOptionsList(CSGeneratorProp.OFFICE_HOURS_TABLE_HEADERS);
        ArrayList<String> dowHeaders = props.getPropertyOptionsList(CSGeneratorProp.DAYS_OF_WEEK);
        gridHeaders = new ArrayList();
        gridHeaders.addAll(timeHeaders);
        gridHeaders.addAll(dowHeaders);
        
        recitations = FXCollections.observableArrayList();
        
        scheduleItems = FXCollections.observableArrayList();
        
        teams = FXCollections.observableArrayList();
        students = FXCollections.observableArrayList();
    }

    @Override
    public void resetData() {
        //sitePages.clear();
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        officeHours.clear();
        recitations.clear();
        scheduleItems.clear();
        teams.clear();
        students.clear();
    }

    public CSGeneratorApp getApp() {
        return app;
    }

    public void setApp(CSGeneratorApp app) {
        this.app = app;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorHome() {
        return instructorHome;
    }

    public void setInstructorHome(String instructorHome) {
        this.instructorHome = instructorHome;
    }

    public String getExportDir() {
        return exportDir;
    }

    public void setExportDir(String exportDir) {
        this.exportDir = exportDir;
    }

    public String getTemplateDir() {
        return templateDir;
    }

    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    public ObservableList<SitePage> getSitePages() {
        return sitePages;
    }

    public void setSitePages(ObservableList<SitePage> sitePages) {
        this.sitePages = sitePages;
    }

    public String getBannerSchoolImage() {
        return bannerSchoolImage;
    }

    public void setBannerSchoolImage(String bannerSchoolImage) {
        this.bannerSchoolImage = bannerSchoolImage;
    }

    public String getLeftFooterImage() {
        return leftFooterImage;
    }

    public void setLeftFooterImage(String leftFooterImage) {
        this.leftFooterImage = leftFooterImage;
    }

    public String getRightFooterImage() {
        return rightFooterImage;
    }

    public void setRightFooterImage(String rightFooterImage) {
        this.rightFooterImage = rightFooterImage;
    }

    public String getStyleSheet() {
        return styleSheet;
    }

    public void setStyleSheet(String styleSheet) {
        this.styleSheet = styleSheet;
    }

    public ObservableList<TeachingAssistant> getTeachingAssistants() {
        return teachingAssistants;
    }

    public void setTeachingAssistants(ObservableList<TeachingAssistant> teachingAssistants) {
        this.teachingAssistants = teachingAssistants;
    }

    public HashMap<String, StringProperty> getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(HashMap<String, StringProperty> officeHours) {
        this.officeHours = officeHours;
    }

    public ArrayList<String> getGridHeaders() {
        return gridHeaders;
    }

    public void setGridHeaders(ArrayList<String> gridHeaders) {
        this.gridHeaders = gridHeaders;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public ObservableList<Recitation> getRecitations() {
        return recitations;
    }

    public void setRecitations(ObservableList<Recitation> recitations) {
        this.recitations = recitations;
    }

    public StringProperty getStartingMonday() {
        return startingMonday;
    }

    public void setStartingMonday(StringProperty startingMonday) {
        this.startingMonday = startingMonday;
    }

    public StringProperty getEndingFriday() {
        return endingFriday;
    }

    public void setEndingFriday(StringProperty endingFriday) {
        this.endingFriday = endingFriday;
    }

    public ObservableList<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

    public void setScheduleItems(ObservableList<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }

    public ObservableList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams = teams;
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }
    
    
}
