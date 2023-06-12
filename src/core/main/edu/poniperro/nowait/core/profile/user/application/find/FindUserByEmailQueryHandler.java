package edu.poniperro.nowait.core.profile.user.application.find;

import edu.poniperro.nowait.core.profile.user.application.UserResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public class FindUserByEmailQueryHandler implements QueryHandler<FindUserByEmailQuery, UserResponse> {

        private final UserFinder finder;

        public FindUserByEmailQueryHandler(UserFinder finder) {
            this.finder = finder;
        }

        @Override
        public UserResponse handle(FindUserByEmailQuery query) {
            return finder.find(query.getEmail());
        }
}
