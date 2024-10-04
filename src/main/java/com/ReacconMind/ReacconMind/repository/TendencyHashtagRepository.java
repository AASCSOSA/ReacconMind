package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.TendencyHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TendencyHashtagRepository extends JpaRepository<TendencyHashtag, Integer> {
    // Los métodos personalizados irían aquí si fueran necesarios
}