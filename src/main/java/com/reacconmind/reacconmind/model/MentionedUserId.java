package com.reacconmind.reacconmind.model;

import java.io.Serializable;
import java.util.Objects;

public class MentionedUserId implements Serializable {
    private int idPublication; // Cambiado para que coincida con MentionedUser
    private int idMentionedUser; // Cambiado para que coincida con MentionedUser

    // Constructor por defecto
    public MentionedUserId() {}

    // Constructor con parámetros
    public MentionedUserId(int idPublication, int idMentionedUser) {
        this.idPublication = idPublication;
        this.idMentionedUser = idMentionedUser;
    }

    // Getters
    public int getIdPublication() {
        return idPublication;
    }

    public int getIdMentionedUser() {
        return idMentionedUser;
    }

    // Setters
    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public void setIdMentionedUser(int idMentionedUser) {
        this.idMentionedUser = idMentionedUser;
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MentionedUserId that))
            return false;
        return idPublication == that.idPublication &&
                idMentionedUser == that.idMentionedUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublication, idMentionedUser);
    }
}
