package com.pranjalkaler.chat_server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller

@RestController
@RequestMapping("/api/v1/chat-server")
public class ChatServerRestController {

    @Autowired
    public ChatServerRestController() {

    }

    @RequestMapping(path = "/post", method = RequestMethod.POST)
    public String get() {
        return "received a POST Request";
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String post(@RequestParam(name = "name") String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("envName or serviceName cannot be null");
        }
        return "GET" + name;
    }
}
