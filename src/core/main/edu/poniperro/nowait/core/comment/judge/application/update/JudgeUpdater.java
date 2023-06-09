package edu.poniperro.nowait.core.comment.judge.application.update;

import edu.poniperro.nowait.core.comment.judge.domain.Judge;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.core.comment.judge.domain.JudgeRepository;

@Service
public final class JudgeUpdater {
    private final JudgeRepository repository;

    public JudgeUpdater(JudgeRepository repository) {
        this.repository = repository;
    }

    public void update(String email, String commentId, int like, int dislike, int report) {
        if (repository.update(email, commentId, like, dislike, report)) {
            return;
        } else {
            Judge judge = Judge.create(email, commentId, like, dislike, report);
            repository.save(judge);
            repository.update(email, commentId, like, dislike, report);
        }
    }
}
