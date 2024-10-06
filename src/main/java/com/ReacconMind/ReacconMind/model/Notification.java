package com.ReacconMind.ReacconMind.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_notification;
    private TypeNotification type_notification;
    private String content;
    private State state;
    private Date dateNotification;

    
    public Notification(int id_notification, TypeNotification type_notification, String content, State state,
            Date dateNotification) {
        this.id_notification = id_notification;
        this.type_notification = type_notification;
        this.content = content;
        this.state = state;
        this.dateNotification = dateNotification;
    }

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public TypeNotification getType_notification() {
        return type_notification;
    }

    public void setType_notification(TypeNotification type_notification) {
        this.type_notification = type_notification;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public State getState() {
        return state;
    }

    public void setState(State estate) {
        this.state = estate;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }


    @Override
    public String toString(){
        return id_notification + " :: " + type_notification + " :: " + content + " :: " + state + " :: " + dateNotification;
    }

    public enum TypeNotification {
        MESSAGE,
        LIKE,
        FOLLOW,
        COMMENT,
    }

    public enum State {
        READ,
        UNREAD
    }
}