package edu.poniperro.nowait.core.comment.comment.application.delete;

import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public class CommentDeleter {
    private final CommentRepository repository;

    public CommentDeleter(CommentRepository repository) {
        this.repository = repository;
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
