package edu.poniperro.nowait.core.comment.judge.application.update;

import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.bus.command.CommandHandler;

@Service
public final class UpdateJudgeCommandHandler implements CommandHandler<UpdateJudgeCommand> {

    private final JudgeUpdater updater;

    public UpdateJudgeCommandHandler(JudgeUpdater updater) {
        this.updater = updater;
    }

    @Override
    public void handle(UpdateJudgeCommand command) {

        updater.update(command.getEmail(), command.getCommentId(), command.getLike(), command.getDislike(), command.getReport());
    }
}
