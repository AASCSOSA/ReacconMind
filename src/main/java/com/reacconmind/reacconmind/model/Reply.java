package com.reacconmind.reacconmind.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReply;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, foreignKey = @ForeignKey(name = "FK_User_Reply"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "idComment", nullable = false, foreignKey = @ForeignKey(name = "FK_Comment_Reply"))
    private Comment comment;

    @Column(name = "contentReply", nullable = false, length = 255)
    private String contentReply;



    // Constructors, getters, and setters

    public Reply() {}

    public Reply(User user, Comment comment, String contentReply) {
        this.user = user;
        this.comment = comment;
        this.contentReply = contentReply;

    }

    public int getIdReply() {
        return idReply;
    }

    public void setIdReply(int idReply) {
        this.idReply = idReply;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getContentReply() {
        return contentReply;
    }

    public void setContentReply(String contentReply) {
        this.contentReply = contentReply;
    }


}
