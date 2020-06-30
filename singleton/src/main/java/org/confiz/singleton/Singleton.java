package org.confiz.singleton;

import java.io.Serializable;

public class Singleton implements Serializable {

    private static volatile Singleton singleton;

    private Singleton() {
        if (singleton != null) {
            throw new IllegalStateException("Object was already initialized!");
        }
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }

    protected Object readResolve()
    {
        return singleton;
    }
}
