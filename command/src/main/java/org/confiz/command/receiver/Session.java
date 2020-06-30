package org.confiz.command.receiver;


import java.util.UUID;

/**
 * Mimic the hibernate session -
 */
public class Session {

    private final UUID id;

    public Session(UUID id) {
        this.id = id;
    }

    public boolean rollback() {
        System.out.println("Rollback");
        return true;
    }

    public boolean commit() {
        System.out.println("Commit");
        return true;
    }

    public UUID getId() {
        return id;
    }
}
