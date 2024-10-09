package com.ReacconMind.ReacconMind.model;

import jakarta.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPublication;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, foreignKey = @ForeignKey(name = "fk_user_publication"))
    private User user; // Relación con la entidad User

    @Column(length = 250)
    private String content;

    @Column(name = "publicationDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String publicationDate;

    // Constructores
    public Publication() {
    }

    public Publication(User user, String content, String publicationDate) {
        this.user = user;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    // Getters y Setters
    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}
