package com.ReacconMind.ReacconMind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.ReacconMind.ReacconMind.model.Follower;
import com.ReacconMind.ReacconMind.model.FollowerPK;

public interface FollowerRepository extends JpaRepository<Follower, FollowerPK> {
    @Query("SELECT f FROM Follower f WHERE f.userFollowing.id = :userId")
    List<Follower> findFollowersByUserId(int userId);

    // Obtener la lista de usuarios que un usuario sigue
    @Query("SELECT f FROM Follower f WHERE f.userFollower.id = :userId")
    List<Follower> findFollowingByUserId(int userId);
}
