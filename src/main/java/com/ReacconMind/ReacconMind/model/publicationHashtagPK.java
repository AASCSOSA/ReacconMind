package com.ReacconMind.ReacconMind.model;

import java.io.Serializable;
import java.util.Objects;

public class publicationHashtagPK implements Serializable {
    private Publication publication; //manufacturing
    private Hashtag idHashtag; //fabric

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof publicationHashtagPK publicationHashtagPK))
            return false;
        return publication.getId() == publicationHashtagPK.publication.getId()
                && Objects.equals(idHashtag, publicationHashtagPK.idHashtag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication, idHashtag);
    }

    public Publication getPublication() {
        return publication;
    }

    public Hashtag getIdFabric() {
        return idHashtag;
    }
}
