package com.pranjalkaler.chat_server.server;

import com.pranjalkaler.chat_server.models.Message;
import com.pranjalkaler.chat_server.models.User;

import java.util.List;

public interface MessageManager {

    void sendMessage(User sender, User receiver, Message message);

    List<Message> readMessages(User reader);

}
