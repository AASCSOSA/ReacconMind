package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    // Aquí puedes agregar métodos personalizados si es necesario,
    // pero JpaRepository ya proporciona los métodos CRUD básicos.

}
