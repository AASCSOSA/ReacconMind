package com.ReacconMind.ReacconMind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReacconMind.ReacconMind.model.publicationHashtag;
import com.ReacconMind.ReacconMind.repository.publicationHashtagRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class publicationHashtagService {
    @Autowired
    private publicationHashtagRepository repository;

    public List<publicationHashtag> getAll(){
        return repository.findAll();
    }

    public void save(publicationHashtag publicationHashtags){
        repository.save(publicationHashtags);
    }
}
