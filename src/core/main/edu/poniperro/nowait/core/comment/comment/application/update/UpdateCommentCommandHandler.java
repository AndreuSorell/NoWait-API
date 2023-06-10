package edu.poniperro.nowait.core.comment.comment.application.update;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class UpdateCommentCommandHandler implements CommandHandler<UpdateCommentCommand> {

            private final CommentUpdater updater;

            public UpdateCommentCommandHandler(CommentUpdater updater) {
                this.updater = updater;
            }

            @Override
            public void handle(UpdateCommentCommand command) {

                updater.update(command.getId(), command.getCommentText(), command.getQuantifiableElement(), command.getCreationDate());
            }
}
