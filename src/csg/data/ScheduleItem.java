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
public class ScheduleItem<E extends Comparable<E>> implements Comparable<E> {
    
    private final StringProperty type;
    private final StringProperty date;
    private final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    private final StringProperty time;
    private final StringProperty criteria;

    public ScheduleItem(String type, String date ,String time, String title, String topic, String link, String criteria) {
        this.type = new SimpleStringProperty(type);
        this.date = new SimpleStringProperty(date);
        this.title = new SimpleStringProperty(title);
        this.topic = new SimpleStringProperty(topic);
        this.link = new SimpleStringProperty(link);
        this.time = new SimpleStringProperty(time);
        this.criteria = new SimpleStringProperty(criteria);
    }
    public String getTime() {
        return time.get();
    }
    public void setTime(String time) {
        this.time.set(time);
    }
    public String getLink() {
        return link.get();
    }
    public void setLink(String link) {
        this.link.set(link);
    }
    
    public String getCriteria() {
        return criteria.get();
    }
    public void setCriteria(String criteria) {
        this.criteria.set(criteria);
    }
    
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }
    
    public String getDate() {
        return date.get();
    }
    
    public void setDate(String date) {
        this.date.set(date);
    }
    
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
    
    public String getTopic() {
        return topic.get();
    }
    
    public void setTopic(String topic) {
        this.topic.set(topic);
    }
    
    @Override
    public int compareTo(E otherDate) {
        return this.getDate().compareTo(((ScheduleItem)otherDate).getDate());
    }

    @Override
    public String toString() {
        return date.getValue();
    }
          
}
