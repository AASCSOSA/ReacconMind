package com.ReacconMind.ReacconMind.controller;

import com.ReacconMind.ReacconMind.model.Reaction;
import com.ReacconMind.ReacconMind.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reactions")
@CrossOrigin(origins = "*")
public class ReactionController {

    @Autowired
    private ReactionService service;

    @GetMapping
    public List<Reaction> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Reaction> getById(@PathVariable Integer id) {
        Reaction reaction = service.getByIdReaction(id);
        return new ResponseEntity<>(reaction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Reaction reaction) {
        service.save(reaction);
        return new ResponseEntity<>("Reaction created", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Reaction reaction, @PathVariable Integer id) {
        service.update(reaction, id);
        return new ResponseEntity<>("Reaction updated", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>("Reaction deleted", HttpStatus.OK);
    }
}
