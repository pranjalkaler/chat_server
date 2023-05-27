package com.pranjalkaler.chat_server.controller;


import com.pranjalkaler.chat_server.models.Message;
import com.pranjalkaler.chat_server.models.RequestMessage;
import com.pranjalkaler.chat_server.server.MessageManager;
import com.pranjalkaler.chat_server.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pranjalkaler.chat_server.utils.UtilityTools.createMessage;
import static com.pranjalkaler.chat_server.utils.UtilityTools.createUser;

@RestController
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@RequestMapping("/api/v1/chat-server")
public class ChatServerRestController {

    private final MessageManager messageManager;
    private final Logger logger;

    @Autowired
    public ChatServerRestController(MessageManager messageManager) {
        this.messageManager = messageManager;
        logger = new Logger(ChatServerRestController.class);
    }

    @RequestMapping(path = "/message", method = RequestMethod.POST)
    public void sendMessage(@RequestBody RequestMessage requestMessage) {
        var sender = createUser(requestMessage.senderUsername, requestMessage.senderEmail);
        var receiver = createUser(requestMessage.receiverUsername, requestMessage.receiverEmail);
        var message = createMessage(sender, receiver, requestMessage.message, requestMessage.messageType);
        messageManager.sendMessage(sender, receiver, message);
    }

    @RequestMapping(path = "/messages", method = RequestMethod.GET)
    public List<Message> readMessages(@RequestParam String username, @RequestParam String email) {
        var receiver = createUser(username, email);
        var messages = messageManager.readMessages(receiver);
        logger.debug(messages.toString());
        return messages;
    }
}
