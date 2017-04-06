/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tyx
 */
public class Recitation <E extends Comparable<E>> implements Comparable<E> {
    private final StringProperty section;
    private final StringProperty instructor;
    private final StringProperty dayTime;
    private final StringProperty location;
    private final StringProperty TA1;
    private final StringProperty TA2;

    public Recitation(String section, String instructor, String dayTime, String location, String TA1, String TA2) {
        this.section = new SimpleStringProperty(section);
        this.instructor = new SimpleStringProperty(instructor);
        this.dayTime = new SimpleStringProperty(dayTime);
        this.location = new SimpleStringProperty(location);
        this.TA1 = new SimpleStringProperty(TA1);
        this.TA2 = new SimpleStringProperty(TA2);
    }

    public String getSection() {
        return section.get();
    }
    
    public void setSection(String section){
        this.section.set(section);
    }

    public String getInstructor() {
        return instructor.get();
    }
    
    public void setInstructor(String instructor) {
        this.instructor.set(instructor);
    }

    public String getDayTime() {
        return dayTime.get();
    }
    
    public void setDayTime(String dayTime) {
        this.dayTime.set(dayTime);
    }

    public String getLocation() {
        return location.get();
    }
    
    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getTA1() {
        return TA1.get();
    }
    
    public void setTA1(String TA1) {
        this.TA1.set(TA1);
    }

    public String getTA2() {
        return TA2.get();
    }
    
    public void setTA2(String TA2) {
        this.TA2.set(TA2);
    }

    @Override
    public int compareTo(E otherRecitation) {
        return getSection().compareTo(((Recitation)otherRecitation).getSection());
    }

    @Override
    public String toString() {
        return section.getValue() + " " + instructor.getValue();
    }
}
