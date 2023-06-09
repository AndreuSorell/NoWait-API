package edu.poniperro.nowait.core.comment.comment.application.search;

import edu.poniperro.nowait.core.comment.comment.application.CommentsResponse;
import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.shared.domain.MotherCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class SearchCommentsQueryHandlerShould {
    protected CommentRepository repository;
    protected SearchCommentsQueryHandler handler;

    @BeforeEach
    void setUp() {
        repository = mock(CommentRepository.class);
        handler = new SearchCommentsQueryHandler(new CommentsSearcher(repository));
    }

    @Test
    void should_look_for_comments_in_db() {
        handler.handle(new SearchCommentsByPlaceIdQuery("1"));
        verify(repository, times(1)).searchByPlaceId("1");
    }

    @Test
    void should_return_comments_response() {
        CommentsResponse response = handler.handle(new SearchCommentsByPlaceIdQuery("1"));
        assertNotNull(response);
    }

    @Test
    void should_return_comments_response_with_comments() {
        CommentsResponse response = handler.handle(new SearchCommentsByPlaceIdQuery("1"));
        assertNotNull(response.getComments());
    }

    @Test
    void should_return_comments_response_with_empty_comments() {
        CommentsResponse response = handler.handle(new SearchCommentsByPlaceIdQuery("1"));
        assertEquals(0, response.getComments().size());
    }

    @Test
    void should_return_comments_response_with_comments_when_there_are_comments_with_place_id() {
        int placeId = MotherCreator.random().number().randomDigit();
        int commentsNumber = MotherCreator.random().number().randomDigitNotZero();
        when(repository.searchByPlaceId(String.valueOf(placeId))).thenReturn(CommentMother.randomListWithPlaceId(commentsNumber, String.valueOf(placeId)));
        CommentsResponse response = handler.handle(new SearchCommentsByPlaceIdQuery(String.valueOf(placeId)));
        assertEquals(commentsNumber, response.getComments().size());
    }
}