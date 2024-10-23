package com.reacconmind.reacconmind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotification;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private int idUser;

    @Enumerated(EnumType.STRING)
    private TypeNotification typeNotification;

    private String content;

    @Enumerated(EnumType.STRING)
    private State state = State.Unread;

    @Column(
        updatable = false,
        insertable = false,
        nullable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private Date dateNotification;

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int userId) {
        this.idUser = userId;
    }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
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

    public void setState(State state) {
        this.state = state;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    @Override
    public String toString() {
        return (
            idNotification +
            " :: " +
            typeNotification +
            " :: " +
            content +
            " :: " +
            state +
            " :: " +
            dateNotification
        );
    }

    public enum TypeNotification {
        Message,
        Like,
        Follow,
        Comment,
        Moderation
    }

    public enum State {
        Read,
        Unread,
    }
}
