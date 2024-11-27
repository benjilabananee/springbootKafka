package com.kafkaSpringBootEx.controller;

import com.kafkaSpringBootEx.entities.Student;
import com.kafkaSpringBootEx.service.producer.KafkaSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaSender kafkaSender;

    public KafkaController(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @PostMapping("/send")
    public String sendMessage() {
        kafkaSender.sendMessage(new Student("wdadwa", "wadwadwd", 1));
        return "Message was successfully sent to Kafka!";
    }
}