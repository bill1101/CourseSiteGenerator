package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;

/**
 * This class represents a Teaching Assistant for the table of TAs.
 * 
 * @author tyx
 */
public class TeachingAssistant<E extends Comparable<E>> implements Comparable<E>  {
    // THE TABLE WILL STORE TA NAMES AND EMAILS
    private final BooleanProperty undergrad;
    private final StringProperty name;
    private final StringProperty email;   
    /**
     * Constructor initializes both the TA name and email.
     */
    public TeachingAssistant(String initName, String initEmail, boolean initUndergrad) {
        undergrad = new SimpleBooleanProperty(initUndergrad);
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);        
    }
    
    public TeachingAssistant(String initName, String initEmail) {
        undergrad = new SimpleBooleanProperty(true);
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);       
    }
    // ACCESSORS AND MUTATORS FOR THE PROPERTIES
    public ObservableBooleanValue isUndergrad() {
        return undergrad;
    }
    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String initEmail) {
        email.set(initEmail);
    }
    
    public boolean getUndergrad(){
        return undergrad.get();
    }
    
    public void setUndergrad(boolean initUndergrad){
        undergrad.set(initUndergrad);
    }
    
    @Override
    public int compareTo(E otherTA) {
        return getName().compareTo(((TeachingAssistant)otherTA).getName());
    }
    
    @Override
    public String toString() {
        return name.getValue();
    }
}