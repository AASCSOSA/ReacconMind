package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
