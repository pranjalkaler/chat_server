package com.pranjalkaler.chat_server.utils;

public enum MessageType {

    TEXT("TEXT"),
    ACK("ACK"),
    READ_RECEIPT("READ_RECEIPT");

    public final String messageType;

    MessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "MessageType{" +
                "messageType='" + messageType + '\'' +
                '}';
    }
}
