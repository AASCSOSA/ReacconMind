package com.reacconmind.reacconmind.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@IdClass(PublicationHashtagPK.class)
@Table(name = "PublicationHashtag")
public class PublicationHashtag {
    @Id
    @ManyToOne
    @JoinColumn(name = "idPublication", nullable = false)
    private Publication publication;

    @Id
    @ManyToOne
    @JoinColumn(name = "idHashtag", nullable = false)
    private Hashtag hashtag;

    public PublicationHashtag() {}

    public PublicationHashtag(Publication publication, Hashtag hashtag) {
        this.publication = publication;
        this.hashtag = hashtag;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }

    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }
}
