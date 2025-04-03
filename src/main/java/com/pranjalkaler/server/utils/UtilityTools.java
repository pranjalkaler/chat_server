package com.pranjalkaler.server.utils;

import com.pranjalkaler.server.models.Message;
import com.pranjalkaler.server.models.User;

public class UtilityTools {

    private UtilityTools () {

    }

    public static User createUser(String username, String email) {
        return new User(username, email);
    }

    public static Message createMessage(User sender, User receiver, String message, String type) {
        return new Message(message, sender, receiver, MessageType.valueOf(type));
    }

}
