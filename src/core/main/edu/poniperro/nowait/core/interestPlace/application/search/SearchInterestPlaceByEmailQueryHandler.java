package edu.poniperro.nowait.core.interestPlace.application.search;

import edu.poniperro.nowait.core.interestPlace.application.InterestPlacesResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchInterestPlaceByEmailQueryHandler implements QueryHandler<SearchInterestPlaceByEmailQuery, InterestPlacesResponse> {
    private final InterestPlaceSearcher searcher;

    public SearchInterestPlaceByEmailQueryHandler(InterestPlaceSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public InterestPlacesResponse handle(SearchInterestPlaceByEmailQuery query) {
        return searcher.searchByEmail(query.getEmail());
    }
}
