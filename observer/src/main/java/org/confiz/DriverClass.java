package org.confiz;

public class DriverClass {
    public static void main(String[] args) {
        MessageTopic topic = new MessageTopic();

        topic.addObserver((observable, observed) -> System.out.println("Message: "  + ((Message)observed).getTest()));

        topic.addObserver(((observable, observed) -> {
            System.out.println("Another log -> Message: "  + ((Message)observed).getTest());
        }));

        topic.addMessage(new Message("Hello World!"));
        topic.removeObservers();
    }
}
