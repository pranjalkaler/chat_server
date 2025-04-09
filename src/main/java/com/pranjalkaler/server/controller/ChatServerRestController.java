package com.pranjalkaler.server.controller;


import com.pranjalkaler.server.models.Message;
import com.pranjalkaler.server.models.RequestMessage;
import com.pranjalkaler.server.messageManager.MessageManager;
import com.pranjalkaler.server.repository.DBRepository;
import com.pranjalkaler.server.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pranjalkaler.server.utils.UtilityTools.createMessage;
import static com.pranjalkaler.server.utils.UtilityTools.createUser;

@RestController
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@RequestMapping("/api/v1/chat-server")
public class ChatServerRestController {

    private final MessageManager messageManager;
    private final Logger logger;
    private final DBRepository dbRepository;

    @Autowired
    public ChatServerRestController(MessageManager messageManager, DBRepository dbRepository) {
        this.messageManager = messageManager;
        this.dbRepository = dbRepository;
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
        logger.debug("DB TEST LOG: " + dbRepository.testDB());
        var receiver = createUser(username, email);
        var messages = messageManager.readMessages(receiver);
        logger.debug(messages.toString());
        return messages;
    }
}
