package edu.poniperro.nowait.core.comment.judge.application.search;

import edu.poniperro.nowait.shared.domain.bus.query.Query;

import java.util.Objects;

public final class SearchJudgeQuery implements Query {
    private String email;
    private String commentId;

    public SearchJudgeQuery(String email, String commentId) {
        this.email = email;
        this.commentId = commentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchJudgeQuery that = (SearchJudgeQuery) o;

        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(commentId, that.commentId);
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (commentId != null ? commentId.hashCode() : 0);
        return result;
    }
}
