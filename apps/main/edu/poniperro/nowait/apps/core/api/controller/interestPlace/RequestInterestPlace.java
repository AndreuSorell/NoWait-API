package edu.poniperro.nowait.apps.core.api.controller.interestPlace;

public class RequestInterestPlace {
    private String token;
    private String placeId;

    public RequestInterestPlace() {
    }

    public RequestInterestPlace(String token, String placeId) {
        this.token = token;
        this.placeId = placeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
