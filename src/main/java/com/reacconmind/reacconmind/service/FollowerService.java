package com.reacconmind.reacconmind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.dto.FollowerDTO;
import com.reacconmind.reacconmind.model.Follower;
import com.reacconmind.reacconmind.model.FollowerPK;
import com.reacconmind.reacconmind.model.Follower.FollowingType;
import com.reacconmind.reacconmind.repository.FollowerRepository;

import java.sql.Timestamp;

@Service
public class FollowerService {
    @Autowired
    private FollowerRepository followerRepository;

    public FollowerDTO followUser(int idFollower, int idFollowing) {
        if (idFollower != idFollowing) {
            FollowerPK followerPK = new FollowerPK(idFollower, idFollowing, FollowingType.User);
            Follower follower = new Follower(followerPK);
            followerRepository.save(follower);
            return new FollowerDTO(idFollower, idFollowing, FollowingType.User.name(), new Timestamp(System.currentTimeMillis()));
        }
        throw new IllegalArgumentException("Invalid user or IDs are the same");
    }

    public FollowerDTO followBot(int idFollower, int idFollowing) {
        FollowerPK followerPK = new FollowerPK(idFollower, idFollowing, FollowingType.Bot);
        Follower follower = new Follower(followerPK);
        followerRepository.save(follower);
        return new FollowerDTO(idFollower, idFollowing, FollowingType.Bot.name(), new Timestamp(System.currentTimeMillis()));
    } 

    public void unfollowUser(int idFollower, int idFollowing) {
        FollowerPK id = new FollowerPK(idFollower, idFollowing, FollowingType.User);
        followerRepository.deleteById(id);
    }

    public void unfollowBot(int idFollower, int idBot) {
        FollowerPK id = new FollowerPK(idFollower, idBot, FollowingType.Bot);
        followerRepository.deleteById(id);
    }

    public boolean isFollowing(int idFollower, int idFollowing) {
        FollowerPK id = new FollowerPK(idFollower, idFollowing, FollowingType.User);
        return followerRepository.existsById(id);
    }

    public boolean isFollowingBot(int idFollower, int idBot) {
        FollowerPK id = new FollowerPK(idFollower, idBot, FollowingType.Bot);
        return followerRepository.existsById(id);
    }

}