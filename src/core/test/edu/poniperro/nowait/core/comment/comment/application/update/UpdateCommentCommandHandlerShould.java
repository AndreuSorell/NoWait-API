package edu.poniperro.nowait.core.comment.comment.application.update;

import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCommentCommandHandlerShould {
    protected CommentRepository repository;
    protected UpdateCommentCommandHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(CommentRepository.class);
        handler = new UpdateCommentCommandHandler(new CommentUpdater(repository));
    }

    @Test
    void should_update_comment_when_command_is_valid() {
        UpdateCommentCommand command = UpdateCommentCommandMother.random();
        handler.handle(command);
        verify(repository, times(1)).update(command.getId(), command.getCommentText(), command.getQuantifiableElement(), command.getCreationDate());
    }

    @Test
    void should_not_update_comment_when_command_id_is_null() {
        UpdateCommentCommand command = UpdateCommentCommandMother.create(null, "commentText", 1, "creationDate");
        handler.handle(command);
        verify(repository, times(0)).update(command.getId(), command.getCommentText(), command.getQuantifiableElement(), command.getCreationDate());
    }
}