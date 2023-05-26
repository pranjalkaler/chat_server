package com.pranjalkaler.chat_server.models;

import com.pranjalkaler.chat_server.utils.MessageType;

public class Message {

    private String message;

    private User sender;

    private User receiver;

    private MessageType messageType;

    public Message(String message, User sender, User receiver, MessageType messageType) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", messageType=" + messageType +
                '}';
    }
}
