/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import csg.data.CSData;
import csg.data.Recitation;
import csg.data.SitePage;
import csg.data.TeachingAssistant;
import csg.file.CSFiles;
import djf.settings.AppStartupConstants;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tyx
 */
public class CSGeneratorAppTest {
    
    CSGeneratorApp csgenerator;
    CSData csData;
    CSFiles csFiles;
    public CSGeneratorAppTest() {
        initialize();
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    public void initialize() {
        try {
            csgenerator = new CSGeneratorApp();
            csgenerator.loadProperties(APP_PROPERTIES_FILE_NAME);
            AppStartupConstants.CLOSE_BUTTON_LABEL = "Close";
            AppYesNoCancelDialogSingleton.YES = "Yes";
            AppYesNoCancelDialogSingleton.CANCEL = "Cancel";
            csData = new CSData(csgenerator);            
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
            csData.getOfficeHours().put("0_7", new SimpleStringProperty("11:30am"));
            csData.getOfficeHours().put("0_8", new SimpleStringProperty("12:30am"));        
            csData.getOfficeHours().put("1_1", new SimpleStringProperty("9:30am"));
            csData.getOfficeHours().put("1_1", new SimpleStringProperty("10:00am"));
            csData.getOfficeHours().put("1_3", new SimpleStringProperty("10:30am"));
            csData.getOfficeHours().put("1_4", new SimpleStringProperty("11:00am"));
            csData.getOfficeHours().put("1_5", new SimpleStringProperty("11:30am"));
            csData.getOfficeHours().put("1_6", new SimpleStringProperty("11:30am"));
            csData.getOfficeHours().put("1_7", new SimpleStringProperty("12:30am"));
            csData.getOfficeHours().put("1_8", new SimpleStringProperty("13:00am"));
            for(int i=2;i<=6;i++){
                for(int j=1;j<=8;j++){
                    String key = i+"_"+j;
                    //if(csData.getOfficeHours().get(key) == null)
                        csData.getOfficeHours().put(key, new SimpleStringProperty(""));
                }
            }
            //System.out.println(csData.getOfficeHours());
            csFiles = new CSFiles(csgenerator);
            csFiles.loadData(csData, "./work/hardcode2.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testSubject() {
        assertEquals("CSE", csData.getSubject());       
    }
    @Test
    public void testNumber() {
        assertEquals("219", csData.getNumber());
    }
    @Test
    public void testSemester() {
        assertEquals("Fall",csData.getSemester());
    }
    @Test
    public void testYear() {
        assertEquals("2017",csData.getYear());
    }
    @Test
    public void testTitle() {
        assertEquals("Computer Science III",csData.getTitle());
    }
    @Test
    public void testInstructorName() {
        assertEquals("Richard McKenna",csData.getInstructorName());
    }
    @Test
    public void testInstructorHome() {
        assertEquals("https://www.cs.stonybrook.edu/~richard",csData.getInstructorHome());
    }
    @Test
    public void testExportDir() {
        assertEquals("..\\..\\Course\\CSE219\\Summer2017\\public",csData.getExportDir());
    }
    @Test
    public void testTempleate() {
        assertEquals(".\\templates\\CSE219",csData.getTemplateDir());
    }
    @Test
    public void testSitePages() {
        ObservableList<SitePage> sitePages = csData.getSitePages();
        assertEquals(true,sitePages.get(0).getUse());
        assertEquals("Home",sitePages.get(0).getNavBarTitle());
        assertEquals("index.html",sitePages.get(0).getFileName());
        assertEquals("HomeBuilder.js",sitePages.get(0).getScript());
        assertEquals(true,sitePages.get(1).getUse());
        assertEquals("Syllabus",sitePages.get(1).getNavBarTitle());
        assertEquals("syllabus.html",sitePages.get(1).getFileName());
        assertEquals("SyllabusBuilder.js",sitePages.get(1).getScript());
        assertEquals(true,sitePages.get(2).getUse());
        assertEquals("Schedule",sitePages.get(2).getNavBarTitle());
        assertEquals("schedule.html",sitePages.get(2).getFileName());
        assertEquals("ScheduleBuilder.js",sitePages.get(2).getScript());
        assertEquals(true,sitePages.get(3).getUse());
        assertEquals("HWs",sitePages.get(3).getNavBarTitle());
        assertEquals("hws.html",sitePages.get(3).getFileName());
        assertEquals("HWsBuilder.js",sitePages.get(3).getScript());
        assertEquals(true,sitePages.get(4).getUse());
        assertEquals("Projects",sitePages.get(4).getNavBarTitle());
        assertEquals("projects.html",sitePages.get(4).getFileName());
        assertEquals("ProjectsBuilder.js",sitePages.get(4).getScript());
    }
    @Test
    public void testBannerImage() {
        assertEquals("SBUDarkRedShieldLogo.png",csData.getBannerSchoolImage());
    }
    @Test
    public void testLeftFooter() {
        assertEquals("SBUWhiteShieldLogo.jpg",csData.getLeftFooterImage());
    }
    @Test
    public void testRightFooter() {
        assertEquals("CSLogo.png",csData.getRightFooterImage());
    }
    @Test
    public void testStylesheet() {
        assertEquals("sea_wolf.css",csData.getStyleSheet());
    }
    @Test
    public void testStartHour() {
        assertEquals(9,csData.getStartHour());
    }
    @Test
    public void testEndHour() {
        assertEquals(13,csData.getEndHour());
    }
    @Test
    public void testUndergrad_tas() {
        ObservableList<TeachingAssistant> tas = csData.getTeachingAssistants();
        assertEquals(true,tas.get(0).getUndergrad());
        assertEquals("Jane Doe",tas.get(0).getName());
        assertEquals("jane.doe@yahoo.com",tas.get(0).getEmail());
        assertEquals(false,tas.get(1).getUndergrad());
        assertEquals("Joe Shmo",tas.get(1).getName());
        assertEquals("joe.shmo@yale.edu",tas.get(1).getEmail());
    }
    @Test
    public void testOfficeHours() {
        assertEquals("Jane Doe",csData.getOfficeHours().get("2_3").get());
        assertEquals("Jane Doe",csData.getOfficeHours().get("2_4").get());
        assertEquals("Joe Shmo",csData.getOfficeHours().get("3_1").get());
        assertEquals("Joe Shmo",csData.getOfficeHours().get("3_2").get());
        assertEquals("Jane Doe",csData.getOfficeHours().get("4_5").get());
        assertEquals("Jane Doe",csData.getOfficeHours().get("4_6").get());
        assertEquals("Jane Doe",csData.getOfficeHours().get("4_7").get());
        assertEquals("Joe Shmo",csData.getOfficeHours().get("5_1").get());
        assertEquals("Joe Shmo",csData.getOfficeHours().get("5_2").get());
        assertEquals("Jane Doe",csData.getOfficeHours().get("6_7").get());
        assertEquals("Jane Doe",csData.getOfficeHours().get("6_8").get());
    }
    @Test
    public void testRecitations() {
        ObservableList<Recitation> recitations = csData.getRecitations();
        assertEquals("R02",csData.getRecitations().get(0).getSection());
        assertEquals("Mckenna",csData.getRecitations().get(0).getInstructor());
        assertEquals("Wed 3:30pm-4:23pm",csData.getRecitations().get(0).getDayTime());
        assertEquals("Old CS 2114",csData.getRecitations().get(0).getLocation());
        assertEquals("Jane Doe",csData.getRecitations().get(0).getTA1());
        assertEquals("Joe Shmo",csData.getRecitations().get(0).getTA2());
        
        assertEquals("R05",csData.getRecitations().get(1).getSection());
        assertEquals("Banerjee",csData.getRecitations().get(1).getInstructor());
        assertEquals("Tues 5:30pm-6:23pm",csData.getRecitations().get(1).getDayTime());
        assertEquals("Old CS 2114",csData.getRecitations().get(1).getLocation());
        assertEquals("",csData.getRecitations().get(1).getTA1());
        assertEquals("",csData.getRecitations().get(1).getTA2());
    }
    @Test
    public void testStartingManday() {
        assertEquals("2017-01-23",csData.getStartingMonday());        
    }
    @Test
    public void testEndingFriday() {
        assertEquals("2017-05-19",csData.getEndingFriday());
    }
    @Test
    public void testScheduleItems() {
        assertEquals("Holiday",csData.getScheduleItems().get(0).getType());
        assertEquals("2/9/2017",csData.getScheduleItems().get(0).getDate());
        assertEquals("SNOW DAY",csData.getScheduleItems().get(0).getTitle());
        assertEquals("",csData.getScheduleItems().get(0).getTopic());
        assertEquals("http://funnybizblog.com",csData.getScheduleItems().get(0).getLink());
        assertEquals("",csData.getScheduleItems().get(0).getTime());
        assertEquals("",csData.getScheduleItems().get(0).getCriteria());
        
        assertEquals("Lecture",csData.getScheduleItems().get(1).getType());
        assertEquals("2/14/2017",csData.getScheduleItems().get(1).getDate());
        assertEquals("Lecture 3",csData.getScheduleItems().get(1).getTitle());
        assertEquals("Event Programming",csData.getScheduleItems().get(1).getTopic());
        assertEquals("",csData.getScheduleItems().get(1).getLink());
        assertEquals("",csData.getScheduleItems().get(1).getTime());
        assertEquals("",csData.getScheduleItems().get(1).getCriteria());
        
        assertEquals("Holiday",csData.getScheduleItems().get(2).getType());
        assertEquals("3/13/2017",csData.getScheduleItems().get(2).getDate());
        assertEquals("Spring Break",csData.getScheduleItems().get(2).getTitle());
        assertEquals("",csData.getScheduleItems().get(2).getTopic());
        assertEquals("",csData.getScheduleItems().get(2).getLink());
        assertEquals("",csData.getScheduleItems().get(2).getTime());
        assertEquals("",csData.getScheduleItems().get(2).getCriteria());
        
        assertEquals("HW",csData.getScheduleItems().get(3).getType());
        assertEquals("3/27/2017",csData.getScheduleItems().get(3).getDate());
        assertEquals("HW3",csData.getScheduleItems().get(3).getTitle());
        assertEquals("UML",csData.getScheduleItems().get(3).getTopic());
        assertEquals("",csData.getScheduleItems().get(3).getLink());
        assertEquals("11:59pm",csData.getScheduleItems().get(3).getTime());
        assertEquals("",csData.getScheduleItems().get(3).getCriteria());
    }
    @Test
    public void testTeams() {
        assertEquals("Atomic Comics",csData.getTeams().get(0).getName());
        assertEquals("#552211ff",csData.getTeams().get(0).getColor());
        assertEquals("#ffffffff",csData.getTeams().get(0).getTextColor());
        assertEquals("http://atomicomic.com",csData.getTeams().get(0).getLink());
        
        assertEquals("C4 Comics",csData.getTeams().get(1).getName());
        assertEquals("#235399ff",csData.getTeams().get(1).getColor());
        assertEquals("#ffffffff",csData.getTeams().get(1).getTextColor());
        assertEquals("http://c4-comics.appspot.com",csData.getTeams().get(1).getLink());
    }
    @Test
    public void testStudents() {
        assertEquals("Beau",csData.getStudents().get(0).getFirstName());
        assertEquals("Brummell",csData.getStudents().get(0).getLastName());
        assertEquals("Atomic Comics",csData.getStudents().get(0).getTeam());
        assertEquals("Lead Designer",csData.getStudents().get(0).getRole());
        
        assertEquals("Jane",csData.getStudents().get(1).getFirstName());
        assertEquals("Doe",csData.getStudents().get(1).getLastName());
        assertEquals("C4 Comics",csData.getStudents().get(1).getTeam());
        assertEquals("Lead Programmer",csData.getStudents().get(1).getRole());
        
        assertEquals("Noonian",csData.getStudents().get(2).getFirstName());
        assertEquals("Soong",csData.getStudents().get(2).getLastName());
        assertEquals("Atomic Comics",csData.getStudents().get(2).getTeam());
        assertEquals("Data Designer",csData.getStudents().get(2).getRole());
    }
}
