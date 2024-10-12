package com.ReacconMind.ReacconMind.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tendency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTendency;
    private String name;
    private Date dateBegin;
    private Date dateEnd;

    @JsonManagedReference
    @OneToMany(mappedBy = "tendency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<tendencyHashtag> tendencyHashtags;

    public int getIdTendency(){
        return idTendency;
    }

    public void setIdTendency(int idTendency){
        this.idTendency = idTendency;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Date getDateBegin(){
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin){
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd(){
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd){
        this.dateEnd = dateEnd;
    }

    public void setTendencyHashtags(List<tendencyHashtag> tendencyHashtags){
        this.tendencyHashtags = tendencyHashtags;
    }
}
