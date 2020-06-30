package org.confiz.command.commands;

import org.confiz.command.ServiceExecutionContext;
import org.confiz.command.receiver.Session;

public class CommitCommand implements Command {
    @Override
    public void execute() {
        Session session = ServiceExecutionContext.getSession();
        session.commit();

        System.out.println("Commit command executed!, Id: " + session.getId());

        ServiceExecutionContext.removeSession();
    }
}
