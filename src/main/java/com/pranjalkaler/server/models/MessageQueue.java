package com.pranjalkaler.server.models;

import java.util.ArrayDeque;
import java.util.Queue;

public class MessageQueue {

    private Queue<Message> queue;

    private User sender;

    private User receiver;

    public MessageQueue(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.queue = new ArrayDeque<>();
    }

    public MessageQueue(Queue<Message> queue, User sender, User receiver) {
        this.queue = queue;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Queue<Message> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Message> queue) {
        this.queue = queue;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "MessageQueue{" +
                "queue=" + queue +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
