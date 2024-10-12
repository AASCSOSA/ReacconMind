package com.ReacconMind.ReacconMind.model;

import java.io.Serializable;
import java.util.Objects;

public class MentionedUserId implements Serializable {

    private int publication;
    private int mentionedUser;

    // Constructor por defecto
    public MentionedUserId() {}

    // Constructor con parámetros
    public MentionedUserId(int publication, int mentionedUser) {
        this.publication = publication;
        this.mentionedUser = mentionedUser;
    }

    // Getters y Setters
    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public int getMentionedUser() {
        return mentionedUser;
    }

    public void setMentionedUser(int mentionedUser) {
        this.mentionedUser = mentionedUser;
    }

    // Métodos hashCode y equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MentionedUserId that = (MentionedUserId) o;
        return publication == that.publication && mentionedUser == that.mentionedUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication, mentionedUser);
    }
}
