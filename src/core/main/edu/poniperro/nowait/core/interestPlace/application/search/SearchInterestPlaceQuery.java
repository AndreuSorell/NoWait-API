package edu.poniperro.nowait.core.interestPlace.application.search;

import edu.poniperro.nowait.shared.domain.bus.query.Query;

import java.util.Objects;

public final class SearchInterestPlaceQuery implements Query {
    private String email;
    private String placeId;

    public SearchInterestPlaceQuery(String email, String placeId) {
        this.email = email;
        this.placeId = placeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        SearchInterestPlaceQuery that = (SearchInterestPlaceQuery) o;

        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(placeId, that.placeId);
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        return result;
    }
}
