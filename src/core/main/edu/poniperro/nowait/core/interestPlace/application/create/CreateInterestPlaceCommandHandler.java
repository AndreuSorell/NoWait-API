package edu.poniperro.nowait.core.interestPlace.application.create;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateInterestPlaceCommandHandler implements CommandHandler<CreateInterestPlaceCommand> {

        private final InterestPlaceCreator creator;

        public CreateInterestPlaceCommandHandler(InterestPlaceCreator creator) {
            this.creator = creator;
        }

        @Override
        public void handle(CreateInterestPlaceCommand command) {
            creator.create(command.getEmail(), command.getPlaceId());
        }
}
