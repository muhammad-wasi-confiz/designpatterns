package org.confiz;

public class MergeVariablesCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Merge variables command executed!");
    }

    @Override
    public CommandType getType() {
        return CommandType.MERGE_VARIABLES;
    }
}
