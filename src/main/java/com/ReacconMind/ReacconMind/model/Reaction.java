package com.ReacconMind.ReacconMind.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReaction;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, foreignKey = @ForeignKey(name = "FK_User"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "idPublication", foreignKey = @ForeignKey(name = "FK_Publication"))
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "idComment", foreignKey = @ForeignKey(name = "FK_Comment"))
    private Comment comment;

    @Column(name = "liked", nullable = false)
    private Boolean liked;

    @Column(name = "reactionDate", nullable = false)
    private Timestamp reactionDate;

    // Getters and Setters

    public Integer getIdReaction() {
        return idReaction;
    }

    public void setIdReaction(Integer idReaction) {
        this.idReaction = idReaction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Timestamp getReactionDate() {
        return reactionDate;
    }

    public void setReactionDate(Timestamp reactionDate) {
        this.reactionDate = reactionDate;
    }
}
