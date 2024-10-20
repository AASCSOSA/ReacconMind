package com.reacconmind.reacconmind.dto;

public class ModerationResponseDTO {
    private int publicationId;
    private String status;
    private String message;
    private String dateModeration;

    public ModerationResponseDTO(int publicationId, String status, String message, String dateModeration) {
        this.publicationId = publicationId;
        this.status = status;
        this.message = message;
        this.dateModeration = dateModeration;
    }

    // Getters y setters
    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateModeration() {
        return dateModeration;
    }

    public void setDateModeration(String dateModeration) {
        this.dateModeration = dateModeration;
    }
}
