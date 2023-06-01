package edu.poniperro.nowait.core.profile.user.application.create;

import edu.poniperro.nowait.core.profile.user.domain.User;
import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public final class UserCreator {
    private final UserRepository repository;

    public UserCreator(UserRepository repository) {
        this.repository = repository;
    }

    public void create(int id, String name, String email, String password, String anonymous, String type,
            String creationDate) {
        User user = User.create(id, name, email, password, anonymous, type, creationDate);
        repository.save(user);
    }
}
