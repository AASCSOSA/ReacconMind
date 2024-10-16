package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.Follower;
import com.ReacconMind.ReacconMind.model.FollowerPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowerRepository
    extends JpaRepository<Follower, FollowerPK> {
    @Query("SELECT f FROM Follower f WHERE f.userFollowing.id = :userId")
    List<Follower> findFollowersByUserId(int userId);

    @Query("SELECT f FROM Follower f WHERE f.userFollower.id = :userId")
    List<Follower> findFollowingByUserId(int userId);
}
