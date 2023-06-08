package edu.poniperro.nowait.core.profile.user.domain;

import java.util.Optional;

public interface UserRepository {
    User findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    void save(User user);
    User searchByEmail(String email);
    void update(String name, String email, String password, String anonymous);
}
