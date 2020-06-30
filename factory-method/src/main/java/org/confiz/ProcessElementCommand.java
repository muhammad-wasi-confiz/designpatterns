package org.confiz;

public class ProcessElementCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Process element command executed!");
    }

    @Override
    public CommandType getType() {
        return CommandType.PROCESS_ELEMENT;
    }
}
