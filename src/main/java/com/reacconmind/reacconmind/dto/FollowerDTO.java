package com.reacconmind.reacconmind.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FollowerDTO {
    private int idUserFollower;
    private int idFollowing;
    private String followingType;
    private Timestamp dateFollowing;

    public FollowerDTO() {}

    public FollowerDTO(int idUserFollower, int idFollowing, String followingType, Timestamp dateFollowing) {
        this.idUserFollower = idUserFollower;
        this.idFollowing = idFollowing;
        this.followingType = followingType;
        this.dateFollowing = dateFollowing;
    }

    public int getIdUserFollower() {
        return idUserFollower;
    }

    public void setIdUserFollower(int idUserFollower) {
        this.idUserFollower = idUserFollower;
    }

    public int getIdFollowing() {
        return idFollowing;
    }

    public void setIdFollowing(int idFollowing) {
        this.idFollowing = idFollowing;
    }

    public String getFollowingType() {
        return followingType;
    }

    public void setFollowingType(String followingType) {
        this.followingType = followingType;
    }

    public Timestamp getDateFollowing() {
        return dateFollowing;
    }

    public void setDateFollowing(Timestamp dateFollowing) {
        this.dateFollowing = dateFollowing;
    }
}
