package edu.poniperro.nowait.core.profile.user.application.search;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public class SearchUserEmailQueryHandler implements QueryHandler<SearchUserEmailQuery, SearchUserEmailResponse> {

        private final UserEmailSearcher searcher;

        public SearchUserEmailQueryHandler(UserEmailSearcher searcher) {
            this.searcher = searcher;
        }

        @Override
        public SearchUserEmailResponse handle(SearchUserEmailQuery query) {
            return searcher.search(query.getEmail());
        }
}
