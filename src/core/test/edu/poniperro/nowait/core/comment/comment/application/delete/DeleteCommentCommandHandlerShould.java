package edu.poniperro.nowait.core.comment.comment.application.delete;

import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.poniperro.nowait.core.comment.comment.application.CommentMother;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteCommentCommandHandlerShould {
    protected CommentRepository repository;
    protected DeleteCommentCommandHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(CommentRepository.class);
        handler = new DeleteCommentCommandHandler(new CommentDeleter(repository));
    }

    @Test
    void should_delete_comment_when_command_is_valid() {
        DeleteCommentCommand command = DeleteCommentCommandMother.random();
        handler.handle(command);
        verify(repository, times(1)).delete(command.getId());
    }

    @Test
    void should_delete_comment_when_command_id_is_null() {
        DeleteCommentCommand command = DeleteCommentCommandMother.create(null);
        handler.handle(command);
        verify(repository, times(1)).delete(command.getId());
    }
}