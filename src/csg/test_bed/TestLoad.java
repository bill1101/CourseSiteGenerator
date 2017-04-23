/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.test_bed;

import csg.CSGeneratorApp;
import csg.data.CSData;
import csg.file.CSFiles;
import djf.settings.AppStartupConstants;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tyx
 */
public class TestLoad {
    static CSGeneratorApp csgenerator;
    static CSData csData;
    static CSFiles csFiles;
    
    public static void main(String[] args){
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
            csFiles.loadData(csData, "./work/hardcode.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
