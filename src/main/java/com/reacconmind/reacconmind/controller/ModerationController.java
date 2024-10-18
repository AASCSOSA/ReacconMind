package com.reacconmind.reacconmind.controller;

import com.reacconmind.reacconmind.model.Moderation;
import com.reacconmind.reacconmind.service.ModerationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/moderations")
public class ModerationController {
    @Autowired
    private ModerationService moderationService;

    @GetMapping
    public List<Moderation> getAllModerations() {
        return moderationService.getAllModerations();
    }

    @PostMapping
    public Moderation createModeration(@RequestBody Moderation moderation) {
        return moderationService.saveModeration(moderation);
    }
}