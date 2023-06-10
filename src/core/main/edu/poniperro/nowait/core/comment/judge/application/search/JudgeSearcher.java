package edu.poniperro.nowait.core.comment.judge.application.search;

import edu.poniperro.nowait.core.comment.judge.application.JudgeResponse;
import edu.poniperro.nowait.core.comment.judge.domain.Judge;
import edu.poniperro.nowait.core.comment.judge.domain.JudgeRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public final class JudgeSearcher {
    private final JudgeRepository repository;

    public JudgeSearcher(JudgeRepository repository) {
        this.repository = repository;
    }

    public JudgeResponse search(String email, String commentId) {
        Judge judge = repository.findByEmailAndCommentId(email, commentId);
        if (judge!=null) {
            return new JudgeResponse(judge.getLike(), judge.getDislike(), judge.getReport());
        } else {
            return new JudgeResponse(0, 0, 0);
        }
    }
}
