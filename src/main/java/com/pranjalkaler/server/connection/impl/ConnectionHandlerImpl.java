package com.pranjalkaler.server.connection.impl;

import com.pranjalkaler.server.connection.ConnectionHandler;
import com.pranjalkaler.server.models.Connection;
import com.pranjalkaler.server.models.User;
import com.pranjalkaler.server.utils.Logger;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectionHandlerImpl implements ConnectionHandler {

    // Needs to be a singleton (bean/Autowired)? idk

    private final List<Connection> connections;

    private final Logger logger;

    public ConnectionHandlerImpl() {
        // TODO: load connections from DB
        this.connections = new ArrayList<>();
        this.logger = new Logger(ConnectionHandlerImpl.class);
    }

    @Override
    public Connection getConnection(User user1, User user2) {
        Connection connection = null;
        for (var connectionItr : connections) {
            var users = connectionItr.getEndUsers();
            if ((users.getFirst().getUsername().equals(user1.getUsername()) && users.getSecond().getUsername().equals(user2.getUsername()))
                || (users.getFirst().getUsername().equals(user2.getUsername()) && users.getSecond().getUsername().equals(user1.getUsername()))
            ) {
                connection = connectionItr;
                break;
            }
        }

        if (connection == null) {
            // Connection between these two users does not exist. Create a new one.
            logger.debug("Connection between %s and %s does not exist. Creating a new one.".formatted(user1.getUsername(), user2.getUsername()));
            connection = this.createConnection(user1, user2);
            connections.add(connection);
        }
        return connection;
    }

    @Override
    public Connection createConnection(User user1, User user2) {
        logger.debug("New Connection created between %s and %s".formatted(user1.getUsername(), user2.getUsername()));
        var userPair = Pair.of(user1, user2);
        return new Connection(userPair);
    }

    @Override
    public List<Connection> getAllConnections(User user) {
        var connections = new ArrayList<Connection>();
        for (var connection : this.connections) {
            var users = connection.getEndUsers();
            if (users.getFirst().getUsername().equals(user.getUsername()) || users.getSecond().getUsername().equals(user.getUsername())) {
                connections.add(connection);
            }
        }
        return connections;
    }
}
