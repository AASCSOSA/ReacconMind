package com.reacconmind.reacconmind.model;


import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBot;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Enumerated(EnumType.STRING)
    @JsonProperty("theme")
    private ThemeBotType  theme = ThemeBotType.CombinatedMedia;

    /*@ManyToOne
    @JoinColumn(name = "idMultimedia", nullable = true)
    private Multimedia multimedia;

    @JsonManagedReference
    @OneToMany(mappedBy = "bot", cascade = CascadeType.ALL, fetch = FetchType.LAZY ,orphanRemoval = true)
    private List<Publication> publications;*/

    @Column(updatable = false, insertable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp shippingDate;

    public int getIdBot() {
        return idBot;
    }

    public void setIdBot(int idBot) {
        this.idBot = idBot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThemeBotType getTheme() {
        return theme;
    }

    public void setTheme(ThemeBotType theme) {
        this.theme = theme;
    }

    public Timestamp getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Timestamp shippingDate) {
        this.shippingDate = shippingDate;
    }
}
