package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.Tendency;
import com.ReacconMind.ReacconMind.repository.TendencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TendencyService {
    @Autowired
    private TendencyRepository tendencyRepository;

    public List<Tendency> getAllTendencies() {
        return tendencyRepository.findAll();
    }

    public Tendency saveTendency(Tendency tendency) {
        return tendencyRepository.save(tendency);
    }
}

