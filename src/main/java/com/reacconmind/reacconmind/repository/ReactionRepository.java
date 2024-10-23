package com.reacconmind.reacconmind.repository;

import com.reacconmind.reacconmind.model.Reaction;
import com.reacconmind.reacconmind.model.ReactionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, ReactionPK> {
    List<Reaction> findByIdUser(int idUser);
    List<Reaction> findByIdPublication(int idPublication);
}
