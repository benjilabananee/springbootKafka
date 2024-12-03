package com.app.mongo.service;

import com.app.mongo.entities.StudentMongo;
import com.app.mongo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveData(StudentMongo studentMongo) {
        studentRepository.save(studentMongo);
    }
}