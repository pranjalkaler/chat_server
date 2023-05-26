package com.pranjalkaler.chat_server.connection;

import com.pranjalkaler.chat_server.models.Connection;
import com.pranjalkaler.chat_server.models.User;

import java.util.List;

public interface ConnectionHandler {

    Connection getConnection(User user1, User user2);

    Connection createConnection(User user1, User user2);

    List<Connection> getAllConnections(User user);

}
