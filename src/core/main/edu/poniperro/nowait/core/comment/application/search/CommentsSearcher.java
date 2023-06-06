package edu.poniperro.nowait.core.comment.application.search;

import edu.poniperro.nowait.core.comment.application.CommentsResponse;
import edu.poniperro.nowait.core.comment.application.CommentResponse;
import edu.poniperro.nowait.core.comment.domain.CommentRepository;
import edu.poniperro.nowait.shared.domain.Service;

import java.util.stream.Collectors;

@Service
public final class CommentsSearcher {

    private final CommentRepository repository;

    public CommentsSearcher(CommentRepository repository) {
        this.repository = repository;
    }

    public CommentsResponse search(String placeId) {
        return new CommentsResponse(
                repository.searchByPlaceId(placeId)
                        .stream()
                        .map(CommentResponse::fromAggregate)
                        .collect(Collectors.toList())
        );
    }
}
