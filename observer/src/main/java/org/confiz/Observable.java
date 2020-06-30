package org.confiz;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private boolean changed = false;
    private List<Observer> observers = new ArrayList<>();

    public Observable() {
    }

    public Observable(List<Observer> observers) {
        this.observers = observers;
    }

    public synchronized void addObserver(Observer o) {
        if (o == null) {
            throw new RuntimeException("Observer can't be null");
        } else {
            if (!observers.contains(o)) {
                observers.add(o);
            }
        }

    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public synchronized void removeObservers() {
        observers.clear();

    }

    public void notifyObservers(Object object) {
        Object[] observerArray;
        synchronized (this) {
            if (!changed) {
                return;
            }

            observerArray = observers.toArray();
            changed = false;
        }

        for(int var3 = observerArray.length - 1; var3 >= 0; --var3) {
            ((Observer)observerArray[var3]).update(this, object);
        }
    }

    public boolean isChanged() {
        return changed;
    }
}
