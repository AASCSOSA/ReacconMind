package com.ReacconMind.ReacconMind.controller;

import com.ReacconMind.ReacconMind.model.Hashtag;
import com.ReacconMind.ReacconMind.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hashtags")
public class HashtagController {
    @Autowired
    private HashtagService hashtagService;

    @GetMapping
    public List<Hashtag> getAllHashtags() {
        return hashtagService.getAllHashtags();
    }

    @PostMapping
    public Hashtag createHashtag(@RequestBody Hashtag hashtag) {
        return hashtagService.saveHashtag(hashtag);
    }
}

