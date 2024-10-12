package com.ReacconMind.ReacconMind.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(tendencyHashtagPK.class)
public class tendencyHashtag {
    @ManyToOne
    @JoinColumn(name="idTendency")
    @Id
    @JsonBackReference
    private Tendency tendency;

    @ManyToOne
    @JoinColumn(name = "idHashtag")
    @Id
    @JsonBackReference
    private Hashtag hashtag;
}
