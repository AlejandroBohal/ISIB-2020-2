package edu.eci.springboot.chat.controllers;

import edu.eci.springboot.chat.models.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import edu.eci.springboot.chat.models.documents.Message;

import java.util.Date;
import java.util.Random;

@Controller
public class AlertController {

    @Autowired
    private SimpMessagingTemplate webSocket;

    @MessageMapping("/data")
    @SendTo("/kafka/data")
    public String kafkaAlert(String msg) {
        System.out.println(msg);
        return null;
    }
}
