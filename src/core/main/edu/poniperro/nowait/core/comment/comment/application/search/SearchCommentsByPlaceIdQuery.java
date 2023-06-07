package edu.poniperro.nowait.core.comment.comment.application.search;

import edu.poniperro.nowait.shared.domain.bus.query.Query;

import java.util.Objects;

public class SearchCommentsByPlaceIdQuery implements Query {
    private String placeId;

    public SearchCommentsByPlaceIdQuery(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchCommentsByPlaceIdQuery that = (SearchCommentsByPlaceIdQuery) o;

        return Objects.equals(placeId, that.placeId);
    }

    @Override
    public int hashCode() {
        return placeId != null ? placeId.hashCode() : 0;
    }
}
