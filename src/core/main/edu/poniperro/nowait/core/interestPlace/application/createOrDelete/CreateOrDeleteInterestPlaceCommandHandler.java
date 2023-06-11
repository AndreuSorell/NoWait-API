package edu.poniperro.nowait.core.interestPlace.application.createOrDelete;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateOrDeleteInterestPlaceCommandHandler implements CommandHandler<CreateOrDeleteInterestPlaceCommand> {

        private final InterestPlaceCreatorOrDeleter creatorOrDeleter;

        public CreateOrDeleteInterestPlaceCommandHandler(InterestPlaceCreatorOrDeleter creatorOrDeleter) {
            this.creatorOrDeleter = creatorOrDeleter;
        }

        @Override
        public void handle(CreateOrDeleteInterestPlaceCommand command) {
            creatorOrDeleter.createOrDelete(command.getEmail(), command.getPlaceId());
        }
}
