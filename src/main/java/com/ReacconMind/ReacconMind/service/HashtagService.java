package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.Hashtag;
import com.ReacconMind.ReacconMind.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService {
    @Autowired
    private HashtagRepository hashtagRepository;

    public List<Hashtag> getAllHashtags() {
        return hashtagRepository.findAll();
    }

    public Hashtag saveHashtag(Hashtag hashtag) {
        return hashtagRepository.save(hashtag);
    }
}

