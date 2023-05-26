package com.pranjalkaler.chat_server.server.impl;

import com.pranjalkaler.chat_server.connection.ConnectionHandler;
import com.pranjalkaler.chat_server.connection.impl.ConnectionHandlerImpl;
import com.pranjalkaler.chat_server.models.Message;
import com.pranjalkaler.chat_server.models.User;
import com.pranjalkaler.chat_server.server.MessageManager;
import com.pranjalkaler.chat_server.utils.Logger;
import com.pranjalkaler.chat_server.utils.MessageType;

import java.util.ArrayList;
import java.util.List;

public class MessageManagerImpl implements MessageManager {

    private final ConnectionHandler connectionHandler;

    private final Logger logger;

    public MessageManagerImpl() {
        connectionHandler = new ConnectionHandlerImpl();
        logger = new Logger(MessageManagerImpl.class);
    }

    @Override
    public void sendMessage(User sender, User receiver, Message message) {
        // replies message Acknowledgment

        var connection = this.connectionHandler.getConnection(sender, receiver);
        var forwardQueue = connection.getForwardQueue();
        var reverseQueue = connection.getReverseQueue();

        if (forwardQueue.getSender().getUsername().equals(sender.getUsername())) {
            forwardQueue.getQueue().add(message);
            if (message.getMessageType() == MessageType.TEXT) {
                // Only send acknowledgement for text messages.
                // No need to send acknowledgement for ack messages.
                reverseQueue.getQueue().add(new Message("", receiver, sender, MessageType.ACK));
                logger.debug("ACK sent for message %s".formatted(message.toString()));
            }
        }
        else if (reverseQueue.getSender().getUsername().equals(sender.getUsername())) {
            reverseQueue.getQueue().add(message);
            if (message.getMessageType() == MessageType.TEXT) {
                // Only send acknowledgement for text messages.
                // No need to send acknowledgement for ack messages.
                forwardQueue.getQueue().add(new Message("", receiver, sender, MessageType.ACK));
                logger.debug("ACK sent for message %s".formatted(message.toString()));
            }
        }
        else {
            // log and throw exception
            logger.error("Misaligned queues between %s and %s".formatted(
                    connection.getEndUsers().getFirst().getUsername(), connection.getEndUsers().getSecond().getUsername()));
        }
    }

    @Override
    public List<Message> readMessages(User reader) {

        // fetch all the connections of this user
        var connections = this.connectionHandler.getAllConnections(reader);
        if (connections.isEmpty()) {
            logger.debug("No connections exist for user %s".formatted(reader.getUsername()));
            return null;
        }

        var messages = new ArrayList<Message>();
        for (var connection : connections) {

            // from each connection, fetch all the messages directed to this user.
            // It can be either of forward or reverse queue
            var forwardQueue = connection.getForwardQueue();
            var reverseQueue = connection.getReverseQueue();

            if (forwardQueue.getReceiver().getUsername().equals(reader.getUsername())) {
                var queue = forwardQueue.getQueue();
                while (!queue.isEmpty()) {
                    messages.add(queue.remove());
                }
            }
            else if (reverseQueue.getReceiver().getUsername().equals(reader.getUsername())) {
                var queue = reverseQueue.getQueue();
                while (!queue.isEmpty()) {
                    messages.add(queue.remove());
                }
            }
            else {
                // log and throw exception
                logger.error("Misaligned queues between %s and %s".formatted(
                        connection.getEndUsers().getFirst().getUsername(), connection.getEndUsers().getSecond().getUsername()));
            }
        }
        return messages;
    }
}
