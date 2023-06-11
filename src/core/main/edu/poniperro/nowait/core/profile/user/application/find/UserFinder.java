package edu.poniperro.nowait.core.profile.user.application.find;

import edu.poniperro.nowait.core.profile.user.application.UserBasicDataResponse;
import edu.poniperro.nowait.core.profile.user.application.UserResponse;
import edu.poniperro.nowait.core.profile.user.domain.User;
import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public final class UserFinder {

        private final UserRepository repository;

        public UserFinder(UserRepository repository) {
            this.repository = repository;
        }

        public UserResponse find(String email) {
            return UserResponse.fromAggregate(repository.findByEmail(email));
        }

        public UserBasicDataResponse findBasicData(String email) {
            User user = repository.findByEmail(email);
            if (user.getAnonymous().equals("true")) {
                return new UserBasicDataResponse("", "");
            } else {
                return new UserBasicDataResponse(user.getName(), user.getType());
            }
        }
}
