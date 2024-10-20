package com.reacconmind.reacconmind.model;


import jakarta.persistence.*;


import java.sql.Timestamp;

@Entity
@IdClass(ReactionId.class)
@Table(name = "Reaction")
public class Reaction {

    @Id
    @Column(name = "idUser", nullable = false)
    private int idUser;

    @Id
    @Column(name = "idPublication")
    private int idPublication;

    @Id
    @Column(name = "idComment")
    private int idComment;

    @Column(nullable = false)
    private boolean liked; // true: le gusta, false: no le gusta



    // Getters and Setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }


}
