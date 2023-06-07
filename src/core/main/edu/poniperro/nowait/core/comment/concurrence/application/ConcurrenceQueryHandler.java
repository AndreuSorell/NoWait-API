package edu.poniperro.nowait.core.comment.concurrence.application;

import edu.poniperro.nowait.core.comment.concurrence.ConcurrenceResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public class ConcurrenceQueryHandler implements QueryHandler<ConcurrenceQuery, ConcurrenceResponse> {

    private final ConcurrenceCalculator calculator;

    public ConcurrenceQueryHandler(ConcurrenceCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public ConcurrenceResponse handle(ConcurrenceQuery query) {
        return calculator.calculate(query.getPlaceId());
    }
}
