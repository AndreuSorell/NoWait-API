package edu.poniperro.nowait.core.profile.user.application.update;

import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUserCommandHandlerShould {
    protected UserRepository repository;
    protected UpdateUserCommandHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        handler = new UpdateUserCommandHandler(new UserUpdater(repository));
    }

    @Test
    void should_update_user_when_command_is_valid() {
        UpdateUserCommand command = UpdateUserCommandMother.random();
        handler.handle(command);
        verify(repository, times(1)).update(command.getName(), command.getEmail(), command.getPassword(), command.getAnonymous());
    }

    @Test
    void should_not_update_user_when_command_email_is_null() {
        UpdateUserCommand command = UpdateUserCommandMother.create("name", null, "password", "false");
        handler.handle(command);
        verify(repository, times(0)).update(command.getName(), command.getEmail(), command.getPassword(), command.getAnonymous());
    }
}