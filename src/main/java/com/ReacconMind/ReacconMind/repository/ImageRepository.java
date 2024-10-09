package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
