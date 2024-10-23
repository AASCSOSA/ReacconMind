package com.reacconmind.reacconmind.dto;

public class ReplyDTO {
    private int idReply;
    private int idUser;
    private String userName;
    private int idComment;
    private int idPublication;
    private String content;
    private String contentComment;
    private String contentReply;

    // Constructor
    public ReplyDTO(int idReply, int idUser, String userName, int idComment, int idPublication, String content, String contentComment, String contentReply) {
        this.idReply = idReply;
        this.idUser = idUser;
        this.userName = userName;
        this.idComment = idComment;
        this.idPublication = idPublication;
        this.content = content;
        this.contentComment = contentComment;
        this.contentReply = contentReply;
    }

    // Getters y Setters
    public int getIdReply() {
        return idReply;
    }

    public void setIdReply(int idReply) {
        this.idReply = idReply;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public String getContentReply() {
        return contentReply;
    }

    public void setContentReply(String contentReply) {
        this.contentReply = contentReply;
    }

}
