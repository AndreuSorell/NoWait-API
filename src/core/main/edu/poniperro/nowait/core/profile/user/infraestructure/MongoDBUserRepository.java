package edu.poniperro.nowait.core.profile.user.infraestructure;

import edu.poniperro.nowait.core.profile.user.domain.User;
import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import edu.poniperro.nowait.shared.domain.Service;
import org.springframework.context.annotation.Primary;

import java.util.Optional;

@Service
@Primary
public class MongoDBUserRepository implements UserRepository {

        @Override
        public void save(User user) {
        }

        @Override
        public Optional<User> findById(int id) {
            return null;
        }
}
