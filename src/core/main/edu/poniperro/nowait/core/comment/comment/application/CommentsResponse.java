package edu.poniperro.nowait.core.comment.comment.application;

import edu.poniperro.nowait.shared.domain.bus.query.Response;

import java.util.List;
import java.util.Objects;

public final class CommentsResponse implements Response {
    private List<CommentResponse> comments;

    public CommentsResponse(List<CommentResponse> comments) {
        this.comments = comments;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsResponse that = (CommentsResponse) o;

        return Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return comments != null ? comments.hashCode() : 0;
    }
}
