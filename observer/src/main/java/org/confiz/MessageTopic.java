package org.confiz;

import java.util.ArrayList;
import java.util.List;

public class MessageTopic extends Observable {
    private List<Message> messages = new ArrayList<>();

    public MessageTopic() {

    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
        setChanged(true);
        notifyObservers(message);
    }


}
