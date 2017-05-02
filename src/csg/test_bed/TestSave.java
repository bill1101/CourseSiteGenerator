/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.test_bed;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.SitePage;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.file.CSFiles;
import djf.components.AppDataComponent;
import djf.settings.AppStartupConstants;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tyx
 */
public class TestSave{
    public static void main(String[] args) throws IOException{
        CSGeneratorApp csgenerator = new CSGeneratorApp();
        csgenerator.loadProperties(APP_PROPERTIES_FILE_NAME);
        AppStartupConstants.CLOSE_BUTTON_LABEL = "Close";
        AppYesNoCancelDialogSingleton.YES = "Yes";
        AppYesNoCancelDialogSingleton.CANCEL = "Cancel";
        
        CSData csData = new CSData(csgenerator);
        csData.getOfficeHours().put("0_0", new SimpleStringProperty("start time"));
        csData.getOfficeHours().put("1_0", new SimpleStringProperty("end time"));
        csData.getOfficeHours().put("2_0", new SimpleStringProperty("MONDAY"));
        csData.getOfficeHours().put("3_0", new SimpleStringProperty("TUESDAY"));
        csData.getOfficeHours().put("4_0", new SimpleStringProperty("WEDNESDAY"));
        csData.getOfficeHours().put("5_0", new SimpleStringProperty("THURSDAY"));
        csData.getOfficeHours().put("6_0", new SimpleStringProperty("FRIDAY"));
        
        csData.getOfficeHours().put("0_1", new SimpleStringProperty("9:00am"));
        csData.getOfficeHours().put("0_2", new SimpleStringProperty("9:30am"));
        csData.getOfficeHours().put("0_3", new SimpleStringProperty("10:00am"));
        csData.getOfficeHours().put("0_4", new SimpleStringProperty("10:30am"));
        csData.getOfficeHours().put("0_5", new SimpleStringProperty("11:00am"));
        csData.getOfficeHours().put("0_6", new SimpleStringProperty("11:30am"));
        csData.getOfficeHours().put("0_7", new SimpleStringProperty("12:00pm"));
        csData.getOfficeHours().put("0_8", new SimpleStringProperty("12:30pm"));
        
        csData.getOfficeHours().put("1_1", new SimpleStringProperty("9:30am"));
        csData.getOfficeHours().put("1_1", new SimpleStringProperty("10:00am"));
        csData.getOfficeHours().put("1_3", new SimpleStringProperty("10:30am"));
        csData.getOfficeHours().put("1_4", new SimpleStringProperty("11:00am"));
        csData.getOfficeHours().put("1_5", new SimpleStringProperty("11:30am"));
        csData.getOfficeHours().put("1_6", new SimpleStringProperty("12:00pm"));
        csData.getOfficeHours().put("1_7", new SimpleStringProperty("12:30pm"));       
        csData.getOfficeHours().put("1_8", new SimpleStringProperty("13:00pm"));
        
        //2_1 --  6_8
        for(int i=2;i<=6;i++){
            for(int j=1;j<=8;j++){
                String key = i+"_"+j;
                    csData.getOfficeHours().put(key, new SimpleStringProperty(""));
            }
        }
        csData.getOfficeHours().put("3_1", new SimpleStringProperty("Joe Shmo"));
        csData.getOfficeHours().put("3_2", new SimpleStringProperty("Joe Shmo"));
        csData.getOfficeHours().put("5_1", new SimpleStringProperty("Joe Shmo"));
        csData.getOfficeHours().put("5_2", new SimpleStringProperty("Joe Shmo"));
        csData.getOfficeHours().put("2_3", new SimpleStringProperty("Jane Doe"));
        csData.getOfficeHours().put("2_4", new SimpleStringProperty("Jane Doe"));
        csData.getOfficeHours().put("4_5", new SimpleStringProperty("Jane Doe"));
        csData.getOfficeHours().put("4_6", new SimpleStringProperty("Jane Doe"));
        csData.getOfficeHours().put("4_7", new SimpleStringProperty("Jane Doe"));
        csData.getOfficeHours().put("6_7", new SimpleStringProperty("Jane Doe"));
        csData.getOfficeHours().put("6_8", new SimpleStringProperty("Jane Doe"));
        hardCodeData(csData);
        CSFiles csFiles = new CSFiles(csgenerator);
        csFiles.saveData(csData, "./work/SiteSaveTest.json");     
    }

    public static void hardCodeData(AppDataComponent appData){
        CSData data = (CSData)appData;
        data.setSubject("CSE");
        data.setNumber("219");
        data.setSemester("Fall");
        data.setYear("2017");
        data.setTitle("Computer Science III");
        data.setInstructorName("Richard McKenna");
        data.setInstructorHome("https://www.cs.stonybrook.edu/~richard");
        data.setExportDir("..\\..\\Course\\CSE219\\Summer2017\\public");
        data.setTemplateDir(".\\templates\\CSE219");
        data.getSitePages().add(new SitePage(true,"Home","index.html","HomeBuilder.js"));
        data.getSitePages().add(new SitePage(true,"Syllabus","syllabus.html","SyllabusBuilder.js"));
        data.getSitePages().add(new SitePage(true,"Schedule","schedule.html","ScheduleBuilder.js"));
        data.getSitePages().add(new SitePage(true,"HWs","hws.html","HWsBuilder.js"));
        data.getSitePages().add(new SitePage(true,"Projects","projects.html","ProjectsBuilder.js"));
        data.setBannerSchoolImage("SBUDarkRedShieldLogo.png");
        data.setLeftFooterImage("SBUWhiteShieldLogo.jpg");
        data.setRightFooterImage("CSLogo.png");
        data.setStyleSheet("sea_wolf.css");
        data.getTeachingAssistants().add(new TeachingAssistant("Jane Doe","jane.doe@yahoo.com",true));
        data.getTeachingAssistants().add(new TeachingAssistant("Joe Shmo","joe.shmo@yale.edu",false));
        
        
        //data.toggleTAOfficeHours("2_1", "Joe Shmo");
        
        data.getRecitations().add(new Recitation("R02","Mckenna","Wed 3:30pm-4:23pm","Old CS 2114","Jane Doe","Joe Shmo"));
        data.getRecitations().add(new Recitation("R05","Banerjee","Tues 5:30pm-6:23pm","Old CS 2114","",""));
        
        data.setStartingMonday("2017-01-23");
        data.setEndingFriday("2017-05-19");
        data.getScheduleItems().add(new ScheduleItem("Holiday","2/9/2017","SNOW DAY","","http://funnybizblog.com","",""));
        data.getScheduleItems().add(new ScheduleItem("Lecture","2/14/2017","Lecture 3","Event Programming","","",""));
        data.getScheduleItems().add(new ScheduleItem("Holiday","3/13/2017","Spring Break","","","",""));
        data.getScheduleItems().add(new ScheduleItem("HW","3/27/2017","HW3","UML","","11:59pm",""));
        
        data.getTeams().add(new Team("Atomic Comics","0x552211ff","0xffffffff","http://atomicomic.com"));
        data.getTeams().add(new Team("C4 Comics","0x235399ff","0xffffffff","http://c4-comics.appspot.com"));
        
        data.getStudents().add(new Student("Beau","Brummell","Atomic Comics","Lead Designer"));
        data.getStudents().add(new Student("Jane","Doe","C4 Comics","Lead Programmer"));
        data.getStudents().add(new Student("Noonian","Soong","Atomic Comics","Data Designer"));
    }
}
