package io.azmain.kafka.services;

import io.azmain.kafka.models.Role;
import io.azmain.kafka.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Value("${io.azmain.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Producer(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        Role role1 = new Role(1L,"Admin");
        Role role2 = new Role(2L, "Manager");
        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        User user = new User(message, "20");
        user.setRoles(roles);

        this.kafkaTemplate.send(topicName,"Key", user);
        return "Produced successfully";
    }

}
