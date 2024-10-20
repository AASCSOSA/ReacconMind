package com.reacconmind.reacconmind.model;




import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.IdClass;

import java.sql.Timestamp;

@Entity
@Table(name = "Reaction")
@IdClass(ReactionPK.class)
public class Reaction {

    @Id
    @Column(name = "idUser", nullable = false)
    private int idUser;

    @Id
    @Column(name = "idPublication", nullable = false)
    private int idPublication;

    @Id
    @Column(name = "idComment", nullable = false)
    private int idComment;

    @Column(name = "liked", nullable = false)
    private boolean liked;



    // Constructor vacío (requerido por JPA)
    public Reaction() {}

    // Constructor con parámetros
    public Reaction(int idUser, int idPublication, int idComment, boolean liked) {
        this.idUser = idUser;
        this.idPublication = idPublication;
        this.idComment = idComment;
        this.liked = liked;

    }

    // Getters y Setters
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
