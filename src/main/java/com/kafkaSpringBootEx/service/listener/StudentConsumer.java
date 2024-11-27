package com.kafkaSpringBootEx.service.listener;

import com.kafkaSpringBootEx.entities.Student;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StudentConsumer {

    @KafkaListener(topics = "testForKafka", groupId = "student-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Student student) {
        System.out.println("Received student: " + student.getId() + " : " + student.getName());
        // Process the Student object as needed
    }
}
