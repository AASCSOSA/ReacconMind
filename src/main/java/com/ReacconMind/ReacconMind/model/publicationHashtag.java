package com.ReacconMind.ReacconMind.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
@IdClass(publicationHashtagPK.class)
public class publicationHashtag {
    @ManyToOne
    @JoinColumn(name="idPublication")
    @Id
    @JsonBackReference
    private Publication publication;

    @ManyToOne
    @JoinColumn(name="idHashtag")
    @Id
    @JsonBackReference
    private Hashtag hashtag;
}
