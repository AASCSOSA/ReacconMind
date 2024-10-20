package com.reacconmind.reacconmind.model;

import java.io.Serializable;
import java.util.Objects;

public class ReactionId implements Serializable {
    private int idUser;
    private int idPublication;
    private int idComment;

    // Getters, Setters, equals, and hashCode
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReactionId)) return false;
        ReactionId that = (ReactionId) o;
        return idUser == that.idUser &&
                idPublication == that.idPublication &&
                idComment == that.idComment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPublication, idComment);
    }
}
