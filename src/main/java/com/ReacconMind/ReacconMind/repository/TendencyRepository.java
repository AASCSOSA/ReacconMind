package com.ReacconMind.ReacconMind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ReacconMind.ReacconMind.model.Tendency;

public interface TendencyRepository extends JpaRepository<Tendency, Integer> {
    Tendency findByNameTendency(String nameTendency);
}
