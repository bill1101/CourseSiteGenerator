/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.file;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.SitePage;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import static djf.settings.AppStartupConstants.PATH_DATA;
import static djf.settings.AppStartupConstants.PATH_HTML;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author tyx
 */
public class CSFiles implements AppFileComponent {
    CSGeneratorApp app;
    
    // THESE ARE USED FOR IDENTIFYING JSON TYPES
    static final String JSON_SUBJECT = "subject";
    static final String JSON_NUMBER = "number";
    static final String JSON_SEMESTER = "semester";
    static final String JSON_YEAR = "year";
    static final String JSON_TITLE = "title";
    static final String JSON_INSTRUCTOR_NAME = "instructorName";
    static final String JSON_INSTRUCTOR_HOME = "instructorHome";
    static final String JSON_EXPORT_DIR = "exportDir";
    static final String JSON_TEMPLATES_DIR = "template";
    static final String JSON_SITE_PAGES = "site_pages";
    static final String JSON_USE = "use";
    static final String JSON_NAVBAR_TITLE = "navbarTitle";
    static final String JSON_FILE_NAME = "fileName";
    static final String JSON_SCRIPT = "script";
    static final String JSON_BANNER_SCHOOL_IMAGE = "bannerSchoolImage";
    static final String JSON_LEFT_FOOTER_IMAGE = "leftFooterImage";
    static final String JSON_RIGHT_FOOTER_IMAGE = "rightFooterImage";
    static final String JSON_STYLESHEET = "stylesheet";
       
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_NAME = "name";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_EMAIL = "email";
    static final String JSON_UNDERGRAD = "undergrad";
    
    static final String JSON_RECITATIONS = "recitations";
    static final String JSON_SECTION = "section";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_DAYTIME = "day_time";
    static final String JSON_LOCATION = "location";
    static final String JSON_TA1 = "ta_1";
    static final String JSON_TA2 = "ta_2";
    
    static final String JSON_STARTING_MONDAY = "startingMonday";
    static final String JSON_ENDING_FRIDAY = "endingFriday";
    static final String JSON_SCHEDULE_ITEMS = "scheduleItems";
    static final String JSON_TYPE = "type";
    static final String JSON_DATE = "date";
    static final String JSON_SCHEDULE_TITLE = "title";
    static final String JSON_TOPIC = "topic";
    static final String JSON_SCHEDULE_LINK = "link";
    static final String JOSN_TIME = "time";
    static final String JSON_CRITERIA = "criteria";
    
    static final String JSON_TEAMS = "teams";
    static final String JSON_TEAM_NAME = "name";
    static final String JSON_COLOR = "color";
    static final String JSON_TEXT_COLOR = "text_color";
    static final String JSON_LINK = "link";
    static final String JSON_STUDENTS = "students";
    static final String JSON_FIRSTNAME = "firstName";
    static final String JSON_LASTNAME = "lastName";
    static final String JSON_TEAM = "team";
    static final String JSON_ROLE = "role";
    
    static final String JSON_STARTING_MONDAY_MONTH = "startingMondayMonth";
    static final String JSON_STARTING_MONDAY_DAY = "startingMondayDay";
    static final String JSON_ENDING_FRIDAY_MONTH = "endingFridayMonth";
    static final String JSON_ENDING_FRIDAY_DAY = "endingFridayDay";
    static final String JSON_HOLIDAYS = "holidays";
    static final String JSON_LECTURES = "lectures";
    static final String JSON_REFERENCES = "references";
    static final String JSON_HWS = "hws";
    
    static final String JSON_MONTH = "month";

    static final String JSON_PROJECTS = "projects";
    static final String JSON_WORK = "work";
    
    public CSFiles(CSGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        CSData dataManager = (CSData)data;

	// NOW BUILD THE TA JSON OBJCTS TO SAVE
        JsonArrayBuilder courseSiteArrayBuilder = Json.createArrayBuilder();
        ObservableList<SitePage> sitePages = dataManager.getSitePages();
        for(SitePage sp : sitePages) {
            JsonObject spJson = Json.createObjectBuilder()
                    .add(JSON_USE, ""+sp.getUse())
                    .add(JSON_NAVBAR_TITLE, sp.getNavBarTitle())
                    .add(JSON_FILE_NAME, sp.getFileName())
                    .add(JSON_SCRIPT, sp.getScript()).build();
            courseSiteArrayBuilder.add(spJson);
        }
        JsonArray sitePagesArray = courseSiteArrayBuilder.build();
        
        JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	    
	    JsonObject taJson = Json.createObjectBuilder()		    
                    .add(JSON_UNDERGRAD, ""+ta.getUndergrad())
                    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
        ObservableList<Recitation> recitations = dataManager.getRecitations();
        for (Recitation re : recitations) {
            JsonObject recitationJson = Json.createObjectBuilder()
                    .add(JSON_SECTION, re.getSection())
                    .add(JSON_INSTRUCTOR, re.getInstructor())
                    .add(JSON_DAYTIME, re.getDayTime())
                    .add(JSON_LOCATION, re.getLocation())
                    .add(JSON_TA1, re.getTA1())
                    .add(JSON_TA2, re.getTA2()).build();
            recitationArrayBuilder.add(recitationJson);
        }
        JsonArray recitationArray = recitationArrayBuilder.build();
        
        JsonArrayBuilder scheduleItemArrayBuilder = Json.createArrayBuilder();
        ObservableList<ScheduleItem> items = dataManager.getScheduleItems();
        for (ScheduleItem item : items) {
            JsonObject itemJson = Json.createObjectBuilder()
                    .add(JSON_TYPE, item.getType())
                    .add(JSON_DATE, item.getDate())
                    .add(JSON_SCHEDULE_TITLE, item.getTitle())
                    .add(JSON_TOPIC, item.getTopic())
                    .add(JSON_LINK, item.getLink())
                    .add(JSON_TIME, item.getTime())
                    .add(JSON_CRITERIA, item.getCriteria()).build();
            scheduleItemArrayBuilder.add(itemJson);
        }
        JsonArray scheduleItemArray = scheduleItemArrayBuilder.build();
        
        JsonArrayBuilder teamArrayBuilder = Json.createArrayBuilder();
        ObservableList<Team> teams = dataManager.getTeams();
        for (Team team : teams) {
            JsonObject teamJson = Json.createObjectBuilder()
                    .add(JSON_TEAM_NAME, team.getName())
                    .add(JSON_COLOR, team.getColor())
                    .add(JSON_TEXT_COLOR, team.getTextColor())
                    .add(JSON_LINK, team.getLink()).build();
            teamArrayBuilder.add(teamJson);
        }
        JsonArray teamArray = teamArrayBuilder.build();
        
        JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();
        ObservableList<Student> students = dataManager.getStudents();
        for (Student student : students) {
            JsonObject studentJson = Json.createObjectBuilder()
                    .add(JSON_FIRSTNAME, student.getFirstName())
                    .add(JSON_LASTNAME, student.getLastName())
                    .add(JSON_TEAM, student.getTeam())
                    .add(JSON_ROLE, student.getRole()).build();
            studentArrayBuilder.add(studentJson);
        }
        JsonArray studentArray = studentArrayBuilder.build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_SUBJECT, dataManager.getSubject())
                .add(JSON_NUMBER, dataManager.getNumber())
                .add(JSON_SEMESTER, dataManager.getSemester())
                .add(JSON_YEAR, dataManager.getYear())
                .add(JSON_TITLE, dataManager.getTitle())
                .add(JSON_INSTRUCTOR_NAME, dataManager.getInstructorName())
                .add(JSON_INSTRUCTOR_HOME, dataManager.getInstructorHome())
                .add(JSON_EXPORT_DIR, dataManager.getExportDir())
                .add(JSON_TEMPLATES_DIR, dataManager.getTemplateDir())
                .add(JSON_SITE_PAGES, sitePagesArray)
                .add(JSON_BANNER_SCHOOL_IMAGE, dataManager.getBannerSchoolImage())
                .add(JSON_LEFT_FOOTER_IMAGE, dataManager.getLeftFooterImage())
                .add(JSON_RIGHT_FOOTER_IMAGE, dataManager.getRightFooterImage())
                .add(JSON_STYLESHEET, dataManager.getStyleSheet())
                
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
                
                .add(JSON_RECITATIONS, recitationArray)
                
                .add(JSON_STARTING_MONDAY, dataManager.getStartingMonday())
                .add(JSON_ENDING_FRIDAY, dataManager.getEndingFriday())
                .add(JSON_SCHEDULE_ITEMS, scheduleItemArray)
                
                .add(JSON_TEAMS, teamArray)
                .add(JSON_STUDENTS, studentArray)
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        CSData dataManager = (CSData)data;
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
        
        dataManager.setSubject(json.getString(JSON_SUBJECT));
        dataManager.setNumber(json.getString(JSON_NUMBER));
        dataManager.setSemester(json.getString(JSON_SEMESTER));
        dataManager.setYear(json.getString(JSON_YEAR));
        dataManager.setTitle(json.getString(JSON_TITLE));
        dataManager.setInstructorName(json.getString(JSON_INSTRUCTOR_NAME));
        dataManager.setInstructorHome(json.getString(JSON_INSTRUCTOR_HOME));
        dataManager.setExportDir(json.getString(JSON_EXPORT_DIR));
        dataManager.setTemplateDir(json.getString(JSON_TEMPLATES_DIR));
        dataManager.setBannerSchoolImage(json.getString(JSON_BANNER_SCHOOL_IMAGE));
        dataManager.setLeftFooterImage(json.getString(JSON_LEFT_FOOTER_IMAGE));
        dataManager.setRightFooterImage(json.getString(JSON_RIGHT_FOOTER_IMAGE));
        dataManager.setStyleSheet(json.getString(JSON_STYLESHEET));
        JsonArray jsonSitePageArray = json.getJsonArray(JSON_SITE_PAGES);
        for (int i = 0; i < jsonSitePageArray.size(); i++) {
            JsonObject jsonSitePage = jsonSitePageArray.getJsonObject(i);
            Boolean use = Boolean.parseBoolean(jsonSitePage.getString(JSON_USE));
            String navbarTitle = jsonSitePage.getString(JSON_NAVBAR_TITLE);
            String fileName = jsonSitePage.getString(JSON_FILE_NAME);
            String script = jsonSitePage.getString(JSON_SCRIPT);
            dataManager.getSitePages().add(new SitePage(use,navbarTitle,fileName,script));
        }
        
	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
        
        dataManager.setStartingMonday(json.getString(JSON_STARTING_MONDAY));
        dataManager.setEndingFriday(json.getString(JSON_ENDING_FRIDAY));
        
        
        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            Boolean undergrad = Boolean.parseBoolean(jsonTA.getString(JSON_UNDERGRAD));
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            dataManager.addTA(undergrad,name, email);
        }
        if(app.getWorkspaceComponent() != null){
            dataManager.initHours(startHour, endHour);
            // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
            app.getWorkspaceComponent().reloadWorkspace(app.getDataComponent());
            // AND THEN ALL THE OFFICE HOURS
        }
        
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            dataManager.addOfficeHoursReservation(day, time, name);
        }
        
        JsonArray jsonRecitationArray = json.getJsonArray(JSON_RECITATIONS);
        for (int i = 0; i < jsonRecitationArray.size(); i++) {
            JsonObject jsonRecitation = jsonRecitationArray.getJsonObject(i);
            String section = jsonRecitation.getString(JSON_SECTION);
            String instructor = jsonRecitation.getString(JSON_INSTRUCTOR);
            String dayTime = jsonRecitation.getString(JSON_DAYTIME);
            String location = jsonRecitation.getString(JSON_LOCATION);
            String TA1 = jsonRecitation.getString(JSON_TA1);
            String TA2 = jsonRecitation.getString(JSON_TA2);
            dataManager.getRecitations().add(new Recitation(section,instructor,dayTime,location,TA1,TA2));
        }
              
        JsonArray jsonScheduleItemArray = json.getJsonArray(JSON_SCHEDULE_ITEMS);
        for(int i = 0; i < jsonScheduleItemArray.size(); i++) {
            JsonObject jsonScheduleItem = jsonScheduleItemArray.getJsonObject(i);
            String type = jsonScheduleItem.getString(JSON_TYPE);
            String date = jsonScheduleItem.getString(JSON_DATE);
            String title = jsonScheduleItem.getString(JSON_SCHEDULE_TITLE);
            String topic = jsonScheduleItem.getString(JSON_TOPIC);
            String link = jsonScheduleItem.getString(JSON_LINK);
            String time = jsonScheduleItem.getString(JSON_TIME);
            String criteria = jsonScheduleItem.getString(JSON_CRITERIA);
            dataManager.getScheduleItems().add(new ScheduleItem(type,date,title,topic,link,time,criteria));
        }
        
        JsonArray jsonTeamsArray = json.getJsonArray(JSON_TEAMS);
        for(int i = 0; i < jsonTeamsArray.size(); i++) {
            JsonObject jsonTeamItem = jsonTeamsArray.getJsonObject(i);
            String name = jsonTeamItem.getString(JSON_TEAM_NAME);
            String color = jsonTeamItem.getString(JSON_COLOR);
            String text_color = jsonTeamItem.getString(JSON_TEXT_COLOR);
            String link = jsonTeamItem.getString(JSON_LINK);
            dataManager.getTeams().add(new Team(name,color,text_color,link));
        }
        
        JsonArray jsonStudentsArray = json.getJsonArray(JSON_STUDENTS);
        for(int i = 0; i < jsonStudentsArray.size(); i++) {
            JsonObject jsonStudent = jsonStudentsArray.getJsonObject(i);
            String firstName = jsonStudent.getString(JSON_FIRSTNAME);
            String lastName = jsonStudent.getString(JSON_LASTNAME);
            String team = jsonStudent.getString(JSON_TEAM);
            String role = jsonStudent.getString(JSON_ROLE);
            dataManager.getStudents().add(new Student(firstName,lastName,team,role));
        }
        
        
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        File source = new File(PATH_HTML);
        String s = source.getAbsolutePath();
        File destination = new File(filePath);
        copyDirectory(source, destination);
        
        String jsonDir = destination.getPath() + "/public_html/js/";
        //Generate CourseInfoData.json
        exportCourseInfoData(data, jsonDir+"CourseInfoData.json");
        exportOfficeHoursGridData(data, jsonDir+"OfficeHoursGridData.json");
        exportRecitationsData(data, jsonDir+"RecitationsData.json");
        exportScheduleData(data, jsonDir+"ScheduleData.json");
        exportPorjectsData(data, jsonDir+"ProjectsData.json");
        exportTeamsAndStudent(data, jsonDir+"TeamsAndStudents.json");
    }

    public void exportCourseInfoData(AppDataComponent data, String filePath) throws FileNotFoundException{
        CSData dataManager = (CSData)data;
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_SUBJECT, dataManager.getSubject())
                .add(JSON_NUMBER, dataManager.getNumber())
                .add(JSON_SEMESTER, dataManager.getSemester())
                .add(JSON_YEAR, dataManager.getYear())
                .add(JSON_TITLE, dataManager.getTitle())
                .add(JSON_INSTRUCTOR_NAME, dataManager.getInstructorName())
                .add(JSON_INSTRUCTOR_HOME, dataManager.getInstructorHome())
                .add(JSON_BANNER_SCHOOL_IMAGE, dataManager.getBannerSchoolImage())
                .add(JSON_LEFT_FOOTER_IMAGE, dataManager.getLeftFooterImage())
                .add(JSON_RIGHT_FOOTER_IMAGE, dataManager.getRightFooterImage())
                .add(JSON_STYLESHEET, dataManager.getStyleSheet())
                .add("home", ""+dataManager.getSitePages().get(0).getUse())
                .add("syllabus", ""+dataManager.getSitePages().get(1).getUse())
                .add("schedule", ""+dataManager.getSitePages().get(2).getUse())
                .add("HWs", ""+dataManager.getSitePages().get(3).getUse())
                .add("projects", ""+dataManager.getSitePages().get(4).getUse()).build();
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void exportOfficeHoursGridData(AppDataComponent data, String filePath) throws FileNotFoundException{
        CSData dataManager = (CSData)data;
        
        JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	    
	    JsonObject taJson = Json.createObjectBuilder()		    
                    .add(JSON_UNDERGRAD, ""+ta.getUndergrad())
                    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray).build();
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void exportRecitationsData(AppDataComponent data, String filePath) throws FileNotFoundException{
        CSData dataManager = (CSData)data;
        
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
        ObservableList<Recitation> recitations = dataManager.getRecitations();
        for (Recitation re : recitations) {
            JsonObject recitationJson = Json.createObjectBuilder()
                    .add(JSON_SECTION, "<strong>" + re.getSection() + "</strong> (" + re.getInstructor() + ")")
                    //.add(JSON_INSTRUCTOR, re.getInstructor())
                    .add(JSON_DAYTIME, re.getDayTime())
                    .add(JSON_LOCATION, re.getLocation())
                    .add(JSON_TA1, re.getTA1())
                    .add(JSON_TA2, re.getTA2()).build();
            recitationArrayBuilder.add(recitationJson);
        }
        JsonArray recitationArray = recitationArrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_RECITATIONS, recitationArray).build();
                
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void exportScheduleData(AppDataComponent data, String filePath) throws FileNotFoundException {
        CSData dataManager = (CSData)data;
        
        JsonArrayBuilder holidaysArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder lecturesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder referencesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder recitationsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder hwsArrayBuilder = Json.createArrayBuilder();
        
        ObservableList<ScheduleItem> items = dataManager.getScheduleItems();
        for (ScheduleItem item : items) {
            String month = ""+Integer.parseInt(item.getDate().split("/")[0]);
            String day = ""+Integer.parseInt(item.getDate().split("/")[1]);
            if(item.getType().equals("Holiday")){
                JsonObject itemJson = Json.createObjectBuilder()
                        .add(JSON_MONTH, month)
                        .add(JSON_DAY, day)
                        .add(JSON_SCHEDULE_TITLE, item.getTitle())
                        .add(JSON_LINK, item.getLink()).build();
                holidaysArrayBuilder.add(itemJson);
            }else if(item.getType().equals("Lecture")){
                JsonObject itemJson = Json.createObjectBuilder()
                        .add(JSON_MONTH, month)
                        .add(JSON_DAY, day)
                        .add(JSON_SCHEDULE_TITLE, item.getTitle())
                        .add(JSON_TOPIC, item.getTopic())
                        .add(JSON_LINK, item.getLink()).build();
                lecturesArrayBuilder.add(itemJson);
            }else if(item.getType().equals("Reference")){
                JsonObject itemJson = Json.createObjectBuilder()
                        .add(JSON_MONTH, month)
                        .add(JSON_DAY, day)
                        .add(JSON_SCHEDULE_TITLE, item.getTitle())
                        .add(JSON_TOPIC, item.getTopic())
                        .add(JSON_LINK, item.getLink()).build();
                referencesArrayBuilder.add(itemJson);
            }else if(item.getType().equals("Recitation")){
                JsonObject itemJson = Json.createObjectBuilder()
                        .add(JSON_MONTH, month)
                        .add(JSON_DAY, day)
                        .add(JSON_SCHEDULE_TITLE, item.getTitle())
                        .add(JSON_TOPIC, item.getTopic()).build();
                recitationsArrayBuilder.add(itemJson);
            }else if(item.getType().equals("HW")){
                JsonObject itemJson = Json.createObjectBuilder()
                        .add(JSON_MONTH, month)
                        .add(JSON_DAY, day)
                        .add(JSON_SCHEDULE_TITLE, item.getTitle())
                        .add(JSON_TOPIC, item.getTopic())
                        .add(JSON_LINK, item.getLink())
                        .add(JSON_TIME, item.getTime())
                        .add(JSON_CRITERIA, item.getCriteria()).build();
                hwsArrayBuilder.add(itemJson);
            }
        }
        JsonArray holidaysArray = holidaysArrayBuilder.build();
        JsonArray lecturesArray = lecturesArrayBuilder.build();
        JsonArray referencesArray = referencesArrayBuilder.build();
        JsonArray recitationsArray = recitationsArrayBuilder.build();
        JsonArray hwsArray = hwsArrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_STARTING_MONDAY_MONTH, ""+Integer.parseInt(dataManager.getStartingMonday().split("-")[1]))
                .add(JSON_STARTING_MONDAY_DAY, ""+Integer.parseInt(dataManager.getStartingMonday().split("-")[2]))
                .add(JSON_ENDING_FRIDAY_MONTH, ""+Integer.parseInt(dataManager.getEndingFriday().split("-")[1]))
                .add(JSON_ENDING_FRIDAY_DAY, ""+Integer.parseInt(dataManager.getEndingFriday().split("-")[2]))
                .add(JSON_HOLIDAYS, holidaysArray)
                .add(JSON_LECTURES, lecturesArray)
                .add(JSON_REFERENCES, referencesArray)
                .add(JSON_RECITATIONS, recitationsArray)
                .add(JSON_HWS, hwsArray)
                .build();
                
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void exportPorjectsData(AppDataComponent data, String filePath) throws FileNotFoundException {
        CSData dataManager = (CSData)data;
        
        JsonArrayBuilder projectsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
        ObservableList<Student> students = dataManager.getStudents();
	for (Team team : teams) {
            JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();
            for(Student student: students){
                if(student.getTeam().equals(team.getName())){
                    studentsArrayBuilder.add(student.getFirstName()+ " " +student.getLastName());
                }
            }
            JsonArray studentsArray = studentsArrayBuilder.build();
	    JsonObject projectJson = Json.createObjectBuilder()		    
                    .add(JSON_NAME, team.getName())
                    .add(JSON_STUDENTS, studentsArray)
                    .add(JSON_LINK, team.getLink())
                    .build();
	    projectsArrayBuilder.add(projectJson);
	}
	JsonArray projectsArray = projectsArrayBuilder.build();
        
        JsonObject workJson = Json.createObjectBuilder()
                .add(JSON_SEMESTER, dataManager.getSemester() + " " + dataManager.getYear())
                .add(JSON_PROJECTS, projectsArray).build();
        
        JsonArrayBuilder workArrayBuilder = Json.createArrayBuilder()
                .add(workJson);
        JsonArray workArray = workArrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_WORK, workArray).build();
        

        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    public void exportTeamsAndStudent(AppDataComponent data, String filePath) throws FileNotFoundException {
        CSData dataManager = (CSData)data;
        
        JsonArrayBuilder teamArrayBuilder = Json.createArrayBuilder();
        ObservableList<Team> teams = dataManager.getTeams();
        for (Team team : teams) {
            JsonObject teamJson = Json.createObjectBuilder()
                    .add(JSON_TEAM_NAME, team.getName())
                    .add(JSON_COLOR, "#"+team.getColor().substring(2, team.getColor().length()-2))
                    .add(JSON_TEXT_COLOR, "#"+team.getTextColor().substring(2, team.getTextColor().length()-2)).build();
            teamArrayBuilder.add(teamJson);
        }
        JsonArray teamArray = teamArrayBuilder.build();
        
        JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();
        ObservableList<Student> students = dataManager.getStudents();
        for (Student student : students) {
            JsonObject studentJson = Json.createObjectBuilder()
                    .add(JSON_LASTNAME, student.getLastName())
                    .add(JSON_FIRSTNAME, student.getFirstName())                    
                    .add(JSON_TEAM, student.getTeam())
                    .add(JSON_ROLE, student.getRole()).build();
            studentArrayBuilder.add(studentJson);
        }
        JsonArray studentArray = studentArrayBuilder.build();
        
        // THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_TEAMS, teamArray)
                .add(JSON_STUDENTS, studentArray)
		.build();
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void copyDirectory(File sourceLocation , File targetLocation) throws IOException {

//        if(sourceLocation.getPath().endsWith("OfficeHoursGridData.json")){
//            String path = targetLocation.getPath();
//            //System.out.println(path);
//            app.getFileComponent().saveData(app.getDataComponent(), path);
//        }
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }
}
