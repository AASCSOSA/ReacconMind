package com.reacconmind.reacconmind.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.time.LocalDateTime;


@Entity
public class Follower {

    @EmbeddedId
    private FollowerPK id;

    @ManyToOne
    @MapsId("idUserFollowing")
    @JoinColumn(name = "idUserFollowing")
    private User userFollowing;

    @ManyToOne
    @MapsId("idUserFollower")
    @JoinColumn(name = "idUserFollower")
    private User userFollower;

    @Column(name = "dateFollowing", nullable = false, updatable = false)
    private LocalDateTime dateFollowing;

    // Constructor por defecto
    public Follower() {}

    // Constructor con parámetros
    public Follower(User userFollowing, User userFollower) {
        this.userFollowing = userFollowing;
        this.userFollower = userFollower;
        this.id = new FollowerPK(userFollowing.getIdUser(), userFollower.getIdUser());
        this.dateFollowing = LocalDateTime.now();
    }

    // Getters y setters
    public FollowerPK getId() {
        return id;
    }

    public void setId(FollowerPK id) {
        this.id = id;
    }

    public User getUserFollowing() {
        return userFollowing;
    }

    public void setUserFollowing(User userFollowing) {
        this.userFollowing = userFollowing;
    }

    public User getUserFollower() {
        return userFollower;
    }

    public void setUserFollower(User userFollower) {
        this.userFollower = userFollower;
    }

    public LocalDateTime getDateFollowing() {
        return dateFollowing;
    }

    public void setDateFollowing(LocalDateTime dateFollowing) {
        this.dateFollowing = dateFollowing;
    }
}

   
    

