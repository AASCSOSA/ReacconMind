package com.ReacconMind.ReacconMind.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHashtag;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "hashtag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<tendencyHashtag> tendencyHashtags;

    @JsonManagedReference
    @OneToMany(mappedBy = "hashtag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<publicationHashtag> publicationHashtags;

    public int getIdHashtag(){
        return idHashtag;
    }
    public void setIdHashtag(int idHashtag){
        this.idHashtag = idHashtag;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}
