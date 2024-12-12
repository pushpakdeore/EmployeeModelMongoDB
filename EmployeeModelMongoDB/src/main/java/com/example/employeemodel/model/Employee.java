package com.example.employeemodel.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "employees1")
public class Employee {
    @Id
    private String empId;
    private String fName;
    private String lName;
    private int age;
    private double salary;
    private String profilePic;
    private List<String> departments;
    private LocalDate doj;
    private String gender;
    private String notes;
    private String email;

    @DBRef(lazy = true)
    private List<Address> address;
}