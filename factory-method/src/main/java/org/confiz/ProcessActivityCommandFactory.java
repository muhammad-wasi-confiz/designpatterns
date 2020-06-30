package org.confiz;

public class ProcessActivityCommandFactory {

    public static Command createCommand(CommandType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null!)");
        }

        switch (type) {
            case MERGE_VARIABLES:
                return new MergeVariablesCommand();
            case PROCESS_ELEMENT:
                return new ProcessElementCommand();
            case PROCESS_FINALIZER:
                return new ProcessFinalizerCommand();
            default:
                throw new IllegalArgumentException("Invalid command type: " + type);
        }
    }
}
