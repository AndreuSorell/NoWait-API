package edu.poniperro.nowait.core.profile.user.application.create;

import edu.poniperro.nowait.core.profile.user.domain.User;
import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserCommandHandlerShould {
    protected UserRepository repository;
    protected CreateUserCommandHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        handler = new CreateUserCommandHandler(new UserCreator(repository));
    }

    @Test
    void should_create_user_when_command_is_valid() {
        CreateUserCommand command = CreateUserCommandMother.random();
        handler.handle(command);
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void should_create_user_with_name() {
        CreateUserCommand command = CreateUserCommandMother.random();
        handler.handle(command);
        verify(repository, times(1)).save(argThat(user -> user.getName().equals(command.getName())));
    }
}