package com.pranjalkaler.server.repository;

import com.pranjalkaler.server.models.Message;
import com.pranjalkaler.server.models.User;

import java.util.List;

public interface DBRepository {

    Integer testDB();

    void storeMessage(Message message);

    void addUser();

    User getUser(String username);

    List<Message> getAllMessageS();

    List<Message> getMessagesForUser();
}
