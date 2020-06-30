package org.confiz;

public interface Command {
    void execute();

    CommandType getType();
}
