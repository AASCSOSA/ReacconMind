package com.reacconmind.reacconmind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reacconmind.reacconmind.model.Follower;
import com.reacconmind.reacconmind.model.FollowerPK;

public interface FollowerRepository extends JpaRepository<Follower, FollowerPK> {
    // List<Follower> findByFollowerPK_IdUserFollower(int idUserFollower);
    
}
  