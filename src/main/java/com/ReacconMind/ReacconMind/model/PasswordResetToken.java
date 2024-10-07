package com.ReacconMind.ReacconMind.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResetToken;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user; // Relación con el usuario
    private String token;
    private Date expirationDate;
    private boolean used;
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }
    public int getIdResetToken() {
        return idResetToken;
    }
    public void setIdResetToken(int idResetToken) {
        this.idResetToken = idResetToken;
    }

    
}
