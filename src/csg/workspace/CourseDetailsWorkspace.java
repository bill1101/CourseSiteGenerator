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
import csg.style.CSStyle;
import static djf.settings.AppStartupConstants.FILE_PROTOCOL;
import static djf.settings.AppStartupConstants.PATH_IMAGES;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import properties_manager.PropertiesManager;

/**
 *
 * @author tyx
 */
public class CourseDetailsWorkspace {
    CSGeneratorApp app;
    Tab courseDetailsTab;
    
    GridPane courseInfoPane;
    //VBox courseInfoPositionPane;
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
    //VBox siteTemplatePositionPane;
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
    //VBox pageStylePositionPane;
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
    
    SplitPane sitePagesContainer;
    
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
        
        //courseInfoPositionPane = new VBox();
        //courseInfoPositionPane.getChildren().add(courseInfoPane);
        
        siteTemplatePane = new VBox(8);
        //siteTemplatePositionPane = new VBox();
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
//        useColumn.setCellValueFactory(param -> param.getValue().isUse());
//        useColumn.setCellFactory(CheckBoxTableCell.forTableColumn(useColumn));
        useColumn.setCellValueFactory(param -> param.getValue().isUse());
        useColumn.setCellFactory(i -> {
            CheckBoxTableCell<SitePage, Boolean> checkBoxCell = new CheckBoxTableCell<>();
            return checkBoxCell;
        });
        
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
        sitePagesTable.setEditable(true);
        sitePagesContainer = new SplitPane(sitePagesTable, new VBox());
        sitePagesContainer.setDividerPositions(0.8f);
        
        //sitePagesContainer.getChildren().add(sitePagesTable);
        
        siteTemplatePane.getChildren().addAll(siteTemplateHeader,selectTemplateLabel,
                selectTemplateContent,selectTemplateButton,sitePagesHeader,sitePagesContainer);
        //siteTemplatePositionPane.getChildren().add(siteTemplatePane);
        
        pageStylePane = new GridPane();
        //pageStylePositionPane = new VBox();
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
        //pageStylePositionPane.getChildren().add(pageStylePane);
        
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
        //courseSiteContent.setStyle("-fx-padding: 20;");
        //courseInfoPositionPane.setStyle("-fx-background-color: Yellow;");
        //siteTemplatePositionPane.setStyle("-fx-background-color: Yellow;");
        //pageStylePositionPane.setStyle("-fx-background-color: Yellow;");
        //courseInfoPane.setStyle("-fx-padding: 10;");
        
        courseSiteContent.getChildren().addAll(courseInfoPane,siteTemplatePane,pageStylePane);
        courseDetailsTab.setContent(courseSiteContent);
        //CSStyle csStyle = (CSStyle)app.getStyleComponent();
        //csStyle.initCourseDetailsWorkspaceStyle();
        
    }
    void reloadWorkspace(CSData data) {
        //System.out.println(data);
        subjectComboBox.setValue(data.getSubject());
        numberComboBox.setValue(data.getNumber());
        semesterComboBox.setValue(data.getSemester());
        yearComboBox.setValue(data.getYear());
        titleTextField.setText(data.getTitle());
        instructorNameTextField.setText(data.getInstructorName());
        instructorHomeTextField.setText(data.getInstructorHome());
        exportDirContent.setText(data.getExportDir());
        selectTemplateContent.setText(data.getTemplateDir());
        Image bannerImage = new Image(FILE_PROTOCOL + PATH_IMAGES + data.getBannerSchoolImage(),250,30,false,false);
        bannerSchoolImage.setImage(bannerImage);
        Image leftImage = new Image(FILE_PROTOCOL + PATH_IMAGES + data.getLeftFooterImage(),250,30,false,false);
        leftFooterImage.setImage(leftImage);
        Image rightImage = new Image(FILE_PROTOCOL + PATH_IMAGES + data.getRightFooterImage(),250,30,false,false);
        rightFooterImage.setImage(rightImage);
        stylesheetComboBox.setValue(data.getStyleSheet());
    }
    
    public SplitPane getSitePagesContainer() {
        return sitePagesContainer;
    }
       
    public Tab getCourseDetailsTab() {
        return courseDetailsTab;
    }

    public CSGeneratorApp getApp() {
        return app;
    }

    public void setApp(CSGeneratorApp app) {
        this.app = app;
    }

    public GridPane getCourseInfoPane() {
        return courseInfoPane;
    }

    public void setCourseInfoPane(GridPane courseInfoPane) {
        this.courseInfoPane = courseInfoPane;
    }

    public Label getCourseIndoHeader() {
        return courseIndoHeader;
    }

    public void setCourseIndoHeader(Label courseIndoHeader) {
        this.courseIndoHeader = courseIndoHeader;
    }

    public Label getSubjectLabel() {
        return subjectLabel;
    }

    public void setSubjectLabel(Label subjectLabel) {
        this.subjectLabel = subjectLabel;
    }

    public ComboBox getSubjectComboBox() {
        return subjectComboBox;
    }

    public void setSubjectComboBox(ComboBox subjectComboBox) {
        this.subjectComboBox = subjectComboBox;
    }

    public Label getBlank() {
        return blank;
    }

    public void setBlank(Label blank) {
        this.blank = blank;
    }

    public Label getNumberLabel() {
        return numberLabel;
    }

    public void setNumberLabel(Label numberLabel) {
        this.numberLabel = numberLabel;
    }

    public ComboBox getNumberComboBox() {
        return numberComboBox;
    }

    public void setNumberComboBox(ComboBox numberComboBox) {
        this.numberComboBox = numberComboBox;
    }

    public Label getSemesterLabel() {
        return semesterLabel;
    }

    public void setSemesterLabel(Label semesterLabel) {
        this.semesterLabel = semesterLabel;
    }

    public ComboBox getSemesterComboBox() {
        return semesterComboBox;
    }

    public void setSemesterComboBox(ComboBox semesterComboBox) {
        this.semesterComboBox = semesterComboBox;
    }

    public Label getYearLabel() {
        return yearLabel;
    }

    public void setYearLabel(Label yearLabel) {
        this.yearLabel = yearLabel;
    }

    public ComboBox getYearComboBox() {
        return yearComboBox;
    }

    public void setYearComboBox(ComboBox yearComboBox) {
        this.yearComboBox = yearComboBox;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    public TextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(TextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public Label getInstructorNameLabel() {
        return instructorNameLabel;
    }

    public void setInstructorNameLabel(Label instructorNameLabel) {
        this.instructorNameLabel = instructorNameLabel;
    }

    public TextField getInstructorNameTextField() {
        return instructorNameTextField;
    }

    public void setInstructorNameTextField(TextField instructorNameTextField) {
        this.instructorNameTextField = instructorNameTextField;
    }

    public Label getInstructorHome() {
        return instructorHome;
    }

    public void setInstructorHome(Label instructorHome) {
        this.instructorHome = instructorHome;
    }

    public TextField getInstructorHomeTextField() {
        return instructorHomeTextField;
    }

    public void setInstructorHomeTextField(TextField instructorHomeTextField) {
        this.instructorHomeTextField = instructorHomeTextField;
    }

    public Label getExportDirLabel() {
        return exportDirLabel;
    }

    public void setExportDirLabel(Label exportDirLabel) {
        this.exportDirLabel = exportDirLabel;
    }

    public Label getExportDirContent() {
        return exportDirContent;
    }

    public void setExportDirContent(Label exportDirContent) {
        this.exportDirContent = exportDirContent;
    }

    public Button getExportDirChangeButton() {
        return exportDirChangeButton;
    }

    public void setExportDirChangeButton(Button exportDirChangeButton) {
        this.exportDirChangeButton = exportDirChangeButton;
    }

    public VBox getSiteTemplatePane() {
        return siteTemplatePane;
    }

    public void setSiteTemplatePane(VBox siteTemplatePane) {
        this.siteTemplatePane = siteTemplatePane;
    }

    public Label getSiteTemplateHeader() {
        return siteTemplateHeader;
    }

    public void setSiteTemplateHeader(Label siteTemplateHeader) {
        this.siteTemplateHeader = siteTemplateHeader;
    }

    public Label getSelectTemplateLabel() {
        return selectTemplateLabel;
    }

    public void setSelectTemplateLabel(Label selectTemplateLabel) {
        this.selectTemplateLabel = selectTemplateLabel;
    }

    public Label getSelectTemplateContent() {
        return selectTemplateContent;
    }

    public void setSelectTemplateContent(Label selectTemplateContent) {
        this.selectTemplateContent = selectTemplateContent;
    }

    public Button getSelectTemplateButton() {
        return selectTemplateButton;
    }

    public void setSelectTemplateButton(Button selectTemplateButton) {
        this.selectTemplateButton = selectTemplateButton;
    }

    public Label getSitePagesHeader() {
        return sitePagesHeader;
    }

    public void setSitePagesHeader(Label sitePagesHeader) {
        this.sitePagesHeader = sitePagesHeader;
    }

    public TableView<SitePage> getSitePagesTable() {
        return sitePagesTable;
    }

    public void setSitePagesTable(TableView<SitePage> sitePagesTable) {
        this.sitePagesTable = sitePagesTable;
    }

    public TableColumn<SitePage, Boolean> getUseColumn() {
        return useColumn;
    }

    public void setUseColumn(TableColumn<SitePage, Boolean> useColumn) {
        this.useColumn = useColumn;
    }

    public TableColumn<SitePage, String> getNavbarTitleColumn() {
        return navbarTitleColumn;
    }

    public void setNavbarTitleColumn(TableColumn<SitePage, String> navbarTitleColumn) {
        this.navbarTitleColumn = navbarTitleColumn;
    }

    public TableColumn<SitePage, String> getFileNameColumn() {
        return fileNameColumn;
    }

    public void setFileNameColumn(TableColumn<SitePage, String> fileNameColumn) {
        this.fileNameColumn = fileNameColumn;
    }

    public TableColumn<SitePage, String> getScriptColumn() {
        return scriptColumn;
    }

    public void setScriptColumn(TableColumn<SitePage, String> scriptColumn) {
        this.scriptColumn = scriptColumn;
    }

    public GridPane getPageStylePane() {
        return pageStylePane;
    }

    public void setPageStylePane(GridPane pageStylePane) {
        this.pageStylePane = pageStylePane;
    }

    public Label getPageStyleHeader() {
        return pageStyleHeader;
    }

    public void setPageStyleHeader(Label pageStyleHeader) {
        this.pageStyleHeader = pageStyleHeader;
    }

    public Label getBannerSchoolImageLabel() {
        return bannerSchoolImageLabel;
    }

    public void setBannerSchoolImageLabel(Label bannerSchoolImageLabel) {
        this.bannerSchoolImageLabel = bannerSchoolImageLabel;
    }

    public ImageView getBannerSchoolImage() {
        return bannerSchoolImage;
    }

    public void setBannerSchoolImage(ImageView bannerSchoolImage) {
        this.bannerSchoolImage = bannerSchoolImage;
    }

    public Button getChangeBannerSchoolImageButton() {
        return changeBannerSchoolImageButton;
    }

    public void setChangeBannerSchoolImageButton(Button changeBannerSchoolImageButton) {
        this.changeBannerSchoolImageButton = changeBannerSchoolImageButton;
    }

    public Label getLeftFooterImageLabel() {
        return leftFooterImageLabel;
    }

    public void setLeftFooterImageLabel(Label leftFooterImageLabel) {
        this.leftFooterImageLabel = leftFooterImageLabel;
    }

    public ImageView getLeftFooterImage() {
        return leftFooterImage;
    }

    public void setLeftFooterImage(ImageView leftFooterImage) {
        this.leftFooterImage = leftFooterImage;
    }

    public Button getChangeLeftFooterImageButton() {
        return changeLeftFooterImageButton;
    }

    public void setChangeLeftFooterImageButton(Button changeLeftFooterImageButton) {
        this.changeLeftFooterImageButton = changeLeftFooterImageButton;
    }

    public Label getRightFooterImageLabel() {
        return rightFooterImageLabel;
    }

    public void setRightFooterImageLabel(Label rightFooterImageLabel) {
        this.rightFooterImageLabel = rightFooterImageLabel;
    }

    public ImageView getRightFooterImage() {
        return rightFooterImage;
    }

    public void setRightFooterImage(ImageView rightFooterImage) {
        this.rightFooterImage = rightFooterImage;
    }

    public Button getChangeRighrFooterImageButton() {
        return changeRighrFooterImageButton;
    }

    public void setChangeRighrFooterImageButton(Button changeRighrFooterImageButton) {
        this.changeRighrFooterImageButton = changeRighrFooterImageButton;
    }

    public Label getStylesheetLabel() {
        return stylesheetLabel;
    }

    public void setStylesheetLabel(Label stylesheetLabel) {
        this.stylesheetLabel = stylesheetLabel;
    }

    public ComboBox getStylesheetComboBox() {
        return stylesheetComboBox;
    }

    public void setStylesheetComboBox(ComboBox stylesheetComboBox) {
        this.stylesheetComboBox = stylesheetComboBox;
    }

    public Label getPageStyleNoteLabel() {
        return pageStyleNoteLabel;
    }

    public void setPageStyleNoteLabel(Label pageStyleNoteLabel) {
        this.pageStyleNoteLabel = pageStyleNoteLabel;
    }

    public VBox getCourseSiteContent() {
        return courseSiteContent;
    }

    public void setCourseSiteContent(VBox courseSiteContent) {
        this.courseSiteContent = courseSiteContent;
    }

    
    
    
}
