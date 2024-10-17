package com.reacconmind.reacconmind.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    private String name;

    private String email;

    private String password;

    private String imageProfile;

    private String imageFacade;

    private String biography;

    private String userName;

    @Enumerated(EnumType.STRING)
    private AuthType typeAuth = AuthType.Email;

    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.Active;

    @Enumerated(EnumType.STRING)
    private ThemeType theme = ThemeType.Light;

    public User() {}

    public User(
        String name,
        String email,
        String password,
        String imageProfile,
        String imageFacade,
        String biography,
        String userName,
        AuthType typeAuth,
        StatusType status,
        ThemeType theme
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.imageProfile = imageProfile;
        this.imageFacade = imageFacade;
        this.biography = biography;
        this.userName = userName;
        this.typeAuth = typeAuth;
        this.status = status;
        this.theme = theme;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getImageFacade() {
        return imageFacade;
    }

    public void setImageFacade(String imageFacade) {
        this.imageFacade = imageFacade;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public AuthType getTypeAuth() {
        return typeAuth;
    }

    public void setTypeAuth(AuthType typeAuth) {
        this.typeAuth = typeAuth;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public ThemeType getTheme() {
        return theme;
    }

    public void setTheme(ThemeType theme) {
        this.theme = theme;
    }
}
