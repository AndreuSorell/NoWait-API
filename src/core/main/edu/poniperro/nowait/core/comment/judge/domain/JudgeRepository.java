package edu.poniperro.nowait.core.comment.judge.domain;

public interface JudgeRepository {
    void save(Judge judge);
    boolean update(String email, String commentId, int like, int dislike, int report);
    Judge findByEmailAndCommentId(String email, String commentId);

}
