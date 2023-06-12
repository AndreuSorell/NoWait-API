package edu.poniperro.nowait.core.profile.user.application.find;

import edu.poniperro.nowait.core.profile.user.application.UserBasicDataResponse;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.query.QueryHandler;

@Service
public final class FindUserBasicDataByEmailQueryHandler implements QueryHandler<FindUserBasicDataByEmailQuery, UserBasicDataResponse> {

        private final UserFinder finder;

        public FindUserBasicDataByEmailQueryHandler(UserFinder finder) {
            this.finder = finder;
        }

        @Override
        public UserBasicDataResponse handle(FindUserBasicDataByEmailQuery query) {
            return finder.findBasicData(query.getEmail());
        }
}
