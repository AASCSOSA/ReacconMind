package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
