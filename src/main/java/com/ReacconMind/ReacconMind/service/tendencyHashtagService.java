package com.ReacconMind.ReacconMind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReacconMind.ReacconMind.model.tendencyHashtag;
import com.ReacconMind.ReacconMind.repository.tendencyHashtagRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class tendencyHashtagService {
    @Autowired
    private tendencyHashtagRepository repository;

    public List<tendencyHashtag> getAll(){
        return repository.findAll();
    }

    public void save(tendencyHashtag tendencyHashtags){
        repository.save(tendencyHashtags);
    }
}
