package edu.poniperro.nowait.core.comment.judge.application.create;

import edu.poniperro.nowait.core.comment.judge.domain.Judge;
import edu.poniperro.nowait.core.comment.judge.domain.JudgeRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public class JudgeCreator {
    private final JudgeRepository repository;

    public JudgeCreator(JudgeRepository repository) {
        this.repository = repository;
    }

    public void create(String email, String commentId, int like, int dislike, int report) {
        Judge judge = Judge.create(email, commentId, like, dislike, report);
        repository.save(judge);
    }
}
