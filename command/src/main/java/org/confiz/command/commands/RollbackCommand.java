package org.confiz.command.commands;

import org.confiz.command.ServiceExecutionContext;
import org.confiz.command.receiver.Session;

public class RollbackCommand implements Command {

    @Override
    public void execute() {
        Session session = ServiceExecutionContext.getSession();
        session.rollback();

        System.out.println("Rollback command executed!, Id: " + session.getId() );

        ServiceExecutionContext.removeSession();
    }
}
