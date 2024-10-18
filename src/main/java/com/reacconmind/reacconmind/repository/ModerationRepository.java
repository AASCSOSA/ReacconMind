package com.reacconmind.reacconmind.repository;

import com.reacconmind.reacconmind.model.Moderation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModerationRepository extends JpaRepository<Moderation, Integer> {
}