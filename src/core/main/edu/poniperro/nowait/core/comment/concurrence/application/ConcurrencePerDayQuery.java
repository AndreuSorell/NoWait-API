package edu.poniperro.nowait.core.comment.concurrence.application;

import edu.poniperro.nowait.shared.domain.bus.query.Query;

import java.util.Objects;

public class ConcurrencePerDayQuery implements Query {

    private String placeId;
    private int day;

    public ConcurrencePerDayQuery(String placeId, int day) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConcurrencePerDayQuery that = (ConcurrencePerDayQuery) o;

        if (day != that.day) return false;
        return Objects.equals(placeId, that.placeId);
    }

    @Override
    public int hashCode() {
        int result = placeId != null ? placeId.hashCode() : 0;
        result = 31 * result + day;
        return result;
    }
}
