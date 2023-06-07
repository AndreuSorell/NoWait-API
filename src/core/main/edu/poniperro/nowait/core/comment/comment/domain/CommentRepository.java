package edu.poniperro.nowait.core.comment.comment.domain;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);
    List<Comment> searchByEmailAndPlaceId(String email, String placeId);
    List<Comment> searchByPlaceId(String placeId);
}
