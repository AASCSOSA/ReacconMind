package com.reacconmind.reacconmind.repository;

import com.reacconmind.reacconmind.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    // Método para encontrar una reacción específica por idUser, idPublication e idComment
    Reaction findByIdUserAndIdPublicationAndIdComment(Integer idUser, Integer idPublication, Integer idComment);

    // Método para eliminar una reacción específica por idUser, idPublication e idComment
    void deleteByIdUserAndIdPublicationAndIdComment(Integer idUser, Integer idPublication, Integer idComment);
}
