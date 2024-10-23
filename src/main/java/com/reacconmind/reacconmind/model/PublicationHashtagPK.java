package com.reacconmind.reacconmind.model;

import java.io.Serializable;
import java.util.Objects;

public class PublicationHashtagPK implements Serializable {
    private int idPublication;
    private int idHashtag;

    public PublicationHashtagPK() {
    }

    public PublicationHashtagPK(int idPublication, int idHashtag) {
        this.idPublication = idPublication;
        this.idHashtag = idHashtag;
    }

    public int getIdPublication() {
        return idPublication;
    }


    public int getIdHashtag() {
        return idHashtag;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationHashtagPK that = (PublicationHashtagPK) o;
        return idPublication == that.idPublication && idHashtag == that.idHashtag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublication, idHashtag);
    }
}
