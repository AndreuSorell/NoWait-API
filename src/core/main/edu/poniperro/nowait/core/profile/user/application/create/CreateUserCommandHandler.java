package edu.poniperro.nowait.core.profile.user.application.create;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private final UserCreator creator;

    public CreateUserCommandHandler(UserCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateUserCommand command) {
        creator.create(command.getId(), command.getName(), command.getEmail(), command.getPassword(), command.getAnonymous(),
                command.getType(), command.getCreationDate());
    }
}
