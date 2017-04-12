/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.data.TeachingAssistant;
import csg.workspace.CSWorkspace;
import csg.workspace.CourseDetailsWorkspace;
import csg.workspace.ProjectsWorkspace;
import csg.workspace.RecitationWorkspace;
import csg.workspace.ScheduleWorkspace;
import csg.workspace.TAWorkspace;
import djf.AppTemplate;
import djf.components.AppStyleComponent;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author tyx
 */
public class CSStyle extends AppStyleComponent {
    // FIRST WE SHOULD DECLARE ALL OF THE STYLE TYPES WE PLAN TO USE
    
    // WE'LL USE THIS FOR ORGANIZING LEFT AND RIGHT CONTROLS
    public static String CLASS_PLAIN_PANE = "plain_pane";
    
    // THESE ARE THE HEADERS FOR EACH SIDE
    public static String CLASS_HEADER_PANE = "header_pane";
    public static String CLASS_HEADER_LABEL = "header_label";

    // ON THE LEFT WE HAVE THE TA ENTRY
    public static String CLASS_TA_TABLE = "ta_table";
    public static String CLASS_TA_TABLE_COLUMN_HEADER = "ta_table_column_header";
    public static String CLASS_ADD_TA_PANE = "add_ta_pane";
    public static String CLASS_ADD_TA_TEXT_FIELD = "add_ta_text_field";
    public static String CLASS_ADD_TA_BUTTON = "add_ta_button";

    // ON THE RIGHT WE HAVE THE OFFICE HOURS GRID
    public static String CLASS_OFFICE_HOURS_GRID = "office_hours_grid";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE = "office_hours_grid_time_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL = "office_hours_grid_time_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE = "office_hours_grid_day_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL = "office_hours_grid_day_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE = "office_hours_grid_time_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL = "office_hours_grid_time_cell_label";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE = "office_hours_grid_ta_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL = "office_hours_grid_ta_cell_label";

    // FOR HIGHLIGHTING CELLS, COLUMNS, AND ROWS
    public static String CLASS_HIGHLIGHTED_GRID_CELL = "highlighted_grid_cell";
    public static String CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN = "highlighted_grid_row_or_column";
    
    public static String CLASS_WORKSPACE_BACKGROUND = "workspace_background";
    public static String CLASS_TAB_PANE_BACKGROUND = ".tab-pane > .tab-header-area > .tab-header-background";
    public static String CLASS_TAB_BACKGROUND = ".tab-pane > .tab-header-area > .headers-region > .tab";
    public static String CLASS_TAB_SELECTED_BACKGROUND = ".tab-pane > .tab-header-area > .headers-region > .tab:selected";
    public static String CLASS_CONTENT_PANE = "content_pane";
    public static String CLASS_SINGLE_PANE = "single_pane";
    public static String CLASS_FORM_PANE = "form_pane";
    public static String CLASS_TABLE_BACKGROUND = "table_backgroud";
    public static String CLASS_TABLE_CONTAINER = ".split-pane > .split-pane-divider";
    public static String CLASS_FORM_HEADER = "form_header";
    public static String CLASS_FORM_SUBHEADER = "form_subheader";
    public static String CLASS_FORM_LABEL = "form_label";
    public static String CLASS_BOLD = "bold";
    public static String CLASS_REMOVE_BUTTON = "remove_button";
    public static String CLASS_GENERAL_BUTTON = "general_button";
    public static String CLASS_MIDIAN_INPUT = "median_input";
    public static String CLASS_LONG_INPUT = "long_input";
    public static String CLASS_TA_BACKGROUND = "ta_header_background";
    public static String CLASS_OFFICE_HOUR_BACKGROUND = "office_hour_background";
    // THIS PROVIDES ACCESS TO OTHER COMPONENTS
    private AppTemplate app;
    
    /**
     * This constructor initializes all style for the application.
     * 
     * @param initApp The application to be stylized.
     */
    public CSStyle(AppTemplate initApp) {
        // KEEP THIS FOR LATER
        app = initApp;

        // LET'S USE THE DEFAULT STYLESHEET SETUP
        super.initStylesheet(app);
        
        initWorkspace();
        
        // INIT THE STYLE FOR THE FILE TOOLBAR
        app.getGUI().initFileToolbarStyle();
        
        initCourseDetailsWorkspaceStyle();
        // AND NOW OUR WORKSPACE STYLE
        initTAWorkspaceStyle();
        //initOfficeHoursGridStyle();
        
        initRecitationWorkspaceStyle();
        
        initScheduleWorkspaceStyle();
        
        initProjectWorkspaceStyle();
    }
    
    
    private void initWorkspace() {
        CSWorkspace workspaceComponent = ((CSWorkspace)app.getWorkspaceComponent());
        workspaceComponent.getWorkspace().getStyleClass().add(CLASS_WORKSPACE_BACKGROUND);
        workspaceComponent.getTabPane().getStyleClass().add(CLASS_TAB_PANE_BACKGROUND);
        workspaceComponent.getTabPane().getStyleClass().add(CLASS_TAB_BACKGROUND);
        workspaceComponent.getTabPane().getStyleClass().add(CLASS_TAB_SELECTED_BACKGROUND);
    }
    
    public void initCourseDetailsWorkspaceStyle() {
        
        CourseDetailsWorkspace courseDetailsWorkspaceComponent = ((CSWorkspace)app.getWorkspaceComponent()).getCourseDetailsWorkspaceComponent();
        
        courseDetailsWorkspaceComponent.getCourseSiteContent().getStyleClass().add(CLASS_CONTENT_PANE);
        courseDetailsWorkspaceComponent.getCourseInfoPane().getStyleClass().add(CLASS_SINGLE_PANE);
        courseDetailsWorkspaceComponent.getCourseIndoHeader().getStyleClass().add(CLASS_FORM_HEADER);
        
        courseDetailsWorkspaceComponent.getSiteTemplatePane().getStyleClass().add(CLASS_SINGLE_PANE);
        courseDetailsWorkspaceComponent.getSiteTemplateHeader().getStyleClass().add(CLASS_FORM_HEADER);
        courseDetailsWorkspaceComponent.getSitePagesContainer().getStyleClass().add(CLASS_TABLE_BACKGROUND);
        courseDetailsWorkspaceComponent.getSitePagesContainer().getStyleClass().add(CLASS_TABLE_CONTAINER);
        courseDetailsWorkspaceComponent.getExportDirContent().getStyleClass().add(CLASS_BOLD);
        courseDetailsWorkspaceComponent.getSelectTemplateContent().getStyleClass().add(CLASS_BOLD);
        courseDetailsWorkspaceComponent.getSitePagesHeader().getStyleClass().add(CLASS_BOLD);
                
        courseDetailsWorkspaceComponent.getPageStylePane().getStyleClass().add(CLASS_SINGLE_PANE);
        courseDetailsWorkspaceComponent.getPageStyleHeader().getStyleClass().add(CLASS_FORM_HEADER);
        courseDetailsWorkspaceComponent.getChangeBannerSchoolImageButton().getStyleClass().add(CLASS_GENERAL_BUTTON);
        courseDetailsWorkspaceComponent.getChangeLeftFooterImageButton().getStyleClass().add(CLASS_GENERAL_BUTTON);
        courseDetailsWorkspaceComponent.getChangeRighrFooterImageButton().getStyleClass().add(CLASS_GENERAL_BUTTON);
        courseDetailsWorkspaceComponent.getStylesheetComboBox().getStyleClass().add(CLASS_MIDIAN_INPUT);
    }
    
    private void initRecitationWorkspaceStyle() {
        RecitationWorkspace recitationWorkspaceComponent = ((CSWorkspace)app.getWorkspaceComponent()).getRecitationWorkspaceComponent();
        
        recitationWorkspaceComponent.getRecitationContent().getStyleClass().add(CLASS_CONTENT_PANE);
        recitationWorkspaceComponent.getRecitationHeader().getStyleClass().add(CLASS_FORM_HEADER);
        recitationWorkspaceComponent.getRecitationDeleteButton().getStyleClass().add(CLASS_REMOVE_BUTTON);
        recitationWorkspaceComponent.getRecitationAddEditPane().getStyleClass().add(CLASS_SINGLE_PANE);
        recitationWorkspaceComponent.getRecitationAddEditHeader().getStyleClass().add(CLASS_FORM_SUBHEADER);
        recitationWorkspaceComponent.getSectionTextField().getStyleClass().add(CLASS_MIDIAN_INPUT);
        recitationWorkspaceComponent.getInstructorTextField().getStyleClass().add(CLASS_MIDIAN_INPUT);
        recitationWorkspaceComponent.getDayTimeTextField().getStyleClass().add(CLASS_MIDIAN_INPUT);
        recitationWorkspaceComponent.getLocationTextField().getStyleClass().add(CLASS_MIDIAN_INPUT);
        recitationWorkspaceComponent.getTA1ComboBox().getStyleClass().add(CLASS_MIDIAN_INPUT);
        recitationWorkspaceComponent.getTA2ComboBox().getStyleClass().add(CLASS_MIDIAN_INPUT);
        recitationWorkspaceComponent.getRecitationAddEditButton().getStyleClass().add(CLASS_GENERAL_BUTTON);
        recitationWorkspaceComponent.getRecitationClear().getStyleClass().add(CLASS_GENERAL_BUTTON);
    }
    
    private void initScheduleWorkspaceStyle() {
        ScheduleWorkspace scheduleWorkspaceComponent = ((CSWorkspace)app.getWorkspaceComponent()).getScheduleWorkspaceComponent();
        
        scheduleWorkspaceComponent.getScheduleContent().getStyleClass().add(CLASS_CONTENT_PANE);
        scheduleWorkspaceComponent.getScheduleHeaderLabel().getStyleClass().add(CLASS_FORM_HEADER);
        scheduleWorkspaceComponent.getCalendarBoundariesPane().getStyleClass().add(CLASS_SINGLE_PANE);
        scheduleWorkspaceComponent.getCalendarBoundariesHeader().getStyleClass().add(CLASS_FORM_SUBHEADER);
        scheduleWorkspaceComponent.getScheduleItemsPane().getStyleClass().add(CLASS_SINGLE_PANE);
        scheduleWorkspaceComponent.getScheduleItemsHeader().getStyleClass().add(CLASS_FORM_SUBHEADER);
        scheduleWorkspaceComponent.getRemoveSchedule().getStyleClass().add(CLASS_REMOVE_BUTTON);
        scheduleWorkspaceComponent.getScheduleItemsAddEditHeader().getStyleClass().add(CLASS_FORM_SUBHEADER);
        scheduleWorkspaceComponent.getScheduleItemPane().getStyleClass().add(CLASS_FORM_PANE);
        scheduleWorkspaceComponent.getTypeComboBox().getStyleClass().add(CLASS_MIDIAN_INPUT);
        scheduleWorkspaceComponent.getTimeTextField().getStyleClass().add(CLASS_MIDIAN_INPUT);
        scheduleWorkspaceComponent.getTitleTextField().getStyleClass().add(CLASS_LONG_INPUT);
        scheduleWorkspaceComponent.getScheduleAddEditButton().getStyleClass().add(CLASS_GENERAL_BUTTON);
        scheduleWorkspaceComponent.getScheduleClear().getStyleClass().add(CLASS_GENERAL_BUTTON);
    }
    
    private void initProjectWorkspaceStyle() {
        ProjectsWorkspace projectWorkspaceComponent = ((CSWorkspace)app.getWorkspaceComponent()).getProjectWorkspaceComponent();
        
        projectWorkspaceComponent.getProjectsContent().getStyleClass().add(CLASS_CONTENT_PANE);
        projectWorkspaceComponent.getProjectsHeader().getStyleClass().add(CLASS_FORM_HEADER);
        
        projectWorkspaceComponent.getTeamsPane().getStyleClass().add(CLASS_SINGLE_PANE);
        projectWorkspaceComponent.getTeamsHeaderLabel().getStyleClass().add(CLASS_FORM_SUBHEADER);
        projectWorkspaceComponent.getRemoveTeamsButton().getStyleClass().add(CLASS_REMOVE_BUTTON);
        projectWorkspaceComponent.getTeamsAddEditPane().getStyleClass().add(CLASS_FORM_PANE);
        projectWorkspaceComponent.getTeamsAddEditHeader().getStyleClass().add(CLASS_FORM_SUBHEADER);
        projectWorkspaceComponent.getTeamsAddEditButton().getStyleClass().add(CLASS_GENERAL_BUTTON);
        projectWorkspaceComponent.getTeamsClearButton().getStyleClass().add(CLASS_GENERAL_BUTTON);
        
        projectWorkspaceComponent.getStudentsPane().getStyleClass().add(CLASS_SINGLE_PANE);
        projectWorkspaceComponent.getStudentsAddEditPane().getStyleClass().add(CLASS_FORM_PANE);
        projectWorkspaceComponent.getStudentsAddEditHeader().getStyleClass().add(CLASS_FORM_SUBHEADER);
        projectWorkspaceComponent.getRemoveStudentsButton().getStyleClass().add(CLASS_REMOVE_BUTTON);
        projectWorkspaceComponent.getStudentsHeaderLabel().getStyleClass().add(CLASS_FORM_SUBHEADER);
        projectWorkspaceComponent.getFirstNameTextField().getStyleClass().add(CLASS_MIDIAN_INPUT);
        projectWorkspaceComponent.getLastNameTextField().getStyleClass().add(CLASS_MIDIAN_INPUT);
        projectWorkspaceComponent.getTeamComboBox().getStyleClass().add(CLASS_MIDIAN_INPUT);
        projectWorkspaceComponent.getRoleTextField().getStyleClass().add(CLASS_MIDIAN_INPUT);
        projectWorkspaceComponent.getStudentsAddEditButton().getStyleClass().add(CLASS_GENERAL_BUTTON);
        projectWorkspaceComponent.getStudentsClear().getStyleClass().add(CLASS_GENERAL_BUTTON);
    }
    /**
     * This function specifies all the style classes for
     * all user interface controls in the workspace.
     */
    private void initTAWorkspaceStyle() {
        // LEFT SIDE - THE HEADER
        TAWorkspace workspaceComponent = ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent();
        workspaceComponent.getsPane().getStyleClass().add(CLASS_CONTENT_PANE);
        //workspaceComponent.getTAsHeaderBox().getStyleClass().add(CLASS_TA_BACKGROUND);
        workspaceComponent.getTAsHeaderLabel().getStyleClass().add(CLASS_FORM_HEADER);
        workspaceComponent.getRemoveTAButton().getStyleClass().add(CLASS_REMOVE_BUTTON);

        // LEFT SIDE - THE TABLE
        //TableView<TeachingAssistant> taTable = workspaceComponent.getTATable();
        //taTable.getStyleClass().add(CLASS_TA_TABLE);
        //for (TableColumn tableColumn : taTable.getColumns()) {
        //    tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        //}

        // LEFT SIDE - THE TA DATA ENTRY
        //workspaceComponent.getAddBox().getStyleClass().add(CLASS_ADD_TA_PANE);
//        workspaceComponent.getNameTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
//        workspaceComponent.getEmailTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
//        workspaceComponent.getAddButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
//        workspaceComponent.getClearButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        // RIGHT SIDE - THE HEADER
        //workspaceComponent.getOfficeHoursSubheaderBox().getStyleClass().add(CLASS_TA_BACKGROUND);
        workspaceComponent.getOfficeHoursSubheaderLabel().getStyleClass().add(CLASS_FORM_HEADER);
    }
    
    /**
     * This method initializes the style for all UI components in
     * the office hours grid. Note that this should be called every
     * time a new TA Office Hours Grid is created or loaded.
     */
    public void initOfficeHoursGridStyle() {
        // RIGHT SIDE - THE OFFICE HOURS GRID TIME HEADERS
        TAWorkspace workspaceComponent = ((CSWorkspace)app.getWorkspaceComponent()).getTAworkspaceComponent();
        workspaceComponent.getOfficeHoursGridPane().getStyleClass().add(CLASS_OFFICE_HOURS_GRID);
        workspaceComponent.getRightPane().getStyleClass().add(CLASS_OFFICE_HOUR_BACKGROUND);
        workspaceComponent.getOfficeHoursGridPane().getStyleClass().add(CLASS_TABLE_BACKGROUND);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderPanes(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderLabels(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderPanes(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderLabels(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellPanes(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellLabels(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellPanes(), CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellLabels(), CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL);
    }
    
    /**
     * This helper method initializes the style of all the nodes in the nodes
     * map to a common style, styleClass.
     */
    private void setStyleClassOnAll(HashMap nodes, String styleClass) {
        for (Object nodeObject : nodes.values()) {
            Node n = (Node)nodeObject;
            n.getStyleClass().add(styleClass);
        }
    }

    

    

    

}