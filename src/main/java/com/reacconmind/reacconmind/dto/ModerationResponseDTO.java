package com.reacconmind.reacconmind.dto;

public class ModerationResponseDTO {
    private int publicationId;
    private String status;
    private String message;

    public ModerationResponseDTO(int publicationId, String status, String message) {
        this.publicationId = publicationId;
        this.status = status;
        this.message = message;
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
}