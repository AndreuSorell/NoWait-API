package edu.poniperro.nowait.apps.core.api.command;

import edu.poniperro.nowait.shared.infraestructure.cli.ConsoleCommand;

public final class FakeCommand extends ConsoleCommand {
    @Override
    public void execute(String[] args) {
        info("This is a fake command!");
    }
}