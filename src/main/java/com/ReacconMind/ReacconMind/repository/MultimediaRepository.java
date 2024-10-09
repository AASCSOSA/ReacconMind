package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {
}
