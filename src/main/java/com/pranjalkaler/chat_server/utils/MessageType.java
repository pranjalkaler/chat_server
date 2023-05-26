package com.pranjalkaler.chat_server.utils;

public enum MessageType {

    TEXT("text"),
    ACK("ack"),
    READ_RECEIPT("read-receipt");

    public final String messageType;

    MessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDeploymentScope() {
        return this.messageType;
    }

    @Override
    public String toString() {
        return "MessageType{" +
                "messageType='" + messageType + '\'' +
                '}';
    }
}
