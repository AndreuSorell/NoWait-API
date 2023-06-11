package edu.poniperro.nowait.core.interestPlace.application.search;

import edu.poniperro.nowait.core.interestPlace.application.InterestPlaceResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchInterestPlaceQueryHandler implements QueryHandler<SearchInterestPlaceQuery, InterestPlaceResponse> {
    private final InterestPlaceSearcher searcher;

    public SearchInterestPlaceQueryHandler(InterestPlaceSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public InterestPlaceResponse handle(SearchInterestPlaceQuery query) {
        return searcher.search(query.getEmail(), query.getPlaceId());
    }
}

