package com.ReacconMind.ReacconMind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ReacconMind.ReacconMind.model.Hashtag;

public interface HashtagRepository extends JpaRepository <Hashtag, Integer> {
    Hashtag findByName(String name);
}
