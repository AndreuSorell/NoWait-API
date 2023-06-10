package edu.poniperro.nowait.core.comment.comment.application.search;

import edu.poniperro.nowait.core.comment.comment.application.CommentsResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchCommentsByEmailQueryHandler implements QueryHandler<SearchCommentsByEmailQuery, CommentsResponse> {

        private final CommentsSearcher searcher;

        public SearchCommentsByEmailQueryHandler(CommentsSearcher searcher) {
            this.searcher = searcher;
        }

        @Override
        public CommentsResponse handle(SearchCommentsByEmailQuery query) {
            return searcher.searchByEmail(query.getEmail());
        }
}
