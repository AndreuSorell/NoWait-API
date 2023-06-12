package edu.poniperro.nowait.core.profile.user.application.search;

import edu.poniperro.nowait.shared.domain.bus.query.Response;

public class SearchUserEmailResponse implements Response {
    private String email;

    public SearchUserEmailResponse(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
