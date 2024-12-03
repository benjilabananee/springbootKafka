package com.app.kafka.entities;

public class Student {
    private String id;
    private String name;
    private int age;
    private int salary;

    public Student() {}

    public Student(String id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public int getSalary() {return salary;}
    public void setSalary(int salary) {this.salary = salary;}
}
