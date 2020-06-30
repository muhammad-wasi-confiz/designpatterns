package org.confiz;

public class ProcessFinalizerCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Process finalizer command executed!");
    }

    @Override
    public CommandType getType() {
        return CommandType.PROCESS_FINALIZER;
    }
}
