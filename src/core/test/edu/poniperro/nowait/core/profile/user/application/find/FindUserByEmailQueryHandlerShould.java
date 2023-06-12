package edu.poniperro.nowait.core.profile.user.application.find;

import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindUserByEmailQueryHandlerShould {
    protected UserRepository repository;
    protected FindUserByEmailQueryHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        handler = new FindUserByEmailQueryHandler(new UserFinder(repository));
    }

    /*@Test
    void should_look_for_user_in_db() {
        handler.handle(new FindUserByEmailQuery("email"));
        verify(repository, times(1)).findByEmail("email");
    }*/
}