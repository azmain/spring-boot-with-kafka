package io.azmain.kafka.controllers;


import io.azmain.kafka.services.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish/{message}")
    public void sendMessageToKafkaTopic(@PathVariable("message") final String message) {
        this.producer.sendMessage(message);
    }
}
