package edu.poniperro.nowait.core.comment.judge.application;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateJudgeCommandHandler implements CommandHandler<CreateJudgeCommand> {

        private final JudgeCreator creator;

        public CreateJudgeCommandHandler(JudgeCreator creator) {
            this.creator = creator;
        }

        @Override
        public void handle(CreateJudgeCommand command) {
            creator.create(command.getEmail(), command.getCommentId(), command.getLike(), command.getDislike(),
                    command.getReport());
        }
}
