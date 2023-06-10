package edu.poniperro.nowait.core.comment.comment.domain;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);
    List<Comment> searchByEmailAndPlaceId(String email, String placeId);
    List<Comment> searchByPlaceId(String placeId);
    Comment findById(String id);
    void delete(String id);
    void update(String id, String commentText, int quantifiableElement, String creationDate);
    void updateJudge(String id, int likes, int dislikes, int reports);
    List<Comment> searchByEmail(String email);
}
