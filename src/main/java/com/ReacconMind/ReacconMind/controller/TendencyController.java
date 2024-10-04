package com.ReacconMind.ReacconMind.controller;

import com.ReacconMind.ReacconMind.model.Tendency;
import com.ReacconMind.ReacconMind.service.TendencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tendencies")
public class TendencyController {
    @Autowired
    private TendencyService tendencyService;

    @GetMapping
    public List<Tendency> getAllTendencies() {
        return tendencyService.getAllTendencies();
    }

    @PostMapping
    public Tendency createTendency(@RequestBody Tendency tendency) {
        return tendencyService.saveTendency(tendency);
    }
}

