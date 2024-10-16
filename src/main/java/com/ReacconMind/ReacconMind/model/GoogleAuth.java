package com.ReacconMind.ReacconMind.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GoogleAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGoogleAuth;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    private String googleId;

    public int getIdGoogleAuth() {
        return idGoogleAuth;
    }

    public void setIdGoogleAuth(int idGoogleAuth) {
        this.idGoogleAuth = idGoogleAuth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
