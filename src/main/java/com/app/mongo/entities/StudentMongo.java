package com.app.mongo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StudentMongo {

    @Id
    private String id;
    private String name;
    private int age;
    private Integer salary;

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public Integer getSalary() {return salary;}
    public void setSalary(Integer salary) {this.salary = salary;}
}
