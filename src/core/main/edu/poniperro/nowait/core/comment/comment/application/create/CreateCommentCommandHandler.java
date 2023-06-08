package edu.poniperro.nowait.core.comment.comment.application.create;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateCommentCommandHandler implements CommandHandler<CreateCommentCommand> {

    private final CommentCreator creator;

    public CreateCommentCommandHandler(CommentCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateCommentCommand command) {
        creator.create(command.getCommentText(), command.getQuantifiableElement(), command.getEmail(),
                command.getReports(), command.getLikes(), command.getDislikes(), command.getCreationDate(),
                command.getPlaceId());
    }
}
