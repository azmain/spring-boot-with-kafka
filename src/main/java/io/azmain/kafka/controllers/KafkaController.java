package io.azmain.kafka.controllers;


import io.azmain.kafka.services.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity sendMessageToKafkaTopic(@PathVariable("message") final String message) {
        return ResponseEntity.ok(this.producer.sendMessage(message));
    }
}
