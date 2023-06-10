package edu.poniperro.nowait.core.comment.comment.application.update;

import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public final class CommentUpdater {
    private final CommentRepository repository;

    public CommentUpdater(CommentRepository repository) {
        this.repository = repository;
    }

    public void update(String id, String commentText, int quantifiableElement, String creationDate) {
        repository.update(id, commentText, quantifiableElement, creationDate);
    }
}
