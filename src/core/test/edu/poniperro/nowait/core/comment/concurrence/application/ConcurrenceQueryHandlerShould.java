package edu.poniperro.nowait.core.comment.concurrence.application;

import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.core.comment.concurrence.ConcurrenceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConcurrenceQueryHandlerShould {
    protected CommentRepository repository;
    protected ConcurrenceQueryHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(CommentRepository.class);
        handler = new ConcurrenceQueryHandler(new ConcurrenceCalculator(repository));
    }

    @Test
    void should_search_comments_when_query_is_valid() {
        ConcurrenceQuery query = ConcurrenceQueryMother.random();
        handler.handle(query);
        verify(repository, times(1)).searchByPlaceId(query.getPlaceId());
    }

    @Test
    void should_return_concurrence_response() {
        ConcurrenceResponse response = handler.handle(ConcurrenceQueryMother.random());
        assertNotNull(response);
    }
}