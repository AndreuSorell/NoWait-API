package edu.poniperro.nowait.core.interestPlace.application;

import edu.poniperro.nowait.core.interestPlace.domain.InterestPlace;
import edu.poniperro.nowait.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class InterestPlaceResponse implements Response {
    private String placeId;

    public InterestPlaceResponse(String placeId) {
        this.placeId = placeId;
    }

    public static InterestPlaceResponse fromAggregate(InterestPlace interestPlace) {
        return new InterestPlaceResponse(interestPlace.getPlaceId());
    }

    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("placeId", placeId);
        }};
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

        InterestPlaceResponse that = (InterestPlaceResponse) o;

        return Objects.equals(placeId, that.placeId);
    }

    @Override
    public int hashCode() {
        return placeId != null ? placeId.hashCode() : 0;
    }

}
