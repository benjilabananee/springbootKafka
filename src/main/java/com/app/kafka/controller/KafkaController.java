package com.app.kafka.controller;

import com.app.kafka.entities.Student;
import com.app.kafka.service.producer.KafkaSender;
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
        kafkaSender.sendMessage(new Student("wdadwa", "wadwadwd", 2,100000));
        return "Message was successfully sent to Kafka!";
    }
}