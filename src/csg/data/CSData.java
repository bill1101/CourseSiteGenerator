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
import java.util.Collections;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
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
    String title;
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
    public static final int MIN_START_HOUR = 9;
    public static final int MAX_END_HOUR = 13;
    
    ///////////////////////////////////////////////////////////////////Recitation tab
    ObservableList<Recitation> recitations;
    
    ///////////////////////////////////////////////////////////////////Schedule tab
    String startingMonday;
    String endingFriday;
    ObservableList<ScheduleItem> scheduleItems;
    
    ///////////////////////////////////////////////////////////////////Project tab
    ObservableList<Team> teams;
    ObservableList<Student> students;
    
    public CSData(CSGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;

        sitePages = FXCollections.observableArrayList();
        
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
        //startingMonday = new SimpleStringProperty();
        //endingFriday = new SimpleStringProperty();
        
        teams = FXCollections.observableArrayList();
        students = FXCollections.observableArrayList();
    }
    
    

    @Override
    public void resetData() {
        sitePages.clear();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    ///////////////////TA
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
    
    public String getCellKey(int col, int row) {
        return col + "_" + row;
    }

    public StringProperty getCellTextProperty(int col, int row) {
        String cellKey = getCellKey(col, row);
        return officeHours.get(cellKey);
    }

    public int getNumRows() {
        return ((endHour - startHour) * 2) + 1;
    }

    public String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
    public String getCellKey(String day, String time) {
        int col = gridHeaders.indexOf(day);
        int row = 1;
        
        int hour = Integer.parseInt(time.substring(0, time.indexOf("_")));
        int milHour = hour;
        //if (hour < startHour)
        if(time.contains("pm") && hour!=12)
            milHour += 12;
        //System.out.println(milHour + " " + startHour);
        row += (milHour - startHour) * 2;
        if (time.contains("_30"))
            row += 1;
        //
        //String am_pm = time.substring(time.length()-2,time.length());
        //System.out.println("time: "+time);
        
        return getCellKey(col, row);       
    }
    
    public TeachingAssistant getTA(String testName) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return ta;
            }
        }
        return null;
    }
    
    /**
     * This method is for giving this data manager the string property
     * for a given cell.
     */
    public void setCellProperty(int col, int row, StringProperty prop) {
        String cellKey = getCellKey(col, row);
        officeHours.put(cellKey, prop);
    }    
    
    /**
     * This method is for setting the string property for a given cell.
     */
    public void setGridProperty(ArrayList<ArrayList<StringProperty>> grid,
                                int column, int row, StringProperty prop) {
        grid.get(row).set(column, prop);
    }
    

    
    private void initOfficeHours(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;
        
        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();
            
        // WE'LL BUILD THE USER INTERFACE COMPONENT FOR THE
        // OFFICE HOURS GRID AND FEED THEM TO OUR DATA
        // STRUCTURE AS WE GO
        CSWorkspace workspaceComponent = (CSWorkspace)app.getWorkspaceComponent();
        //todo ..workspaceComponent.reloadOfficeHoursGrid(this);
    }
    
    
    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if ((initStartHour >= MIN_START_HOUR)
                && (initEndHour <= MAX_END_HOUR)
                && (initStartHour <= initEndHour)) {
            // THESE ARE VALID HOURS SO KEEP THEM
            initOfficeHours(initStartHour, initEndHour);
        }
    }



    public boolean containsTA(String testName, String testEmail) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return true;
            }
            if (ta.getEmail().equals(testEmail)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsName(String testName){
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsEmail(String testEmail){
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getEmail().equals(testEmail)) {
                return true;
            }
        }
        return false;
    }
    
    public void editTA(String initName, String name, String email) {
        TeachingAssistant ta = getTA(initName);
        if(ta!=null){
            //System.out.println(ta.getName());
            //System.out.println(ta.getEmail());
            ta.setName(name);
            ta.setEmail(email);
            //addTA(name,email);
            //System.out.println(ta.getName());
            //System.out.println(ta.getEmail());
            //removeTA(initName);
            //addTA(name,email);
        }
        
        Collections.sort(teachingAssistants);
    }

    public void addTA(String initName, String initEmail) {
        // MAKE THE TA
        TeachingAssistant ta = new TeachingAssistant(initName, initEmail);

        // ADD THE TA
        if (!containsTA(initName, initEmail)) {
            teachingAssistants.add(ta);            
        }

        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }
    
    public void addTA(Boolean undergrad, String initName, String initEmail) {
        // MAKE THE TA
        TeachingAssistant ta = new TeachingAssistant(initName, initEmail, undergrad);

        // ADD THE TA
        if (!containsTA(initName, initEmail)) {
            teachingAssistants.add(ta);
        }

        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }
    
    public void removeTA(String name) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (name.equals(ta.getName())) {
                teachingAssistants.remove(ta);
                return;
            }
        }
    }
    
    public void addOfficeHoursReservation(String day, String time, String taName) {
        String cellKey = getCellKey(day, time);
        toggleTAOfficeHours(cellKey, taName);
    }
    
    public void replaceTAOfficeHours(String cellKey, String oldName, String newName){
        StringProperty cellProp = officeHours.get(cellKey);
        String cellText = cellProp.getValue();

        cellProp.setValue(cellProp.getValue().replaceAll(oldName, newName));
    }

    /**
     * This function toggles the taName in the cell represented
     * by cellKey. Toggle means if it's there it removes it, if
     * it's not there it adds it.
     */
    public void toggleTAOfficeHours(String cellKey, String taName) {
        StringProperty cellProp = officeHours.get(cellKey);       
        String cellText = cellProp.getValue();
        // IF IT ALREADY HAS THE TA, REMOVE IT
        if (cellText.contains(taName)) {
            removeTAFromCell(cellProp, taName);
        } // OTHERWISE ADD IT
        else if (cellText.length() == 0) {
            cellProp.setValue(taName);
        } else {
            cellProp.setValue(cellText + "\n" + taName);
        }
    }
    
    /**
     * This method removes taName from the office grid cell
     * represented by cellProp.
     */
    public void removeTAFromCell(StringProperty cellProp, String taName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            cellProp.setValue("");
        }
        // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = cellText.indexOf("\n") + 1;
            cellText = cellText.substring(startIndex);
            cellProp.setValue(cellText);
        }
        // IS IT IN THE MIDDLE OF A LIST OF TAs
        else if (cellText.indexOf(taName) < cellText.indexOf("\n", cellText.indexOf(taName))) {
            int startIndex = cellText.indexOf("\n" + taName);
            int endIndex = startIndex + taName.length() + 1;
            cellText = cellText.substring(0, startIndex) + cellText.substring(endIndex);
            cellProp.setValue(cellText);
        }
        // IT MUST BE THE LAST TA
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            cellText = cellText.substring(0, startIndex);
            cellProp.setValue(cellText);
        }
    }
    
    public boolean containsRecitation(String section) {
        for(Recitation recitation: recitations){
            if(recitation.getSection().equals(section)){
                return true;
            }
        }
        return false;
    }
    
    public void addRecitation(String section, String instructor, String dayTime, String location, String ta1, String ta2) {
        Recitation recitation = new Recitation(section,instructor,dayTime,location,ta1,ta2);
        if(!containsRecitation(section)){
            recitations.add(recitation);
        }
        Collections.sort(recitations);
    }

    public void removeRecitation(String section) {
        for(Recitation recitation: recitations){
            if(recitation.getSection().equals(section)){
                recitations.remove(recitation);
                return;
            }
        }
    }
    
    public void editRecitation(String initSection, String section, String instructor, String dayTime, String location, String TA1, String TA2) {
        Recitation recitation = getRecitation(initSection);
        if(recitation!=null){
            recitation.setSection(section);
            recitation.setInstructor(instructor);
            recitation.setDayTime(dayTime);
            recitation.setLocation(location);
            recitation.setTA1(TA1);
            recitation.setTA2(TA2);
        }
        
        Collections.sort(recitations);
    }
    
    public Recitation getRecitation(String initSection) {
        for(Recitation recitation:recitations){
            if(recitation.getSection().equals(initSection))
                return recitation;
        }
        return null;
    }
    
    public boolean containsSchedule(String type, String date) {
        for(ScheduleItem si: scheduleItems){
            if(si.getType().equals(type) && si.getDate().equals(date)){
                return true;
            }
        }
        return false;
    }

    public void addSchedule(String type, String date, String time, String title, String topic, String link, String criteria) {
        ScheduleItem si = new ScheduleItem(type,date,time,title,topic,link,criteria);
        if(!containsSchedule(type,date)){
            scheduleItems.add(si);
        }
        Collections.sort(scheduleItems);
    }

    public void removeSchedule(String type, String date){
        for(ScheduleItem si: scheduleItems){
            if(si.getType().equals(type) && si.getDate().equals(date)){
                scheduleItems.remove(si);
                return;
            }
        }
    }
        
    public void editSchedule(String initType, String initDate, String type, String date, String time, String title, String topic, String link, String criteria) {
        ScheduleItem si = getScheduleItem(initType, initDate);
        if(si != null){           
            si.setType(type);
            si.setDate(date);
            si.setTime(time);
            si.setTitle(title);
            si.setTopic(topic);
            si.setLink(link);
            si.setCriteria(criteria);
        }
        Collections.sort(scheduleItems);
    }
    
    public ScheduleItem getScheduleItem(String initType, String initDate){
        for(ScheduleItem si: scheduleItems){
            if(si.getType().equals(initType) && si.getDate().equals(initDate)){
                return si;
            }
        }
        return null;
    }
    
    public boolean containsTeam(String name) {
        for(Team team: teams){
            if(team.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public void addTeam(String name, String color, String textColor, String link) {
        Team team = new Team(name,color,textColor,link);
        if(!containsTeam(name)){
            teams.add(team);
            
        }
        Collections.sort(teams);
    }

    public void removeTeam(String name) {
        for(Team team:teams){
            if(team.getName().equals(name)){
                teams.remove(team);
                return;
            }
        }
    }
      
    public void editTeam(String initName, String name, String color, String textColor, String link) {
        Team team = getTeam(initName);
        if(team != null){
            team.setName(name);
            team.setColor(color);
            team.setTextColor(textColor);
            team.setLink(link);
        }      
        Collections.sort(teams);        
    }
    
    private Team getTeam(String initName) {
        for(Team team:teams){
            if(team.getName().equals(initName)){
                return team;
            }
        }
        return null;
    }
    
    public boolean containsStudent(String firstName,String lastName) {
        for(Student student: students){
            if(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))
                return true;
        }
        return false;
    }
  
    public void addStudent(String firstName, String lastName, String team, String role) {
        Student student = new Student(firstName,lastName,team,role);
        if(!containsStudent(firstName,lastName)){
            students.add(student);
        }
        Collections.sort(students);
    }

    public void removeStudent(String firstName, String lastName) {
        for(Student student:students){
            if(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)){
                students.remove(student);
                return;
            }
        }
    }

    public void editStudent(String initFirstName, String initLastName, String firstName, String lastName, String team, String role) {
        Student student = getStudent(initFirstName, initLastName);
        if(student != null){
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setTeam(team);
            student.setRole(role);
        }
        Collections.sort(students);
    }

    public Student getStudent(String firstName, String lastName){
        for(Student student: students){
            if(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)){
                return student;
            }
        }
        return null;
    }
    ///recitation
    public ObservableList<Recitation> getRecitations() {
        return recitations;
    }

    public void setRecitations(ObservableList<Recitation> recitations) {
        this.recitations = recitations;
    }

    public String getStartingMonday() {
        return startingMonday;
    }

    public void setStartingMonday(String startingMonday) {
        this.startingMonday = startingMonday;
    }

    public String getEndingFriday() {
        return endingFriday;
    }

    public void setEndingFriday(String endingFriday) {
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
