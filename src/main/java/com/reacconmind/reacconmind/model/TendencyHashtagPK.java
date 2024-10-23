package com.reacconmind.reacconmind.model;

import java.io.Serializable;
import java.util.Objects;

public class TendencyHashtagPK implements Serializable {
    private int idTendency;
    private int idHashtag;

    public TendencyHashtagPK() {}

    public TendencyHashtagPK(int idTendency, int idHashtag) {
        this.idTendency = idTendency;
        this.idHashtag = idHashtag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TendencyHashtagPK that = (TendencyHashtagPK) o;
        return idTendency == that.idTendency && idHashtag == that.idHashtag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTendency, idHashtag);
    }

    public int getIdTendency() {
        return idTendency;
    }

    public int getIdHashtag() {
        return idHashtag;
    }

    
}
