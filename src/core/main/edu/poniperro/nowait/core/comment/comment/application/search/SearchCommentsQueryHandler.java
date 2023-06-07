package edu.poniperro.nowait.core.comment.comment.application.search;

import edu.poniperro.nowait.core.comment.comment.application.CommentsResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public class SearchCommentsQueryHandler implements QueryHandler<SearchCommentsByPlaceIdQuery, CommentsResponse> {

    private final CommentsSearcher searcher;

    public SearchCommentsQueryHandler(CommentsSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public CommentsResponse handle(SearchCommentsByPlaceIdQuery query) {
        return searcher.search(query.getPlaceId());
    }
}

