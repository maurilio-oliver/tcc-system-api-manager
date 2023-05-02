package br.unip.tcc.tccapi.controller;

import jakarta.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RequestMapping("test")
@RestController
@Service
public class Test {
    @Autowired
    JmsTemplate jmsTemplate;

    @Async
    @GetMapping
    public void a( @RequestParam String o){
        jmsTemplate.send("maurilio", session -> {
            TextMessage textMessage = session.createTextMessage(o);
            return textMessage;
        });

    }

    @Async
    @JmsListener(destination = "maurilio", concurrency = "1")
     void receivemessage(String o){
        Logger.getAnonymousLogger().info(o);

    }


}
