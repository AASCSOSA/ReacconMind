package com.reacconmind.reacconmind.model;

import java.io.Serializable;
import java.util.Objects;

public class ReactionPK implements Serializable {
    private int idUser;
    private int idPublication;
    private int idComment;

    // Constructor vacío (requerido por JPA)
    public ReactionPK() {}

    // Constructor con parámetros
    public ReactionPK(int idUser, int idPublication, int idComment) {
        this.idUser = idUser;
        this.idPublication = idPublication;
        this.idComment = idComment;
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

    // Sobrescribir equals y hashCode para que JPA los utilice
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReactionPK)) return false;
        ReactionPK that = (ReactionPK) o;
        return idUser == that.idUser &&
                idPublication == that.idPublication &&
                idComment == that.idComment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPublication, idComment);
    }
}
