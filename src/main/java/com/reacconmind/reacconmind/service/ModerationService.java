package com.reacconmind.reacconmind.service;

import com.reacconmind.reacconmind.model.Moderation;
import com.reacconmind.reacconmind.repository.ModerationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModerationService {
    private final ModerationRepository moderationRepository;

    public ModerationService(ModerationRepository moderationRepository) {
        this.moderationRepository = moderationRepository;
    }

    public List<Moderation> getAllModerations() {
        return moderationRepository.findAll();
    }

    public Moderation saveModeration(Moderation moderation) {
        return moderationRepository.save(moderation);
    }
}