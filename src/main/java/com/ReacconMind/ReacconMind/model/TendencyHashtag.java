package com.ReacconMind.ReacconMind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "TENDENCY_HASHTAG")
public class TendencyHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tendencyHashtag")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tendency", nullable = false)
    private Tendency tendency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hashtag", nullable = false)
    private Hashtag hashtag;

    @Column(name = "creation_date")
    private Date creationDate;

    // Constructores
    public TendencyHashtag() {
    }

    public TendencyHashtag(Tendency tendency, Hashtag hashtag, Date creationDate) {
        this.tendency = tendency;
        this.hashtag = hashtag;
        this.creationDate = creationDate;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TendencyHashtag that = (TendencyHashtag) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "TendencyHashtag{" +
                "id=" + id +
                ", tendency=" + tendency +
                ", hashtag=" + hashtag +
                ", creationDate=" + creationDate +
                '}';
    }
}