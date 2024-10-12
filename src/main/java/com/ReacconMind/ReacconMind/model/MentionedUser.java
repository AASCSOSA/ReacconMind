package com.ReacconMind.ReacconMind.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MentionedUser")
@IdClass(MentionedUser.class)
public class MentionedUser implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idPublication", nullable = false)
    private Publication publication;

    @Id
    @ManyToOne
    @JoinColumn(name = "idMentionedUser", nullable = false)
    private User mentionedUser;

    // Constructor por defecto
    public MentionedUser() {}

    // Constructor con parámetros
    public MentionedUser(Publication publication, User mentionedUser) {
        this.publication = publication;
        this.mentionedUser = mentionedUser;
    }

    // Getters y Setters
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public User getMentionedUser() {
        return mentionedUser;
    }

    public void setMentionedUser(User mentionedUser) {
        this.mentionedUser = mentionedUser;
    }

    // Métodos hashCode y equals para manejar la llave compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MentionedUser that = (MentionedUser) o;
        return Objects.equals(publication, that.publication) && Objects.equals(mentionedUser, that.mentionedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication, mentionedUser);
    }
}
