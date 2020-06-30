package org.confiz.command;

import org.confiz.command.receiver.Session;

import java.util.UUID;

public class ServiceExecutionContext {
    private static ThreadLocal<Session> session = ThreadLocal.withInitial(() -> new Session(UUID.randomUUID()));

    public static Session getSession() {
        return session.get();
    }


    public static void removeSession() {
        session.remove();
    }

}
