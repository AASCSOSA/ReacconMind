package com.reacconmind.reacconmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reacconmind.reacconmind.dto.FollowerDTO;
import com.reacconmind.reacconmind.model.Follower;
import com.reacconmind.reacconmind.service.FollowerService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name ="Follower")
@RequestMapping("ReacconMind/followers")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PostMapping("/follow/user")
    public ResponseEntity<FollowerDTO> followUser(@RequestParam int idFollower, @RequestParam int idFollowing) {
        FollowerDTO newFollower = followerService.followUser(idFollower, idFollowing);
        return ResponseEntity.ok(newFollower);
    }

    @PostMapping("/follow/bot")
    public ResponseEntity<FollowerDTO> followBot(@RequestParam int idFollower, @RequestParam int idFollowing) {
        FollowerDTO newFollower = followerService.followBot(idFollower, idFollowing);
        return ResponseEntity.ok(newFollower);
    }

    @DeleteMapping("/unfollow/user")
    public ResponseEntity<Void> unfollowUser(@RequestParam int idFollower, @RequestParam int idFollowing) {
        followerService.unfollowUser(idFollower, idFollowing);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/unfollow/bot")
    public ResponseEntity<Void> unfollowBot(@RequestParam int idFollower, @RequestParam int idFollowing) {
        followerService.unfollowBot(idFollower, idFollowing);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/isFollowing/user")
    public ResponseEntity<Boolean> isFollowing(@RequestParam int idFollower, @RequestParam int idFollowing) {
        boolean isFollowing = followerService.isFollowing(idFollower, idFollowing);
        return ResponseEntity.ok(isFollowing);
    }

    @GetMapping("/isFollowing/bot")
    public ResponseEntity<Boolean> isFollowingBot(@RequestParam int idFollower, @RequestParam int idFollowing) {
        boolean isFollowing = followerService.isFollowingBot(idFollower, idFollowing);
        return ResponseEntity.ok(isFollowing);
    }
}
