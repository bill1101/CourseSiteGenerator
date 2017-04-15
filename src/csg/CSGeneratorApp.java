package csg;

import csg.data.CSData;
import csg.data.Recitation;
import csg.data.ScheduleItem;
import csg.data.SitePage;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.file.CSFiles;
import csg.style.CSStyle;
import csg.workspace.CSWorkspace;
import djf.AppTemplate;
import djf.components.AppDataComponent;
import java.util.Locale;
import static javafx.application.Application.launch;

/**
 *
 * @author tyx
 */
public class CSGeneratorApp extends AppTemplate{

    @Override
    public void buildAppComponentsHook() {
        dataComponent = new CSData(this);
        workspaceComponent = new CSWorkspace(this);       
        fileComponent = new CSFiles(this);
        styleComponent = new CSStyle(this);
        
        hardCodeDate(dataComponent);
    }
    
    public void hardCodeDate(AppDataComponent appData){
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
        data.getSitePages().add(new SitePage(false,"Projects","projects.html","ProjectsBuilder.js"));
        data.setBannerSchoolImage("banner.png");
        data.setLeftFooterImage("footer.png");
        data.setRightFooterImage("right.png");
        data.setStyleSheet("sea_wolf.css");
        
        data.getTeachingAssistants().add(new TeachingAssistant("Jane Doe","jane.doe@yahoo.com",true));
        data.getTeachingAssistants().add(new TeachingAssistant("Joe Shmo","joe.shmo@yale.edu",false));
        
        
        //data.toggleTAOfficeHours("2_1", "Joe Shmo");
        
        data.getRecitations().add(new Recitation("R02","Mckenna","Wed 3:30pm-4:23pm","Old CS 2114","Jane Doe","Joe Shmo"));
        data.getRecitations().add(new Recitation("R05","Banerjee","Tues 5:30pm-6:23pm","Old CS 2114","",""));
        
        data.setStartingMonday("2017-04-05");
        data.setEndingFriday("2017-04-15");
        data.getScheduleItems().add(new ScheduleItem("Holiday","2/9/2017","SNOW DAY",""));
        data.getScheduleItems().add(new ScheduleItem("Lecture","2/14/2017","Lecture 3","Event Programming"));
        data.getScheduleItems().add(new ScheduleItem("Holiday","3/13/2017","Spring Break",""));
        data.getScheduleItems().add(new ScheduleItem("HW","3/27/2017","HW3","UML"));
        
        data.getTeams().add(new Team("Atomio Comics","552211","ffffff","http://atomicomic.com"));
        data.getTeams().add(new Team("C4 Comics","235399","ffffff","http://c4-comics.appspot.com"));
        
        data.getStudents().add(new Student("Beau","Brummell","Atomic Comics","Lead Designer"));
        data.getStudents().add(new Student("Jane","Doe","C4 Comics","Lead Programmer"));
        data.getStudents().add(new Student("Noonian","Soong","Atomic Comics","Data Designer"));
    }
    
    public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	launch(args);
    }
}
