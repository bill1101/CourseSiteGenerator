/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

/**
 *
 * @author tyx
 */
public enum CSGeneratorProp {
    // FOR SIMPLE OK/CANCEL DIALOG BOXES
    OK_PROMPT,
    CANCEL_PROMPT,
    
    // THESE ARE FOR TEXT PARTICULAR TO THE APP'S WORKSPACE CONTROLS
    TAB_TITLE_COURSE_DETAILS,
    TAB_TITLE_TA_DATA,
    TAB_TITLE_RECITATION_DATA,
    TAB_TITLE_SCHEDULE_DATA,
    TAB_TITLE_PROJECT_DATA,
    
    COURSE_INFO_HEADER_TEXT,
    SUBJECT_TEXT,
    NUMBER_TEXT,
    SEMESTER_TEXT,
    YEAR_TEXT,
    TITLE_TEXT,
    INSTRUCTOR_NAME_TEXT,
    INSTRUCTOR_HOME_TEXT,
    EXPORT_DIR_TEXT,
    CHANGE_BUTTON_TEXT,
    SITE_TEMPLATE_HEADER_TEXT,
    SELECT_TEMPLATE_TEXT,
    SELECT_TEMPLATE_BUTTON_TEXT,
    SITE_PAGES_HEADER_TEXT,
    USE_COLUMN_TEXT,
    NAVBAR_TITLE_COLUMN_TEXT,
    FILE_NAME_COLUMN_TEXT,
    SCRIPT_COLUMN_TEXT,
    PAGE_STYLE_HEADER_TEXT,
    BANNER_SCHOOL_IMAGE_TEXT,
    BANNER_SCHOOL_IMAGE_BUTTON_TEXT,
    LEFT_FOOTER_IMAGE_TEXT,
    LEFT_FOOTER_IMAGE_BUTTON_TEXT,
    RIGHT_FOOTER_IMAGE_TEXT,
    RIGHE_FOOTER_IMAGE_BUTTON_TEXT,
    STYLESHEET_TEXT,
    PAGE_STYLE_NOTE_TEXT,
    
    RECITATION_HEADER_TEXT,
    RECITATION_DELETE_BUTTON_TEXT,
    SECTION_COLUMN_TEXT,
    INSTRUCTOR_COLUMN_TEXT,
    DAY_TIME_COLUMN_TEXT,
    LOCATION_COLUMN_TEXT,
    TA1_COLUMN_TEXT,
    TA2_COLUMN_TEXT,
    RECITATION_ADD_EDIT_HEADER_TEXT,
    SECTION_TEXT,
    INSTRUCTOR_TEXT,
    DAY_TIME_TEXT,
    LOCATION_TEXT,
    TA1_TEXT,
    TA2_TEXT,
    RECITATION_ADD_EDIT_BUTTON_TEXT,
    RECITATION_CLEAR_BUTTON_TEXT,
       
    
    TAS_HEADER_TEXT,
    REMOVE_TA_TEXT,
    UNDERGRAD_COLUMN_TEXT,
    NAME_COLUMN_TEXT,
    EMAIL_COLUMN_TEXT,
    NAME_PROMPT_TEXT,
    EMAIL_PROMPT_TEXT,
    ADD_BUTTON_TEXT,
    CLEAR_BUTTON_TEXT,
    UPDATE_BUTTON_TEXT,
    OFFICE_HOURS_SUBHEADER,
    OFFICE_HOURS_TABLE_HEADERS,
    DAYS_OF_WEEK,
    
    SCHEDULE_HEADER_TEXT,
    CALENDAR_BOUNDARIES_HEADER,
    STARTING_MONDAY_LABEL,
    ENDING_FRIDAY_LABEL,
    SCHEDULE_ITEMS_HEADER,
    REMOVE_SCHEDULE_BUTTON_TEXT,
    TYPE_COLUMN_TEXT,
    DATE_COLUMN_TEXT,
    TITLE_COLUMN_TEXT,
    TOPIC_COLUMN_TEXT,
    SCHEDULE_ITEMS_ADD_EDIT_HEADER,
    TYPE_LABEL,
    DATE_LABEL,
    TIME_TEXT,
    SCHEDULE_TITLE_TEXT,
    TOPIC_TEXT,
    LINK_TEXT,
    CRITERIA_TEXT,
    SCHEDULE_ITEMS_ADD_EDIT_BUTTON_TEXT,
    SCHEDULE_CLEAR_BUTTON_TEXT,
    
    PROJECTS_HEADER,
    TEAMS_HEADER_LABEL,
    REMOVE_TEAMS_BUTTON_TEXT,
    PROJECT_NAME_COLUMN_TEXT,
    COLOR_COLUMN_TEXT,
    TEXT_COLOR_COLUMN_TEXT,
    LINK_COLUMN_TEXT,
    TEAMS_ADD_EDIT_HEADER,
    NAME_TEXT,
    COLOR_TEXT,
    TEXT_COLOR_TEXT,
    TEAM_LINK_TEXT,
    ADD_TEAM_BUTTON_TEXT,
    TEAM_CLEAR_BUTTON_TEXT,
    STUDENTS_HEADER,
    REMOVE_STUDENTS_BUTTON_TEXT,
    FIRSTNAME_COLUMN_TEXT,
    LASTNAME_COLUMN_TEXT,
    TEAM_COLUMN_TEXT,
    ROLE_COLUMN_TEXT,
    STUDENT_ADD_EDIT_HEADER,
    FIRSTNAME_LABEL,
    LASTNAME_LABEL,
    TEAM_LABEL,
    ROLE_LABEL,
    STUDENT_ADD_BUTTON_TEXT,
    STUDENT_CLEAR_BUTTON_TEXT,
    
    
    // THESE ARE FOR ERROR MESSAGES PARTICULAR TO THE APP
    MISSING_TA_NAME_TITLE,
    MISSING_TA_NAME_MESSAGE,
    MISSING_TA_EMAIL_TITLE,
    MISSING_TA_EMAIL_MESSAGE,
    TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE,
    TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE,
    TA_EMAIL_INVALID_TITLE,
    TA_EMAIL_INVALID_MESSAGE,
    START_END_TIME_INVALID_TITLE,
    START_END_TIME_INVALID_MESSAGE,
    CHANGE_TIME_WARNING_TITLE,
    CHANGE_TIME_WARNING_MESSAGE,
}
