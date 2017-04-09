/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.CSGeneratorProp;
import csg.data.CSData;
import csg.data.SitePage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author tyx
 */
public class CourseDetailsWorkspace {
    CSGeneratorApp app;
    Tab courseDetailsTab;
    
    GridPane courseInfoPane;
    VBox courseInfoPositionPane;
    Label courseIndoHeader;
    Label subjectLabel;
    ComboBox subjectComboBox;
    Label blank;
    Label numberLabel;
    ComboBox numberComboBox;
    Label semesterLabel;
    ComboBox semesterComboBox;
    Label yearLabel;
    ComboBox yearComboBox;   
    Label titleLabel;
    TextField titleTextField;
    Label instructorNameLabel;
    TextField instructorNameTextField;
    Label instructorHome;
    TextField instructorHomeTextField;   
    Label exportDirLabel;
    Label exportDirContent;
    Button exportDirChangeButton;
    
    VBox siteTemplatePane;
    VBox siteTemplatePositionPane;
    Label siteTemplateHeader;
    Label selectTemplateLabel;
    Label selectTemplateContent;
    Button selectTemplateButton;
    Label sitePagesHeader;
    TableView<SitePage> sitePagesTable;
    TableColumn<SitePage, Boolean> useColumn;
    TableColumn<SitePage, String> navbarTitleColumn;
    TableColumn<SitePage, String> fileNameColumn;
    TableColumn<SitePage, String> scriptColumn;
    
    GridPane pageStylePane;
    VBox pageStylePositionPane;
    Label pageStyleHeader;
    Label bannerSchoolImageLabel;
    ImageView bannerSchoolImage;
    Button changeBannerSchoolImageButton;
    Label leftFooterImageLabel;
    ImageView leftFooterImage;
    Button changeLeftFooterImageButton;
    Label rightFooterImageLabel;
    ImageView rightFooterImage;
    Button changeRighrFooterImageButton;
    Label stylesheetLabel;
    ComboBox stylesheetComboBox;
    Label pageStyleNoteLabel;
    
    VBox courseSiteContent;
    
    public CourseDetailsWorkspace(CSGeneratorApp initApp){
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        courseDetailsTab = new Tab();
        courseDetailsTab.setText(props.getProperty(CSGeneratorProp.TAB_TITLE_COURSE_DETAILS.toString()));
        courseDetailsTab.setClosable(false);
        
        courseInfoPane = new GridPane();
        courseInfoPane.setVgap(10);
        courseInfoPane.setHgap(20);
        courseIndoHeader = new Label(props.getProperty(CSGeneratorProp.COURSE_INFO_HEADER_TEXT.toString()));
        courseInfoPane.add(courseIndoHeader, 0, 0);
        subjectLabel = new Label(props.getProperty(CSGeneratorProp.SUBJECT_TEXT.toString()));
        courseInfoPane.add(subjectLabel, 0, 1);
        subjectComboBox = new ComboBox();
        courseInfoPane.add(subjectComboBox, 1, 1);
        blank = new Label("        ");
        courseInfoPane.add(blank, 2, 1);
        numberLabel = new Label(props.getProperty(CSGeneratorProp.NUMBER_TEXT.toString()));
        courseInfoPane.add(numberLabel, 3, 1);
        numberComboBox = new ComboBox();
        courseInfoPane.add(numberComboBox, 4, 1);
        
        semesterLabel = new Label(props.getProperty(CSGeneratorProp.SEMESTER_TEXT.toString()));
        courseInfoPane.add(semesterLabel, 0, 2);
        semesterComboBox = new ComboBox();
        courseInfoPane.add(semesterComboBox, 1, 2);
        yearLabel = new Label(props.getProperty(CSGeneratorProp.YEAR_TEXT.toString()));
        courseInfoPane.add(yearLabel, 3, 2);
        yearComboBox = new ComboBox();
        courseInfoPane.add(yearComboBox, 4, 2);
        
        titleLabel = new Label(props.getProperty(CSGeneratorProp.TITLE_TEXT.toString()));
        courseInfoPane.add(titleLabel, 0, 3);
        titleTextField = new TextField();
        courseInfoPane.add(titleTextField, 1, 3, 4, 1);
        
        instructorNameLabel = new Label(props.getProperty(CSGeneratorProp.INSTRUCTOR_NAME_TEXT.toString()));
        courseInfoPane.add(instructorNameLabel, 0, 4);
        instructorNameTextField = new TextField();
        courseInfoPane.add(instructorNameTextField, 1, 4, 4, 1);
        instructorHome = new Label(props.getProperty(CSGeneratorProp.INSTRUCTOR_HOME_TEXT.toString()));
        courseInfoPane.add(instructorHome, 0, 5);
        instructorHomeTextField = new TextField();
        courseInfoPane.add(instructorHomeTextField, 1, 5, 4, 1);
        
        exportDirLabel = new Label(props.getProperty(CSGeneratorProp.EXPORT_DIR_TEXT));
        courseInfoPane.add(exportDirLabel, 0, 6);
        exportDirContent = new Label();
        courseInfoPane.add(exportDirContent, 1, 6, 3, 1);
        exportDirChangeButton = new Button(props.getProperty(CSGeneratorProp.CHANGE_BUTTON_TEXT.toString()));
        courseInfoPane.add(exportDirChangeButton, 4, 6);
        
        courseInfoPositionPane = new VBox();
        courseInfoPositionPane.getChildren().add(courseInfoPane);
        
        siteTemplatePane = new VBox();
        siteTemplatePositionPane = new VBox();
        siteTemplateHeader = new Label(props.getProperty(CSGeneratorProp.SITE_TEMPLATE_HEADER_TEXT.toString()));
        selectTemplateLabel = new Label(props.getProperty(CSGeneratorProp.SELECT_TEMPLATE_TEXT.toString()));
        selectTemplateContent = new Label();
        selectTemplateButton = new Button(props.getProperty(CSGeneratorProp.SELECT_TEMPLATE_BUTTON_TEXT.toString()));
        sitePagesHeader = new Label(props.getProperty(CSGeneratorProp.SITE_PAGES_HEADER_TEXT));
        
        sitePagesTable = new TableView();
        sitePagesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSData data = (CSData) app.getDataComponent();
        //ObservableList<SitePage> tableData = FXCollections.observableArrayList();
        ObservableList<SitePage> tableData = data.getSitePages();
        
        
        
        sitePagesTable.setItems(tableData);
        sitePagesTable.getColumns();
        useColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.USE_COLUMN_TEXT.toString()));
        //useColumn.setCellValueFactory(new PropertyValueFactory<SitePage, Boolean>("use"));
        useColumn.setCellFactory(column -> new CheckBoxTableCell());    
        navbarTitleColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.NAVBAR_TITLE_COLUMN_TEXT.toString()));
        navbarTitleColumn.setCellValueFactory(new PropertyValueFactory<SitePage, String>("navBarTitle"));
        fileNameColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.FILE_NAME_COLUMN_TEXT.toString()));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<SitePage, String>("fileName"));
        scriptColumn = new TableColumn<>(props.getProperty(CSGeneratorProp.SCRIPT_COLUMN_TEXT.toString()));
        scriptColumn.setCellValueFactory(new PropertyValueFactory<SitePage, String>("script"));
        sitePagesTable.getColumns().add(useColumn);
        sitePagesTable.getColumns().add(navbarTitleColumn);
        sitePagesTable.getColumns().add(fileNameColumn);
        sitePagesTable.getColumns().add(scriptColumn);
        HBox sitePagesContainer = new HBox();
        sitePagesContainer.getChildren().add(sitePagesTable);
        siteTemplatePane.getChildren().addAll(siteTemplateHeader,selectTemplateLabel,
                selectTemplateContent,selectTemplateButton,sitePagesHeader,sitePagesContainer);
        siteTemplatePositionPane.getChildren().add(siteTemplatePane);
        
        pageStylePane = new GridPane();
        pageStylePositionPane = new VBox();
        pageStylePane.setVgap(10);
        pageStylePane.setHgap(20);
        pageStyleHeader = new Label(props.getProperty(CSGeneratorProp.PAGE_STYLE_HEADER_TEXT.toString()));
        bannerSchoolImageLabel = new Label(props.getProperty(CSGeneratorProp.BANNER_SCHOOL_IMAGE_TEXT.toString()));
        bannerSchoolImage = new ImageView();
        changeBannerSchoolImageButton = new Button(props.getProperty(CSGeneratorProp.BANNER_SCHOOL_IMAGE_BUTTON_TEXT.toString()));
        leftFooterImageLabel = new Label(props.getProperty(CSGeneratorProp.LEFT_FOOTER_IMAGE_TEXT.toString()));
        leftFooterImage = new ImageView();
        changeLeftFooterImageButton = new Button(props.getProperty(CSGeneratorProp.LEFT_FOOTER_IMAGE_BUTTON_TEXT.toString()));
        rightFooterImageLabel = new Label(props.getProperty(CSGeneratorProp.RIGHT_FOOTER_IMAGE_TEXT.toString()));
        rightFooterImage = new ImageView();
        changeRighrFooterImageButton = new Button(props.getProperty(CSGeneratorProp.RIGHE_FOOTER_IMAGE_BUTTON_TEXT.toString()));
        stylesheetLabel = new Label(props.getProperty(CSGeneratorProp.STYLESHEET_TEXT.toString()));
        stylesheetComboBox = new ComboBox();
        pageStyleNoteLabel = new Label(props.getProperty(CSGeneratorProp.PAGE_STYLE_NOTE_TEXT.toString()));
        pageStylePositionPane.getChildren().add(pageStylePane);
        
        pageStylePane.add(pageStyleHeader, 0, 0);
        pageStylePane.add(bannerSchoolImageLabel, 0, 1);
        pageStylePane.add(bannerSchoolImage, 1, 1);
        pageStylePane.add(changeBannerSchoolImageButton, 2, 1);
        pageStylePane.add(leftFooterImageLabel, 0, 2);
        pageStylePane.add(leftFooterImage, 1, 2);
        pageStylePane.add(changeLeftFooterImageButton, 2, 2);
        pageStylePane.add(rightFooterImageLabel, 0, 3);
        pageStylePane.add(rightFooterImage, 1, 3);
        pageStylePane.add(changeRighrFooterImageButton, 2, 3);
        pageStylePane.add(stylesheetLabel, 0, 4);
        pageStylePane.add(stylesheetComboBox, 1, 4);
        pageStylePane.add(pageStyleNoteLabel, 0, 5, 3, 1);
        courseSiteContent = new VBox(10);
        courseSiteContent.setStyle("-fx-padding: 20;");
        courseInfoPositionPane.setStyle("-fx-background-color: Yellow;");
        siteTemplatePositionPane.setStyle("-fx-background-color: Yellow;");
        pageStylePositionPane.setStyle("-fx-background-color: Yellow;");
        courseInfoPane.setStyle("-fx-padding: 10;");
        
        courseSiteContent.getChildren().addAll(courseInfoPositionPane,siteTemplatePositionPane,pageStylePositionPane);
        courseDetailsTab.setContent(courseSiteContent);
    }

    public Tab getCourseDetailsTab() {
        return courseDetailsTab;
    }
}
