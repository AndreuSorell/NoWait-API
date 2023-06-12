package edu.poniperro.nowait.core.comment.comment.application.search;

import edu.poniperro.nowait.core.comment.comment.application.CommentsResponse;
import edu.poniperro.nowait.core.comment.comment.application.CommentResponse;
import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.shared.domain.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public final class CommentsSearcher {

    private final CommentRepository repository;

    public CommentsSearcher(CommentRepository repository) {
        this.repository = repository;
    }

    public CommentsResponse search(String placeId) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime limitDateTime = currentDateTime.minusHours(8); // Mostramos los comentarios de las últimas 8 horas
        return new CommentsResponse(
                repository.searchByPlaceId(placeId)
                        .stream()
                        .filter(comment -> {
                            LocalDateTime commentDateTime = LocalDateTime.parse(comment.getCreationDate());
                            return commentDateTime.isAfter(limitDateTime) && commentDateTime.isBefore(currentDateTime);
                        })
                        .map(CommentResponse::fromAggregate)
                        .collect(Collectors.toList())
        );
    }



    public CommentsResponse searchByEmail(String email) {
        return new CommentsResponse(
                repository.searchByEmail(email)
                        .stream()
                        .map(CommentResponse::fromAggregate)
                        .collect(Collectors.toList())
        );
    }
}
