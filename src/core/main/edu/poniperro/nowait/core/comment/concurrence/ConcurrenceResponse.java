package edu.poniperro.nowait.core.comment.concurrence;

import edu.poniperro.nowait.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ConcurrenceResponse implements Response {

    private int now;
    private List<Integer> today;
    private List<Integer> week;

    public ConcurrenceResponse(int now, List<Integer> today, List<Integer> week) {
        this.now = now;
        this.today = today;
        this.week = week;
    }


    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("now", now);
            put("today", (Serializable) today);
            put("week", (Serializable) week);
        }};
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public List<Integer> getToday() {
        return today;
    }

    public void setToday(List<Integer> today) {
        this.today = today;
    }

    public List<Integer> getWeek() {
        return week;
    }

    public void setWeek(List<Integer> week) {
        this.week = week;
    }
}
