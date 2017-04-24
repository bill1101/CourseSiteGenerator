/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;

/**
 *
 * @author tyx
 */
public class SitePage<E extends Comparable<E>> implements Comparable<E> {
    private final BooleanProperty use;
    private final StringProperty navBarTitle;
    private final StringProperty fileName;
    private final StringProperty script;

    public SitePage(Boolean use, String navBarTitle, String fileName, String script) {
        this.use = new SimpleBooleanProperty(use);
        this.navBarTitle = new SimpleStringProperty(navBarTitle);
        this.fileName = new SimpleStringProperty(fileName);
        this.script = new SimpleStringProperty(script);
    }
    public ObservableBooleanValue isUse() {
        return use;
    }    
    public boolean getUse() {
        return use.get();
    }
    
    public void setUse(boolean use){
        this.use.set(use);
    }

    public String getNavBarTitle() {
        return navBarTitle.get();
    }
    
    public void setNavBar(String navBarTitle){
        this.navBarTitle.set(navBarTitle);
    }

    public String getFileName() {
        return fileName.get();
    }
    
    public void setFileName(String fileName){
        this.fileName.set(fileName);
    }

    public String getScript() {
        return script.get();
    }
    
    public void setScript(String script){
        this.script.set(script);
    }

    @Override
    public int compareTo(E otherSitePage) {
        if(getUse()==true && ((SitePage)otherSitePage).getUse()==false){
            return 1;
        }else if(getUse()==false && ((SitePage)otherSitePage).getUse()==true){
            return -1;
        }else{
            return getNavBarTitle().compareTo(((SitePage)otherSitePage).getNavBarTitle());
        }
    }
    
    @Override
    public String toString() {
        return navBarTitle.getValue();
    }
    
}
