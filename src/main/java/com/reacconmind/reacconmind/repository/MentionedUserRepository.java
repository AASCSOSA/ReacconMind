package com.reacconmind.reacconmind.repository;

import com.reacconmind.reacconmind.model.MentionedUser;
import com.reacconmind.reacconmind.model.MentionedUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentionedUserRepository extends JpaRepository<MentionedUser, MentionedUserId> {

}
