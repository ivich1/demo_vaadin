package com.example.demo.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class SimpleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private int number;//табельный номер
    private double salary;
    @Temporal(TemporalType.DATE)
    private Calendar hiringDate;


    public SimpleEntity(){

    }

    public SimpleEntity(String name, String surname, int number, double salary, Calendar hiringDate) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.salary = salary;
        this.hiringDate = hiringDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Calendar getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Calendar hiringDate) {
        this.hiringDate = hiringDate;
    }
}
