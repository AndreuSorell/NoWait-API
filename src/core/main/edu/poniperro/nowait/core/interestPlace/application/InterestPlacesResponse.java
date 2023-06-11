package edu.poniperro.nowait.core.interestPlace.application;

import edu.poniperro.nowait.shared.domain.bus.query.Response;

import java.util.List;
import java.util.Objects;

public final class InterestPlacesResponse implements Response {
    private List<InterestPlaceResponse> interestPlaces;

    public InterestPlacesResponse(List<InterestPlaceResponse> interestPlaces) {
        this.interestPlaces = interestPlaces;
    }

    public List<InterestPlaceResponse> getInterestPlaces() {
        return interestPlaces;
    }

    public void setInterestPlaces(List<InterestPlaceResponse> interestPlaces) {
        this.interestPlaces = interestPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestPlacesResponse that = (InterestPlacesResponse) o;

        return Objects.equals(interestPlaces, that.interestPlaces);
    }

    @Override
    public int hashCode() {
        return interestPlaces != null ? interestPlaces.hashCode() : 0;
    }
}
