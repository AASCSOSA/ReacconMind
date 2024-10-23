package com.reacconmind.reacconmind.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(TendencyHashtagPK.class)
@Table(name = "TendencyHashtag")
public class TendencyHashtag {
    @Id
    @ManyToOne
    @JoinColumn(name = "idTendency", nullable = false)
    private Tendency tendency;

    @Id
    @ManyToOne
    @JoinColumn(name = "idHashtag", nullable = false)
    private Hashtag hashtag;

    public TendencyHashtag() {}

    public TendencyHashtag(Tendency tendency, Hashtag hashtag) {
        this.tendency = tendency;
        this.hashtag = hashtag;
    }

    public Tendency getTendency() {
        return tendency;
    }

    public void setTendency(Tendency tendency) {
        this.tendency = tendency;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }

    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }
}
