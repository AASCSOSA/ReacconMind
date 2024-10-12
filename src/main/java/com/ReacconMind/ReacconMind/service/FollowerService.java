package com.ReacconMind.ReacconMind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReacconMind.ReacconMind.model.Follower;
import com.ReacconMind.ReacconMind.model.FollowerPK;
import com.ReacconMind.ReacconMind.model.User;
import com.ReacconMind.ReacconMind.repository.FollowerRepository;
import com.ReacconMind.ReacconMind.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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

        if (follower.isPresent() && following.isPresent() && followerId != followingId) {
            Follower followerEntity = new Follower(following.get(), follower.get());
            return followerRepository.save(followerEntity);
        }
        throw new IllegalArgumentException("Usuario no válido o los IDs son iguales");
    }

    // Dejar de seguir a un usuario
    public void unfollowUser(int followerId, int followingId) {
        FollowerPK id = new FollowerPK(followingId, followerId);
        followerRepository.deleteById(id);
    }

    // Obtener la lista de seguidores de un usuario
    public List<Follower> getFollowers(int userId) {
        return followerRepository.findFollowersByUserId(userId);
    }

    // Obtener la lista de usuarios que un usuario sigue
    public List<Follower> getFollowing(int userId) {
        return followerRepository.findFollowingByUserId(userId);
    }


    // Verificar si un usuario sigue a otro
    public boolean isFollowing(int followerId, int followingId) {
        FollowerPK id = new FollowerPK(followingId, followerId);
        return followerRepository.existsById(id);
    }
}


