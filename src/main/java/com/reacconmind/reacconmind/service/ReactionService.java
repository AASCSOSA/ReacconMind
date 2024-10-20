package com.reacconmind.reacconmind.service;

import com.reacconmind.reacconmind.model.Reaction;
import com.reacconmind.reacconmind.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    public Reaction createReaction(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    public Reaction getReaction(Integer idUser, Integer idPublication, Integer idComment) {
        return reactionRepository.findByIdUserAndIdPublicationAndIdComment(idUser, idPublication, idComment);
    }

    public Reaction updateReaction(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    public void deleteReaction(Integer idUser, Integer idPublication, Integer idComment) {
        reactionRepository.deleteByIdUserAndIdPublicationAndIdComment(idUser, idPublication, idComment);
    }
}
