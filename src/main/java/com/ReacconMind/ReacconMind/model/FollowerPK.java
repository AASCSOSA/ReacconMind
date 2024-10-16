package com.ReacconMind.ReacconMind.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowerPK implements Serializable {
    
    private int idUserFollowing;
    private int idUserFollower;

    
    public FollowerPK() {}

    public FollowerPK(int idUserFollowing, int idUserFollower) {
        this.idUserFollowing = idUserFollowing;
        this.idUserFollower = idUserFollower;
    }

    // Getters y setters
    public int getIdUserFollowing() {
        return idUserFollowing;
    }

    public void setIdUserFollowing(int idUserFollowing) {
        this.idUserFollowing = idUserFollowing;
    }

    public int getIdUserFollower() {
        return idUserFollower;
    }

    public void setIdUserFollower(int idUserFollower) {
        this.idUserFollower = idUserFollower;
    }

    // Sobrescribir equals() y hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowerPK that = (FollowerPK) o;
        return idUserFollowing == that.idUserFollowing && idUserFollower == that.idUserFollower;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserFollowing, idUserFollower);
    }
}

