package org.confiz.command.invoker;

import org.confiz.command.commands.Command;

public class CommandInvoker {
    private final Command command;

    public CommandInvoker(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
