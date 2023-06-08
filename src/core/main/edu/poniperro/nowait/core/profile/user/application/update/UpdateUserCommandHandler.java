package edu.poniperro.nowait.core.profile.user.application.update;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class UpdateUserCommandHandler implements CommandHandler<UpdateUserCommand> {

        private final UserUpdater updater;

        public UpdateUserCommandHandler(UserUpdater updater) {
            this.updater = updater;
        }

        @Override
        public void handle(UpdateUserCommand command) {

            updater.update(command.getName(), command.getEmail(), command.getPassword(), command.getAnonymous());
        }
}
