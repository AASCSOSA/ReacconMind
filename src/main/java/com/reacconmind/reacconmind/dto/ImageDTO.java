package com.reacconmind.reacconmind.dto;

public class ImageDTO {
    private int idImage;
    private String url;
    private int idPublication;
    private String userName;


    public ImageDTO(int idImage, String url, int idPublication, String userName) {
        this.idImage = idImage;
        this.url = url;
        this.idPublication = idPublication;
        this.userName = userName;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
