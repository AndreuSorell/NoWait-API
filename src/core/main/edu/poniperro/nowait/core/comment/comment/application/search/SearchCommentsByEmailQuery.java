package edu.poniperro.nowait.core.comment.comment.application.search;

import edu.poniperro.nowait.shared.domain.bus.query.Query;

import java.util.Objects;

public final class SearchCommentsByEmailQuery implements Query {
    private String email;

    public SearchCommentsByEmailQuery(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchCommentsByEmailQuery that = (SearchCommentsByEmailQuery) o;

        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
