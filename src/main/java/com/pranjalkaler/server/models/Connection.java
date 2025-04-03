package com.pranjalkaler.server.models;

import org.springframework.data.util.Pair;

public class Connection {

    private Pair<User, User> endUsers;

    private MessageQueue forwardQueue;

    private MessageQueue reverseQueue;

    public Connection(Pair<User, User> endUsers) {
        this.endUsers = endUsers;
        this.forwardQueue = new MessageQueue(endUsers.getFirst(), endUsers.getSecond());
        this.reverseQueue = new MessageQueue(endUsers.getSecond(), endUsers.getFirst());
    }

    public Connection(Pair<User, User> endUsers, MessageQueue forwardQueue, MessageQueue reverseQueue) {
        this.endUsers = endUsers;
        this.forwardQueue = forwardQueue;
        this.reverseQueue = reverseQueue;
    }

    public Pair<User, User> getEndUsers() {
        return endUsers;
    }

    public void setEndUsers(Pair<User, User> endUsers) {
        this.endUsers = endUsers;
    }

    public MessageQueue getForwardQueue() {
        return forwardQueue;
    }

    public void setForwardQueue(MessageQueue forwardQueue) {
        this.forwardQueue = forwardQueue;
    }

    public MessageQueue getReverseQueue() {
        return reverseQueue;
    }

    public void setReverseQueue(MessageQueue reverseQueue) {
        this.reverseQueue = reverseQueue;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "endUsers=" + endUsers +
                ", forwardQueue=" + forwardQueue +
                ", reverseQueue=" + reverseQueue +
                '}';
    }
}
