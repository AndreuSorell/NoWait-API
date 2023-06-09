package edu.poniperro.nowait.core.comment.comment.application.create;

import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCommentCommandHandlerShould {
    protected CommentRepository repository;
    protected CreateCommentCommandHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(CommentRepository.class);
        handler = new CreateCommentCommandHandler(new CommentCreator(repository));
    }

}