/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import csg.data.CSData;
import csg.data.SitePage;
import csg.file.CSFiles;
import djf.settings.AppStartupConstants;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
            csFiles.loadData(csData, "./work/hardcode1.json");
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
        assertEquals(false,sitePages.get(4).getUse());
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
}
