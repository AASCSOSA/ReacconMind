package com.reacconmind.reacconmind.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reacconmind.reacconmind.model.Follower;
import com.reacconmind.reacconmind.model.FollowerPK;

public interface FollowerRepository
    extends JpaRepository<Follower, FollowerPK> {
    @Query("SELECT f FROM Follower f WHERE f.userFollowing.id = :userId")
    List<Follower> findFollowersByUserId(int userId);

    @Query("SELECT f FROM Follower f WHERE f.userFollower.id = :userId")
    List<Follower> findFollowingByUserId(int userId);
}
