package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.MentionedUser;
import com.ReacconMind.ReacconMind.model.MentionedUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentionedUserRepository extends JpaRepository<MentionedUser, MentionedUserId> {

}
