package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.Reaction;
import com.ReacconMind.ReacconMind.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReactionService {

    @Autowired
    private ReactionRepository repo;

    public List<Reaction> getAll() {
        return repo.findAll();
    }

    public void save(Reaction reaction) {
        repo.save(reaction);
    }

    public Reaction getByIdReaction(Integer idReaction) {
        return repo.findById(idReaction).orElse(null);
    }

    public void update(Reaction reaction, Integer id) {
        reaction.setIdReaction(id);
        repo.save(reaction);
    }

    public void delete(Integer idReaction) {
        repo.deleteById(idReaction);
    }
}
