package edu.poniperro.nowait.core.comment.concurrence;

import edu.poniperro.nowait.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ConcurrencePerDayResponse implements Response {
    private List<Integer> concurrencePerDay;

    public ConcurrencePerDayResponse(List<Integer> concurrencePerDay) {
        this.concurrencePerDay = concurrencePerDay;
    }

    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("concurrencePerDay", (Serializable) concurrencePerDay);
        }};
    }

    public List<Integer> getConcurrencePerDay() {
        return concurrencePerDay;
    }

    public void setConcurrencePerDay(List<Integer> concurrencePerDay) {
        this.concurrencePerDay = concurrencePerDay;
    }
}
