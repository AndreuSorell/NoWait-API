package edu.poniperro.nowait.core.profile.user.application.search;

import edu.poniperro.nowait.core.profile.user.application.create.DuplicateUserException;
import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public class UserEmailSearcher {
    private final UserRepository repository;

    public UserEmailSearcher(UserRepository repository) {
        this.repository = repository;
    }

    public SearchUserEmailResponse search(String email) {
        if (repository.searchByEmail(email).getEmail()!=null) {
            return new SearchUserEmailResponse(repository.searchByEmail(email).getEmail());
        } else {
            return new SearchUserEmailResponse("");
        }
    }
}
