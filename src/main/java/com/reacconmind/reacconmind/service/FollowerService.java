package com.reacconmind.reacconmind.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.model.Follower;
import com.reacconmind.reacconmind.model.FollowerPK;
import com.reacconmind.reacconmind.model.User;
import com.reacconmind.reacconmind.repository.FollowerRepository;
import com.reacconmind.reacconmind.repository.UserRepository;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserRepository userRepository;

    // Seguir a un usuario
    public Follower followUser(int followerId, int followingId) {
        Optional<User> follower = userRepository.findById(followerId);
        Optional<User> following = userRepository.findById(followingId);

        if (follower.isPresent() &&
                following.isPresent() &&
                followerId != followingId) {
            Follower followerEntity = new Follower(
                    following.get(),
                    follower.get());
            return followerRepository.save(followerEntity);
        }
        throw new IllegalArgumentException(
                "Usuario no válido o los IDs son iguales");
    }

    public void unfollowUser(int followerId, int followingId) {
        FollowerPK id = new FollowerPK(followingId, followerId);
        followerRepository.deleteById(id);
    }

    public List<Follower> getFollowers(int userId) {
        return followerRepository.findFollowersByUserId(userId);
    }

    public List<Follower> getFollowing(int userId) {
        return followerRepository.findFollowingByUserId(userId);
    }

    public boolean isFollowing(int followerId, int followingId) {
        FollowerPK id = new FollowerPK(followingId, followerId);
        return followerRepository.existsById(id);
    }
}
