package org.confiz;

import org.junit.Assert;
import org.junit.Test;

public class FactoryMethodTest {
    @Test
    public void test() {
        Command mergeVariablesCommand = ProcessActivityCommandFactory.createCommand(CommandType.MERGE_VARIABLES);
        mergeVariablesCommand.execute();

        Assert.assertEquals(CommandType.MERGE_VARIABLES, mergeVariablesCommand.getType());

        Command processElementCommand = ProcessActivityCommandFactory.createCommand(CommandType.PROCESS_ELEMENT);
        processElementCommand.execute();

        Assert.assertEquals(CommandType.PROCESS_ELEMENT, processElementCommand.getType());

        Command processFinalizerCommand = ProcessActivityCommandFactory.createCommand(CommandType.PROCESS_FINALIZER);
        processFinalizerCommand.execute();

        Assert.assertEquals(CommandType.PROCESS_FINALIZER, processFinalizerCommand.getType());
    }
}
