package edu.poniperro.nowait.apps.core.api.controller.comment.concurrence;

public final class RequestConcurrence {

    private String placeId;
    private int day;

    public RequestConcurrence() {
    }

    public RequestConcurrence(String placeId, int day) {
        this.placeId = placeId;
        this.day = day;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
