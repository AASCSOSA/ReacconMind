package com.ReacconMind.ReacconMind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hashtag")
    private int id_hashtag;

    @Column(name = "name")
    private String name;

    // Getters y Setters
    public int getId_hashtag() {
        return id_hashtag;
    }

    public void setId_hashtag(int id_hashtag) {
        this.id_hashtag = id_hashtag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

