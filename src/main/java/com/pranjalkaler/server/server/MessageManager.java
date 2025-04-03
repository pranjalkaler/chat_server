package com.pranjalkaler.server.server;

import com.pranjalkaler.server.models.Message;
import com.pranjalkaler.server.models.User;

import java.util.List;

public interface MessageManager {

    void sendMessage(User sender, User receiver, Message message);

    List<Message> readMessages(User reader);

}
