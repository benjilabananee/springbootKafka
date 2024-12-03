package com.app.kafka.service.listener;

import com.app.kafka.entities.Student;
import com.app.mongo.entities.StudentMongo;
import com.app.mongo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class StudentConsumer {

    private final StudentService studentService;
    private final ObjectMapper objectMapper;



    public StudentConsumer(StudentService studentService,  ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "testForKafka", groupId = "student-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Student student) {

//        StudentMongo studentMongo = new StudentMongo();
//        studentMongo.setId(student.getId());
//        studentMongo.setAge(student.getAge());
//        studentMongo.setName(student.getName());

        StudentMongo studentMongo = objectMapper.convertValue(student, StudentMongo.class);

        studentService.saveData(studentMongo);

        System.out.println("Received student: " + student.getId() + " : " + student.getName());
    }
}
