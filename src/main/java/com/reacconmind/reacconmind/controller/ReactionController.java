package com.reacconmind.reacconmind.controller;

import com.reacconmind.reacconmind.model.Reaction;
import com.reacconmind.reacconmind.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping
    public ResponseEntity<Reaction> createReaction(@RequestBody Reaction reaction) {
        Reaction createdReaction = reactionService.createReaction(reaction);
        return ResponseEntity.ok(createdReaction);
    }

    @GetMapping
    public ResponseEntity<List<Reaction>> getAllReactions() {
        List<Reaction> reactions = reactionService.getAllReactions(); // Asegúrate de que este método esté definido en ReactionService
        return ResponseEntity.ok(reactions);
    }

    @GetMapping("/{idUser}/{idPublication}/{idComment}")
    public ResponseEntity<Reaction> getReaction(
            @PathVariable Integer idUser,
            @PathVariable Integer idPublication,
            @PathVariable Integer idComment) {
        Reaction reaction = reactionService.getReaction(idUser, idPublication, idComment);
        return reaction != null ? ResponseEntity.ok(reaction) : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Reaction> updateReaction(@RequestBody Reaction reaction) {
        Reaction updatedReaction = reactionService.updateReaction(reaction);
        return ResponseEntity.ok(updatedReaction);
    }

    @DeleteMapping("/{idUser}/{idPublication}/{idComment}")
    public ResponseEntity<Void> deleteReaction(
            @PathVariable Integer idUser,
            @PathVariable Integer idPublication,
            @PathVariable Integer idComment) {
        reactionService.deleteReaction(idUser, idPublication, idComment);
        return ResponseEntity.noContent().build();
    }
}
