package edu.poniperro.nowait.core.comment.judge.application.search;

import edu.poniperro.nowait.core.comment.judge.application.JudgeResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchJudgeQueryHandler implements QueryHandler<SearchJudgeQuery, JudgeResponse> {
    private final JudgeSearcher searcher;

    public SearchJudgeQueryHandler(JudgeSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public JudgeResponse handle(SearchJudgeQuery query) {
        return searcher.search(query.getEmail(), query.getCommentId());
    }
}
