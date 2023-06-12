package edu.poniperro.nowait.core.comment.comment.application.create;

import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public class CommentCreator {
    private final CommentRepository repository;

    public CommentCreator(CommentRepository repository) {
        this.repository = repository;
    }

    public void create(String commentText, int quantifiableElement, String email, int reports, int likes,
            int dislikes, String creationDate, String placeId) {
        Comment comment = Comment.create(commentText, quantifiableElement, email, reports, likes, dislikes,
                creationDate, placeId);
        repository.save(comment);
    }
}
