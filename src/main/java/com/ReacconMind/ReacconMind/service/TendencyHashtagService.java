package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.TendencyHashtag;
import com.ReacconMind.ReacconMind.repository.TendencyHashtagRepository;
import com.ReacconMind.ReacconMind.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.sql.Date;

@Service
@Transactional
public class TendencyHashtagService {

    private final TendencyHashtagRepository tendencyHashtagRepository;

    public TendencyHashtagService(TendencyHashtagRepository tendencyHashtagRepository) {
        this.tendencyHashtagRepository = tendencyHashtagRepository;
    }

    public List<TendencyHashtag> getAllTendencyHashtags() {
        return tendencyHashtagRepository.findAll();
    }

    public TendencyHashtag getTendencyHashtagById(Integer id) {
        return tendencyHashtagRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("TendencyHashtag not found with id: " + id));
    }

    public TendencyHashtag createTendencyHashtag(TendencyHashtag tendencyHashtag) {
        tendencyHashtag.setCreationDate(new Date(System.currentTimeMillis()));
        return tendencyHashtagRepository.save(tendencyHashtag);
    }

    public TendencyHashtag updateTendencyHashtag(Integer id, TendencyHashtag tendencyHashtagDetails) {
        TendencyHashtag tendencyHashtag = getTendencyHashtagById(id);
        tendencyHashtag.setTendency(tendencyHashtagDetails.getTendency());
        tendencyHashtag.setHashtag(tendencyHashtagDetails.getHashtag());
        return tendencyHashtagRepository.save(tendencyHashtag);
    }

    public void deleteTendencyHashtag(Integer id) {
        TendencyHashtag tendencyHashtag = getTendencyHashtagById(id);
        tendencyHashtagRepository.delete(tendencyHashtag);
    }
}