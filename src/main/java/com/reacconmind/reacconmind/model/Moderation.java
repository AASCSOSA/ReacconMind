package com.reacconmind.reacconmind.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class Moderation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moderation")
    private int idModeration;

    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @Column(name = "id_publication", nullable = false)
    private int idPublication;

    @Enumerated(EnumType.STRING)
    @Column(name = "moderation_type", nullable = false)
    private ModerationTypeEnum moderationType;

    @Column(name = "moderation_date", nullable = false)
    private LocalDateTime moderationDate;

    // Constructor
    public Moderation() {
        this.moderationDate = LocalDateTime.now();
    }
}
