package io.azmain.kafka.services;

import io.azmain.kafka.models.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(
            topics = "${io.azmain.kafka.topic.name}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<?,?> record) {
        logger.info("Consumed -> '{}'", record.key().toString());
        //logger.info("#### -> Consumed message -> '{}'", record.key()+"="+record.value().toString());
//        logger.info(String.format("#### -> Consumed message -> %s", record.value().toString()));

        try{
            User user = (User) record.value();
            logger.info(user.getName());
            logger.info(user.getRoles().toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
