package edu.poniperro.nowait.core.comment.concurrence.application;

import edu.poniperro.nowait.core.comment.concurrence.ConcurrencePerDayResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;
import edu.poniperro.nowait.shared.domain.bus.query.Response;

@Service
public final class ConcurrencePerDayQueryHandler implements QueryHandler<ConcurrencePerDayQuery, ConcurrencePerDayResponse> {

        private final ConcurrenceCalculator calculator;

        public ConcurrencePerDayQueryHandler(ConcurrenceCalculator calculator) {
            this.calculator = calculator;
        }

        @Override
        public ConcurrencePerDayResponse handle(ConcurrencePerDayQuery query) {
            return calculator.calculateConcurrencePerDay(query.getPlaceId(), query.getDay());
        }
}
