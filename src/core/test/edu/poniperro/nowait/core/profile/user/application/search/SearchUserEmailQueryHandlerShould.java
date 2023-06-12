package edu.poniperro.nowait.core.profile.user.application.search;

import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchUserEmailQueryHandlerShould {
    protected UserRepository repository;
    protected SearchUserEmailQueryHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        handler = new SearchUserEmailQueryHandler(new UserEmailSearcher(repository));
    }
}
    /*@Test
    void should_look_for_user_in_db() {
        handler.handle(new SearchUserEmailQuery("email"));
        verify(repository, times(1)).searchByEmail("email");
    }

    @Test
    void should_return_user_email_response() {
        SearchUserEmailResponse response = handler.handle(new SearchUserEmailQuery("email"));
        assertNotNull(response);
    }*/