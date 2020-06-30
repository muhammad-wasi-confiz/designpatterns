package org.confiz.command;

import org.confiz.command.commands.CommitCommand;
import org.confiz.command.commands.RollbackCommand;
import org.confiz.command.invoker.CommandInvoker;
import org.junit.Test;

public class CommandTest {

    @Test
    public void test() {
        // act like a client for testing - Perfect Test :D :D
        CommandInvoker executor = new CommandInvoker(new CommitCommand());
        executor.executeCommand();

        executor = new CommandInvoker(new RollbackCommand());
        executor.executeCommand();
    }
}
