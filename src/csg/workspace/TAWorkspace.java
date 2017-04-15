/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGeneratorApp;
import csg.CSGeneratorProp;
import static csg.CSGeneratorProp.START_END_TIME_INVALID_MESSAGE;
import static csg.CSGeneratorProp.START_END_TIME_INVALID_TITLE;
import csg.data.CSData;
import csg.data.TeachingAssistant;
import csg.jtps.ChangeTime_Transaction;
import csg.jtps.jTPS_Transaction;
import csg.style.CSStyle;
import djf.components.AppDataComponent;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author tyx
 */
public class TAWorkspace {
    // THIS PROVIDES US WITH ACCESS TO THE APP COMPONENTS
    CSGeneratorApp app;
    TAController controller;
    Tab TAData;
    // THIS PROVIDES RESPONSES TO INTERACTIONS WITH THIS WORKSPACE
    //TAController controller;

    // NOTE THAT EVERY CONTROL IS PUT IN A BOX TO HELP WITH ALIGNMENT
    
    // FOR THE HEADER ON THE LEFT
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    Button removeTAButton;
    HBox setTime;
    // FOR THE TA TABLE
    TableView<TeachingAssistant> taTable;
    TableColumn<TeachingAssistant, String> nameColumn;
    TableColumn<TeachingAssistant, String> emailColumn;
    TableColumn<TeachingAssistant, Boolean> undergradColumn;
    // THE TA INPUT
    HBox addBox;
    TextField nameTextField;
    TextField emailTextField;
    Button addButton;
    Button clearButton;
    // THE HEADER ON THE RIGHT
    HBox officeHoursHeaderBox;
    Label officeHoursHeaderLabel;
    
    // THE OFFICE HOURS GRID
    GridPane officeHoursGridPane;
    HashMap<String, Pane> officeHoursGridTimeHeaderPanes;
    HashMap<String, Label> officeHoursGridTimeHeaderLabels;
    HashMap<String, Pane> officeHoursGridDayHeaderPanes;
    HashMap<String, Label> officeHoursGridDayHeaderLabels;
    HashMap<String, Pane> officeHoursGridTimeCellPanes;
    HashMap<String, Label> officeHoursGridTimeCellLabels;
    HashMap<String, Pane> officeHoursGridTACellPanes;
    HashMap<String, Label> officeHoursGridTACellLabels;
    
    
    HBox sPane;
    ScrollPane scrollPane;
    String updateName;
    String updateEmail;
    VBox rightPane;
    boolean isAdd;
    
    /**
     * The contstructor initializes the user interface, except for
     * the full office hours grid, since it doesn't yet know what
     * the hours will be until a file is loaded or a new one is created.
     */
    public TAWorkspace(CSGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        
        
        app = initApp;
        isAdd = true;
        // WE'LL NEED THIS TO GET LANGUAGE PROPERTIES FOR OUR UI
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        TAData = new Tab();
        TAData.setText(props.getProperty(CSGeneratorProp.TAB_TITLE_TA_DATA.toString()));
        TAData.setClosable(false);
        
        
        // INIT THE HEADER ON THE LEFT
        tasHeaderBox = new HBox(20);
        String tasHeaderText = props.getProperty(CSGeneratorProp.TAS_HEADER_TEXT.toString());
        tasHeaderLabel = new Label(tasHeaderText);
        removeTAButton = new Button(props.getProperty(CSGeneratorProp.REMOVE_TA_TEXT.toString()));
        tasHeaderBox.getChildren().addAll(tasHeaderLabel,removeTAButton);

        // MAKE THE TABLE AND SETUP THE DATA MODEL
        taTable = new TableView();
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSData data = (CSData) app.getDataComponent();
        ObservableList<TeachingAssistant> tableData = data.getTeachingAssistants();
        taTable.setItems(tableData);
        taTable.getColumns();
        String nameColumnText = props.getProperty(CSGeneratorProp.NAME_COLUMN_TEXT.toString());
        String emailColumnText = props.getProperty(CSGeneratorProp.EMAIL_COLUMN_TEXT.toString());
        nameColumn = new TableColumn(nameColumnText);    
        emailColumn = new TableColumn(emailColumnText);
        undergradColumn = new TableColumn(props.getProperty(CSGeneratorProp.UNDERGRAD_COLUMN_TEXT.toString()));
        //useColumn.setCellValueFactory(new PropertyValueFactory<SitePage, Boolean>("use"));
        undergradColumn.setCellFactory(column -> new CheckBoxTableCell());     
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("name")
        );
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("email")
        );
        taTable.getColumns().add(undergradColumn);
        taTable.getColumns().add(nameColumn);
        taTable.getColumns().add(emailColumn);
        taTable.setEditable(true);
        // ADD BOX FOR ADDING A TA
        String namePromptText = props.getProperty(CSGeneratorProp.NAME_PROMPT_TEXT.toString());
        String emailPromptText = props.getProperty(CSGeneratorProp.EMAIL_PROMPT_TEXT.toString());
        String addButtonText = props.getProperty(CSGeneratorProp.ADD_BUTTON_TEXT.toString());
        String clearButtonText = props.getProperty(CSGeneratorProp.CLEAR_BUTTON_TEXT.toString());
        String updateButtonText = props.getProperty(CSGeneratorProp.UPDATE_BUTTON_TEXT.toString());
        nameTextField = new TextField();
        emailTextField = new TextField();
        nameTextField.setPromptText(namePromptText);
        emailTextField.setPromptText(emailPromptText);
        addButton = new Button(addButtonText);
        addBox = new HBox(10);
        nameTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.3));
        emailTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.3));
        addButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        addBox.getChildren().add(nameTextField);
        addBox.getChildren().add(emailTextField);
        addBox.getChildren().add(addButton);
        
        //updateButton = new Button("Update");
        //updateButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        
        clearButton = new Button(clearButtonText);
        clearButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        addBox.getChildren().add(clearButton);
        
        taTable.setRowFactory(event -> {
            TableRow<TeachingAssistant> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (! row.isEmpty() && e.getButton()== MouseButton.PRIMARY) {

                    TeachingAssistant clickedRow = row.getItem();
                    nameTextField.setText(clickedRow.getName());
                    emailTextField.setText(clickedRow.getEmail());
                    
                    addButton.setText(updateButtonText);
                    isAdd = false;

                    updateName = clickedRow.getName();
                    updateEmail = clickedRow.getEmail();
                }
            });
            return row ;
        });
        
        
        
        // INIT THE HEADER ON THE RIGHT
        officeHoursHeaderBox = new HBox(300);
        String officeHoursGridText = props.getProperty(CSGeneratorProp.OFFICE_HOURS_SUBHEADER.toString());
        officeHoursHeaderLabel = new Label(officeHoursGridText);
        officeHoursHeaderBox.getChildren().add(officeHoursHeaderLabel);
        
        // THESE WILL STORE PANES AND LABELS FOR OUR OFFICE HOURS GRID
        officeHoursGridPane = new GridPane();
        officeHoursGridTimeHeaderPanes = new HashMap();
        officeHoursGridTimeHeaderLabels = new HashMap();
        officeHoursGridDayHeaderPanes = new HashMap();
        officeHoursGridDayHeaderLabels = new HashMap();
        officeHoursGridTimeCellPanes = new HashMap();
        officeHoursGridTimeCellLabels = new HashMap();
        officeHoursGridTACellPanes = new HashMap();
        officeHoursGridTACellLabels = new HashMap();

        
        
        // ORGANIZE THE LEFT AND RIGHT PANES
        VBox leftPane = new VBox(10);
        leftPane.getChildren().add(tasHeaderBox);        
        leftPane.getChildren().add(taTable);        
        leftPane.getChildren().add(addBox);
        rightPane = new VBox(10);
        rightPane.getChildren().add(officeHoursHeaderBox);
        rightPane.getChildren().add(officeHoursGridPane);
        //time
        setTime = new HBox();
        setTime.setAlignment(Pos.CENTER_RIGHT);
        officeHoursHeaderBox.getChildren().add(setTime);
        ObservableList<String> options_start = 
            FXCollections.observableArrayList(
                "00:00am",
                "1:00am",
                "2:00am",
                "3:00am",
                "4:00am",
                "5:00am",
                "6:00am",
                "7:00am",
                "8:00am",
                "9:00am",
                "10:00am",
                "11:00am",
                "12:00pm",
                "1:00pm",
                "2:00pm",
                "3:00pm",
                "4:00pm",
                "5:00pm",
                "6:00pm",
                "7:00pm",
                "8:00pm",
                "9:00pm",
                "10:00pm",
                "11:00pm"
            );
        
        final ComboBox comboBox_start = new ComboBox(options_start);
        
        comboBox_start.setOnAction(e -> {

            CSData taData = (CSData)app.getDataComponent();
            int originalStart = taData.getStartHour();
            int originalEnd = taData.getEndHour();
                    
            String time = comboBox_start.getValue().toString();
            int hour = Integer.parseInt(time.substring(0, time.indexOf(':')));
            
            if("pm".equals(time.substring(time.length()-2, time.length())) && hour!=12){
                hour += 12;
            }
            
            if(hour >= taData.getEndHour()){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(START_END_TIME_INVALID_TITLE), props.getProperty(START_END_TIME_INVALID_MESSAGE));
            }else{
                boolean warning = checkTime(taData.getStartHour(),hour);
                boolean confirm = true;
                
                if(warning){
                    confirm = promptToChangeTime();
                }
                if(confirm){
                    jTPS_Transaction changeStartTransaction = new ChangeTime_Transaction(app,originalStart,originalEnd,hour,originalEnd);
                    CSWorkspace.jTPS.addTransaction(changeStartTransaction);
                    //taData.setStartHour(hour);
                    //reloadOfficeHoursGrid2(taData,originalStart,originalEnd);
                    //app.getGUI().getFileController().markAsEdited(app.getGUI());
                }
            }
        });
        
        ObservableList<String> options_end = 
            FXCollections.observableArrayList(
                "00:00am",
                "1:00am",
                "2:00am",
                "3:00am",
                "4:00am",
                "5:00am",
                "6:00am",
                "7:00am",
                "8:00am",
                "9:00am",
                "10:00am",
                "11:00am",
                "12:00pm",
                "1:00pm",
                "2:00pm",
                "3:00pm",
                "4:00pm",
                "5:00pm",
                "6:00pm",
                "7:00pm",
                "8:00pm",
                "9:00pm",
                "10:00pm",
                "11:00pm"
            );
        final ComboBox comboBox_end = new ComboBox(options_end);
        
        comboBox_end.setOnAction(e -> {
            CSData taData = (CSData)app.getDataComponent();
            int originalStart = taData.getStartHour();
            int originalEnd = taData.getEndHour();
                 
            String time = comboBox_end.getValue().toString();
            
            int hour = Integer.parseInt(time.substring(0, time.indexOf(':')));
                   
            if("pm".equals(time.substring(time.length()-2, time.length())) && hour!=12){
                hour += 12;
            }
            if(time.equals("00:00am"))
                hour = 24;
            //System.out.println(hour);
            if(taData.getStartHour() >= hour){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(START_END_TIME_INVALID_TITLE), props.getProperty(START_END_TIME_INVALID_MESSAGE));
            }else{
                boolean warning = checkEndTime(taData.getEndHour(),hour);
                boolean confirm = true;
                
                if(warning){
                    confirm = promptToChangeTime();
                }
                if(confirm){
                    jTPS_Transaction changeEndTransaction = new ChangeTime_Transaction(app,originalStart,originalEnd,originalStart,hour);
                    CSWorkspace.jTPS.addTransaction(changeEndTransaction);
                    //taData.setEndHour(hour);
                    //reloadOfficeHoursGrid2(taData,originalStart,originalEnd);
                    //app.getGUI().getFileController().markAsEdited(app.getGUI());
                }
            }
        });
        
        setTime.getChildren().add(new Label(props.getProperty(CSGeneratorProp.START_TIME)));
        setTime.getChildren().add(comboBox_start);
        setTime.getChildren().add(new Label(props.getProperty(CSGeneratorProp.END_TIME)));
        setTime.getChildren().add(comboBox_end);
        
        scrollPane = new ScrollPane(rightPane);
        scrollPane.setFitToWidth(true);
        // BOTH PANES WILL NOW GO IN A SPLIT PANE
        //sPane = new SplitPane(leftPane, new VBox(), scrollPane);
        
        //sPane.setDividerPositions(0.3,0.31);
        sPane = new HBox(20);
        leftPane.setMinWidth(500);
        leftPane.setMaxWidth(500);
        scrollPane.setMaxWidth(1100);
        scrollPane.setMinWidth(1100);
        sPane.getChildren().addAll(leftPane, scrollPane);
        TAData.setContent(sPane);
        /*
        workspace = new BorderPane();
        
        // AND PUT EVERYTHING IN THE WORKSPACE
        ((BorderPane) workspace).setCenter(sPane);
        */
        // MAKE SURE THE TABLE EXTENDS DOWN FAR ENOUGH
        taTable.prefHeightProperty().bind(sPane.heightProperty().multiply(1.9));

        // NOW LET'S SETUP THE EVENT HANDLING
        controller = new TAController(app);

        // CONTROLS FOR ADDING TAs
        nameTextField.setOnAction(e -> {
            controller.handleAddTA();
        });
        emailTextField.setOnAction(e -> {
            controller.handleAddTA();
        });
        addButton.setOnAction(e -> {
            if(isAdd){
                System.out.println(controller);
                controller.handleAddTA();
            }
            else{
                controller.handleEditTA(updateName, updateEmail);
                taTable.refresh();
                //for (Pane p : officeHoursGridTACellPanes.values()) {
                //    controller.handleCellEdit(p);
                //}
                addButton.setText(addButtonText);
            }
        });
        
        clearButton.setOnAction(e -> {
            nameTextField.setText("");
            emailTextField.setText("");
            isAdd = true;
            addButton.setText(addButtonText);
            nameTextField.requestFocus();
        });
        taTable.setFocusTraversable(true);
        taTable.setOnKeyPressed(e -> {
            controller.handleKeyPress(e.getCode());
        });
        sPane.setOnKeyPressed(e -> {
            controller.handleKeyPress(e);
        });
        
    }
    
    
    // WE'LL PROVIDE AN ACCESSOR METHOD FOR EACH VISIBLE COMPONENT
    // IN CASE A CONTROLLER OR STYLE CLASS NEEDS TO CHANGE IT

    public Tab getTAData() {
        return TAData;
    }

    public TableColumn<TeachingAssistant, Boolean> getUndergradColumn() {
        return undergradColumn;
    }

    public VBox getRightPane() {
        return rightPane;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }    
 
    public HBox getTAsHeaderBox() {
        return tasHeaderBox;
    }

    public Label getTAsHeaderLabel() {
        return tasHeaderLabel;
    }

    public TableView getTATable() {
        return taTable;
    }

    public HBox getAddBox() {
        return addBox;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public Button getAddButton() {
        return addButton;
    }
    
    public Button getClearButton() {
        return clearButton;
    }
    
    public HBox getOfficeHoursSubheaderBox() {
        return officeHoursHeaderBox;
    }

    public Label getOfficeHoursSubheaderLabel() {
        return officeHoursHeaderLabel;
    }

    public GridPane getOfficeHoursGridPane() {
        return officeHoursGridPane;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeHeaderPanes() {
        return officeHoursGridTimeHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeHeaderLabels() {
        return officeHoursGridTimeHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridDayHeaderPanes() {
        return officeHoursGridDayHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridDayHeaderLabels() {
        return officeHoursGridDayHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeCellPanes() {
        return officeHoursGridTimeCellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeCellLabels() {
        return officeHoursGridTimeCellLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTACellPanes() {
        return officeHoursGridTACellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTACellLabels() {
        return officeHoursGridTACellLabels;
    }
    //
    public void setOfficeHoursGridTACellLabels(HashMap<String, Label> label){
        officeHoursGridTACellLabels = label;
    }
    
    public String getCellKey(Pane testPane) {
        for (String key : officeHoursGridTACellLabels.keySet()) {
            if (officeHoursGridTACellPanes.get(key) == testPane) {
                return key;
            }
        }
        return null;
    }

    public Label getTACellLabel(String cellKey) {
        return officeHoursGridTACellLabels.get(cellKey);
    }

    public Pane getTACellPane(String cellPane) {
        return officeHoursGridTACellPanes.get(cellPane);
    }

    public String buildCellKey(int col, int row) {
        return "" + col + "_" + row;
    }

    public String buildCellText(int militaryHour, String minutes) {
        // FIRST THE START AND END CELLS
        if(militaryHour == 24){
            return "00:"+minutes+"am";
        }
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutes;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }

    //@Override
    public void resetWorkspace() {
        // CLEAR OUT THE GRID PANE
        officeHoursGridPane.getChildren().clear();
        
        // AND THEN ALL THE GRID PANES AND LABELS
        officeHoursGridTimeHeaderPanes.clear();
        officeHoursGridTimeHeaderLabels.clear();
        officeHoursGridDayHeaderPanes.clear();
        officeHoursGridDayHeaderLabels.clear();
        officeHoursGridTimeCellPanes.clear();
        officeHoursGridTimeCellLabels.clear();
        officeHoursGridTACellPanes.clear();
        officeHoursGridTACellLabels.clear();
    }
  
    //@Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        CSData taData = (CSData)dataComponent;
        reloadOfficeHoursGrid(taData);
    }

    public void reloadOfficeHoursGrid2(CSData dataComponent,int originalStart,int originalEnd) {        
        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();
        //HashMap<String, Label> officeHoursGridTACellLabelsTemp = new HashMap<String, Label>(officeHoursGridTACellLabels);
        
//        for (String s : officeHoursGridTACellLabels.keySet())
//        {
//            String content = officeHoursGridTACellLabels.get(s).getText();
//            officeHoursGridTACellLabelsTemp.put(new String(s), new String(content));
//        }
        HashMap<String, String> officeHoursGridTACellLabelsTemp = new HashMap<String, String>();
        for (String s : officeHoursGridTACellLabels.keySet())
        {   
            
            String content = officeHoursGridTACellLabels.get(s).getText();
            officeHoursGridTACellLabelsTemp.put(s, content);
        }
        resetWorkspace();
        
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }       
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
        
        int row = 1;
        int row_diff = 2*(dataComponent.getStartHour() - originalStart);
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) {
            // START TIME COLUMN
            int col = 0;
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
                dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
                dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));
                // END TIME COLUMN
                col++;
                int endHour = i;
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
                dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
                dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
                col++;

                // AND NOW ALL THE TA TOGGLE CELLS
                while (col < 7) {
                    addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                    dataComponent.getCellTextProperty(col, row).set(officeHoursGridTACellLabelsTemp.get(dataComponent.getCellKey(col, row+row_diff)));
                    
                    addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);                   
                    dataComponent.getCellTextProperty(col, row+1).set(officeHoursGridTACellLabelsTemp.get(dataComponent.getCellKey(col, row+1+row_diff)));

                    col++;
                }
                row += 2;
            
        }
        for(String s:dataComponent.getOfficeHours().keySet())
            if(dataComponent.getOfficeHours().get(s).getValue() == null)
                dataComponent.getOfficeHours().get(s).setValue("");
        
        // CONTROLS FOR TOGGLING TA OFFICE HOURS
        for (Pane p : officeHoursGridTACellPanes.values()) {
            p.setFocusTraversable(true);
            p.setOnKeyPressed(e -> {
                controller.handleKeyPress(e.getCode());
            });
            p.setOnMouseClicked(e -> {
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseExited(e -> {
                controller.handleGridCellMouseExited((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
                controller.handleGridCellMouseEntered((Pane) e.getSource());
            });
        }
        
        CSStyle taStyle = (CSStyle)app.getStyleComponent();
        taStyle.initOfficeHoursGridStyle();
    }
    
    public void reloadOfficeHoursGrid(CSData dataComponent) {        
        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();

        // ADD THE TIME HEADERS
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
        
        // THEN THE TIME AND TA CELLS
        int row = 1;
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) {
            //System.out.println(i);
            // START TIME COLUMN
            int col = 0;
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
                dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
                dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));
                // END TIME COLUMN
                col++;
                int endHour = i;
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
                dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
                dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
                col++;

                // AND NOW ALL THE TA TOGGLE CELLS
                while (col < 7) {
                    addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                    addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);
                    col++;
                }
                row += 2;
            
        }
        
        // CONTROLS FOR TOGGLING TA OFFICE HOURS
        for (Pane p : officeHoursGridTACellPanes.values()) {
            p.setFocusTraversable(true);
            p.setOnKeyPressed(e -> {
                controller.handleKeyPress(e.getCode());
            });
            p.setOnMouseClicked(e -> {
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseExited(e -> {
                controller.handleGridCellMouseExited((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
                controller.handleGridCellMouseEntered((Pane) e.getSource());
            });
        }
        
        // AND MAKE SURE ALL THE COMPONENTS HAVE THE PROPER STYLE
        CSStyle taStyle = (CSStyle)app.getStyleComponent();
        taStyle.initOfficeHoursGridStyle();
    }
    
    public void reloadOfficeHoursGrid3(CSData dataComponent,HashMap<String, String> officeHoursGridTACellLabelsTemp) {        
        resetWorkspace();
        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();

        // ADD THE TIME HEADERS
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
        
        // THEN THE TIME AND TA CELLS
        int row = 1;
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) {
            //System.out.println(i);
            // START TIME COLUMN
            int col = 0;
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
                dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
                dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));
                // END TIME COLUMN
                col++;
                int endHour = i;
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
                dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
                addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
                dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
                col++;

                // AND NOW ALL THE TA TOGGLE CELLS
                while (col < 7) {
                    addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                    dataComponent.getCellTextProperty(col, row).set(officeHoursGridTACellLabelsTemp.get(dataComponent.getCellKey(col, row)));

                    addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);                   
                    dataComponent.getCellTextProperty(col, row+1).set(officeHoursGridTACellLabelsTemp.get(dataComponent.getCellKey(col, row+1)));


                    col++;
                }
                row += 2;
            
        }
        for(String s:dataComponent.getOfficeHours().keySet())
            if(dataComponent.getOfficeHours().get(s).getValue() == null)
                dataComponent.getOfficeHours().get(s).setValue("");
        // CONTROLS FOR TOGGLING TA OFFICE HOURS
        for (Pane p : officeHoursGridTACellPanes.values()) {
            p.setFocusTraversable(true);
            p.setOnKeyPressed(e -> {
                controller.handleKeyPress(e.getCode());
            });
            p.setOnMouseClicked(e -> {
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseExited(e -> {
                controller.handleGridCellMouseExited((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
                controller.handleGridCellMouseEntered((Pane) e.getSource());
            });
        }
        
        // AND MAKE SURE ALL THE COMPONENTS HAVE THE PROPER STYLE
        CSStyle taStyle = (CSStyle)app.getStyleComponent();
        taStyle.initOfficeHoursGridStyle();
    }
    
    public void addCellToGrid(CSData dataComponent, HashMap<String, Pane> panes, HashMap<String, Label> labels, int col, int row) {       
        // MAKE THE LABEL IN A PANE
        Label cellLabel = new Label("");
        HBox cellPane = new HBox();
        cellPane.setAlignment(Pos.CENTER);
        cellPane.getChildren().add(cellLabel);

        // BUILD A KEY TO EASILY UNIQUELY IDENTIFY THE CELL
        String cellKey = dataComponent.getCellKey(col, row);
        cellPane.setId(cellKey);
        cellLabel.setId(cellKey);
        
        // NOW PUT THE CELL IN THE WORKSPACE GRID
        officeHoursGridPane.add(cellPane, col, row);
        
        // AND ALSO KEEP IN IN CASE WE NEED TO STYLIZE IT
        panes.put(cellKey, cellPane);
        labels.put(cellKey, cellLabel);
        
        // AND FINALLY, GIVE THE TEXT PROPERTY TO THE DATA MANAGER
        // SO IT CAN MANAGE ALL CHANGES
        dataComponent.setCellProperty(col, row, cellLabel.textProperty());        
    }
    
    
    private boolean promptToChangeTime() {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	
	// CHECK TO SEE IF THE CURRENT WORK HAS
	// BEEN SAVED AT LEAST ONCE
	
        // PROMPT THE USER TO SAVE UNSAVED WORK
	AppYesNoCancelDialogSingleton yesNoDialog = AppYesNoCancelDialogSingleton.getSingleton();
        yesNoDialog.show(props.getProperty(CSGeneratorProp.CHANGE_TIME_WARNING_TITLE), props.getProperty(CSGeneratorProp.CHANGE_TIME_WARNING_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(AppYesNoCancelDialogSingleton.YES)) {
            // SAVE THE DATA FILE
            return true;
        }
        else if (selection.equals(AppYesNoCancelDialogSingleton.CANCEL)) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return false;
    }

    private boolean checkTime(int oldHour, int newHour) {
        int diff = newHour - oldHour;
        //System.out.println(diff);
        if(diff>0){
            
            for (String s : officeHoursGridTACellLabels.keySet()) {
                //System.out.print(s+" ");
                for(int i=1;i<=2*diff;i++){
                    if(s.endsWith("_"+i))
                        //System.out.print(officeHoursGridTACellLabels.get(s));
                    if(s.endsWith("_"+i) && !officeHoursGridTACellLabels.get(s).getText().equals("")){
                        return true;
                    }
                }
                //System.out.println();
                //System.out.println();
            }
        }
        return false;
    }

    private boolean checkEndTime(int oldHour, int newHour) {
        int diff = oldHour - newHour;
        CSData taData = (CSData)app.getDataComponent();
        //System.out.println(oldHour);
        //System.out.println(taData.getStartHour());
        if(diff>0){
            //System.out.println(diff);
            
            for (String s : officeHoursGridTACellLabels.keySet()) {
                //System.out.print(s+" ");
                for(int i=(oldHour-taData.getStartHour())*2;i>=(oldHour-taData.getStartHour())*2-2*diff+1;i--){
                    //System.out.println("i " +i);
                    if(s.endsWith("_"+i))
                        //System.out.print(officeHoursGridTACellLabels.get(s));
                    if(s.endsWith("_"+i) && !officeHoursGridTACellLabels.get(s).getText().equals("")){
                        return true;
                    }
                }
                //System.out.println();
                //System.out.println();
            }
        }
        return false;
    }

    public CSGeneratorApp getApp() {
        return app;
    }

    public void setApp(CSGeneratorApp app) {
        this.app = app;
    }

    public TAController getController() {
        return controller;
    }

    public void setController(TAController controller) {
        this.controller = controller;
    }

    public HBox getTasHeaderBox() {
        return tasHeaderBox;
    }

    public void setTasHeaderBox(HBox tasHeaderBox) {
        this.tasHeaderBox = tasHeaderBox;
    }

    public Label getTasHeaderLabel() {
        return tasHeaderLabel;
    }

    public void setTasHeaderLabel(Label tasHeaderLabel) {
        this.tasHeaderLabel = tasHeaderLabel;
    }

    public HBox getSetTime() {
        return setTime;
    }

    public void setSetTime(HBox setTime) {
        this.setTime = setTime;
    }

    public TableView<TeachingAssistant> getTaTable() {
        return taTable;
    }

    public void setTaTable(TableView<TeachingAssistant> taTable) {
        this.taTable = taTable;
    }

    public TableColumn<TeachingAssistant, String> getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(TableColumn<TeachingAssistant, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public TableColumn<TeachingAssistant, String> getEmailColumn() {
        return emailColumn;
    }

    public void setEmailColumn(TableColumn<TeachingAssistant, String> emailColumn) {
        this.emailColumn = emailColumn;
    }

    public HBox getOfficeHoursHeaderBox() {
        return officeHoursHeaderBox;
    }

    public void setOfficeHoursHeaderBox(HBox officeHoursHeaderBox) {
        this.officeHoursHeaderBox = officeHoursHeaderBox;
    }

    public Label getOfficeHoursHeaderLabel() {
        return officeHoursHeaderLabel;
    }

    public void setOfficeHoursHeaderLabel(Label officeHoursHeaderLabel) {
        this.officeHoursHeaderLabel = officeHoursHeaderLabel;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateEmail() {
        return updateEmail;
    }

    public void setUpdateEmail(String updateEmail) {
        this.updateEmail = updateEmail;
    }

    public boolean isIsAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }

    public HBox getsPane() {
        return sPane;
    }

    public Button getRemoveTAButton() {
        return removeTAButton;
    }


}
