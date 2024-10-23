package com.reacconmind.reacconmind.dto;

import java.sql.Date;

import com.reacconmind.reacconmind.model.ThemeBotType;
import com.reacconmind.reacconmind.model.ThemeType;

public class UserDTO {
    private String name;
    private String email;
    private String userName;
    private String biography;
    private String imageProfile;
    private String imageFacade;
    private String thumbnail;
    private ThemeType theme;
    private ThemeBotType themeBot;
    private Date dateCreationProfile;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ThemeType getTheme() {
        return theme;
    }

    public void setTheme(ThemeType theme) {
        this.theme = theme;
    }

    public ThemeBotType getThemeBot() {
        return themeBot;
    }

    public void setThemeBot(ThemeBotType themeBot) {
        this.themeBot = themeBot;
    }

    public Date getDateCreationProfile() {
        return dateCreationProfile;
    }

    public void setDateCreationProfile(Date dateCreationProfile) {
        this.dateCreationProfile = dateCreationProfile;
    }

}
