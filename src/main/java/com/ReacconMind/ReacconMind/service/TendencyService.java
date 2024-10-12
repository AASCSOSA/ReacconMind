package com.ReacconMind.ReacconMind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReacconMind.ReacconMind.exception.UserAlreadyExistsException;
import com.ReacconMind.ReacconMind.model.Tendency;
import com.ReacconMind.ReacconMind.repository.TendencyRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TendencyService {
    @Autowired
    private TendencyRepository tendencyRepository;

    public List<Tendency> getAll(){
        return tendencyRepository.findAll();
    }

    public boolean tendencyExists(Integer idTendency, String tendencyName){
        return tendencyRepository.existsById(idTendency)||
            tendencyRepository.findByNameTendency(tendencyName)!= null;
    }

    public void save(Tendency tendency){
        tendencyRepository.save(tendency);
    }

    public Tendency getByIdTendency(Integer idTendency){
        return tendencyRepository.findById(idTendency)
            .orElseThrow(() -> new UserAlreadyExistsException("Tendency not found with id: "+ idTendency));
    }

    public Tendency findByName(String tendencyName){
        return tendencyRepository.findByNameTendency(tendencyName);
    }
}
