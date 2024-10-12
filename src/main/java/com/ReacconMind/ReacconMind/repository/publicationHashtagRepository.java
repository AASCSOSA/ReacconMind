package com.ReacconMind.ReacconMind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ReacconMind.ReacconMind.model.publicationHashtag;
import com.ReacconMind.ReacconMind.model.publicationHashtagPK;

public interface publicationHashtagRepository extends JpaRepository<publicationHashtag,publicationHashtagPK>{

}
