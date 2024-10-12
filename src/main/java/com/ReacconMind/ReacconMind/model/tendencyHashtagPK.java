package com.ReacconMind.ReacconMind.model;

import java.io.Serializable;
import java.util.Objects;

public class tendencyHashtagPK implements Serializable{
    private Tendency tendency; //manufacturing
    private Hashtag hashtag; //fabric
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof tendencyHashtagPK tendencyHashtagPK))
            return false;
        return tendency.getIdTendency() == tendencyHashtagPK.tendency.getIdTendency() && Objects.equals(hashtag, tendencyHashtagPK.hashtag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tendency, hashtag);
    }

    public Tendency getTendency() {
        return tendency;
    }

    public Hashtag getIdHashtag() {
        return hashtag;
    }    
}
