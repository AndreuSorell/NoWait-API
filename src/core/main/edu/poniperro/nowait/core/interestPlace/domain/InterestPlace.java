package edu.poniperro.nowait.core.interestPlace.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class InterestPlace {
    private String email;
    private String placeId;

    public InterestPlace() {
    }
    public InterestPlace(String email, String placeId) {
        this.email = email;
        this.placeId = placeId;
    }

    public static InterestPlace create(String email, String placeId) {
        return new InterestPlace(email, placeId);
    }

    public static InterestPlace fromPrimitives(HashMap<String, Serializable> data) {
        return new InterestPlace(
                (String) data.get("email"),
                (String) data.get("placeId")
        );
    }

    public HashMap<String, Object> toPrimitives() {
        return new HashMap<String, Object>() {
            {
                put("email", email);
                put("placeId", placeId);
            }
        };
    }

    public String getEmail() {
        return email;
    }

    public String getPlaceId() {
        return placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestPlace that = (InterestPlace) o;

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
