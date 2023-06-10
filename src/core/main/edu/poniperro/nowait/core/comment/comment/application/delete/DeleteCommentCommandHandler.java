package edu.poniperro.nowait.core.comment.comment.application.delete;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class DeleteCommentCommandHandler implements CommandHandler<DeleteCommentCommand> {

    private final CommentDeleter deleter;

    public DeleteCommentCommandHandler(CommentDeleter deleter) {
        this.deleter = deleter;
    }

    @Override
    public void handle(DeleteCommentCommand command) {
        deleter.delete(command.getId());
    }
}

