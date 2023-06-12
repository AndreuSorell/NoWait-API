package edu.poniperro.nowait.core.profile.user.application.update;

import edu.poniperro.nowait.core.profile.user.domain.User;
import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public final class UserUpdater {
    private final UserRepository repository;

    public UserUpdater(UserRepository repository) {
        this.repository = repository;
    }

    public void update(String name, String email, String password, String anonymous) {
        repository.update(name, email, password, anonymous);
    }
}
