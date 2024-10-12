package com.ReacconMind.ReacconMind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReacconMind.ReacconMind.repository.HashtagRepository;
import com.ReacconMind.ReacconMind.exception.UserAlreadyExistsException;
import com.ReacconMind.ReacconMind.model.Hashtag;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HashtagService {
    @Autowired
    private HashtagRepository hashtagRepository;

    public List<Hashtag> getAll(){
        return hashtagRepository.findAll();
    }

    public void save(Hashtag hashtag){
        hashtagRepository.save(hashtag);
    }

    public Hashtag getByIdHashtag(Integer idHashtag){
        return hashtagRepository.findById(idHashtag)
        .orElseThrow(() -> new UserAlreadyExistsException("Hashtag not found"));
    }

    public Hashtag findByName(String name){
        return hashtagRepository.findByName(name);
    }
}
