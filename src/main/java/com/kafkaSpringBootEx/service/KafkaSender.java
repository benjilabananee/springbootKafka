package com.kafkaSpringBootEx.service;

import com.kafkaSpringBootEx.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    private static final String TOPIC = "testForKafka";

    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;

    public void sendMessage(Student student) {
        kafkaTemplate.send(TOPIC, student);
        System.out.println("Message sent: " + student);
    }
}
