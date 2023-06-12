package edu.poniperro.nowait.core.comment.concurrence.application;

import edu.poniperro.nowait.shared.domain.MotherCreator;

public class ConcurrenceQueryMother {
    public static ConcurrenceQuery create(String placeId) {
        return new ConcurrenceQuery(placeId);
    }

    public static ConcurrenceQuery random() {
        return create(MotherCreator.random().name().name());
    }
}
