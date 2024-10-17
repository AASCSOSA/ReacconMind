package com.reacconmind.reacconmind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reacconmind.reacconmind.model.Follower;
import com.reacconmind.reacconmind.service.FollowerService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Follower")
@RequestMapping("ReacconMind/followers")
public class FollowerController {

    @Autowired
    private FollowerService service;

    @PostMapping("/follow")
    public ResponseEntity<Follower> followUser(
        @RequestParam int followerId,
        @RequestParam int followingId
    ) {
        Follower follower = service.followUser(followerId, followingId);
        return ResponseEntity.ok(follower);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unfollowUser(
        @RequestParam int followerId,
        @RequestParam int followingId
    ) {
        service.unfollowUser(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<Follower>> getFollowers(
        @PathVariable int userId
    ) {
        List<Follower> followers = service.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<Follower>> getFollowing(
        @PathVariable int userId
    ) {
        List<Follower> following = service.getFollowing(userId);
        return ResponseEntity.ok(following);
    }

    @GetMapping("/isFollowing")
    public ResponseEntity<Boolean> isFollowing(
        @RequestParam int followerId,
        @RequestParam int followingId
    ) {
        boolean isFollowing = service.isFollowing(followerId, followingId);
        return ResponseEntity.ok(isFollowing);
    }
}
