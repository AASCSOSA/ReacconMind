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
@Table(name = "Tendency")
public class Tendency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tendency")
    private Integer id_tendency;

    @Column(name = "name")
    private String name;

    @Column(name = "date_begin")
    private Date date_begin;

    @Column(name = "date_end")
    private Date date_end;

    // Getters y Setters
    public int getId_tendency() {
        return id_tendency;
    }

    public void setId_tendency(int id_tendency) {
        this.id_tendency = id_tendency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tendency)) return false;
        Tendency tendency = (Tendency) o;
        return id_tendency == tendency.id_tendency && Objects.equals(name, tendency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_tendency, name);
    }
}

