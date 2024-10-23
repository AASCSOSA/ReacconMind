package com.reacconmind.reacconmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reacconmind.reacconmind.dto.BotDTO;
import com.reacconmind.reacconmind.dto.FollowerDTO;
import com.reacconmind.reacconmind.service.FollowerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("ReacconMind/followers")
@Tag(name = "Follower")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @Operation(summary = "A user will follow another user", description = "A user can follow another user as long as it is not about him.")
    @ApiResponse(responseCode = "200", description = "User successfully followed.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @PostMapping("/follow/user")
    public ResponseEntity<FollowerDTO> followUser(@RequestParam int idFollower, @RequestParam int idFollowing) {
        FollowerDTO newFollower = followerService.followUser(idFollower, idFollowing);
        return ResponseEntity.ok(newFollower);
    }

    @Operation(summary = "A user will follow a bot", description = "A user can follow a bot.")
    @ApiResponse(responseCode = "200", description = "Bot successfully followed.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @PostMapping("/follow/bot")
    public ResponseEntity<FollowerDTO> followBot(@RequestParam int idFollower, @RequestParam int idFollowing) {
        FollowerDTO newFollower = followerService.followBot(idFollower, idFollowing);
        return ResponseEntity.ok(newFollower);
    }

    @Operation(summary = "A user will unfollow an user", description = "A user can stop following a user.")
    @ApiResponse(responseCode = "200", description = "Unfollowed user.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @DeleteMapping("/unfollow/user")
    public ResponseEntity<Void> unfollowUser(@RequestParam int idFollower, @RequestParam int idFollowing) {
        followerService.unfollowUser(idFollower, idFollowing);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "A user will unfollow a bot", description = "A user can stop following a bot.")
    @ApiResponse(responseCode = "200", description = "Unfollowed bot.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @DeleteMapping("/unfollow/bot")
    public ResponseEntity<Void> unfollowBot(@RequestParam int idFollower, @RequestParam int idFollowing) {
        followerService.unfollowBot(idFollower, idFollowing);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get if a user follows another user", description = "Receive if it is true that a user follows another user.")
    @ApiResponse(responseCode = "200", description = "The user follows the other user.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @GetMapping("/isFollowing/user")
    public ResponseEntity<Boolean> isFollowing(@RequestParam int idFollower, @RequestParam int idFollowing) {
        boolean isFollowing = followerService.isFollowing(idFollower, idFollowing);
        return ResponseEntity.ok(isFollowing);
    }

    
    @Operation(summary = "Get if a user follows a bot", description = "Receive if it is true that a user follows a bot.")
    @ApiResponse(responseCode = "200", description = "The user follows the bot.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @GetMapping("/isFollowing/bot")
    public ResponseEntity<Boolean> isFollowingBot(@RequestParam int idFollower, @RequestParam int idFollowing) {
        boolean isFollowing = followerService.isFollowingBot(idFollower, idFollowing);
        return ResponseEntity.ok(isFollowing);
    }
}
