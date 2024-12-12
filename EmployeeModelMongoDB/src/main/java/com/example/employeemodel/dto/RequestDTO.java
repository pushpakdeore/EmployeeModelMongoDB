// RequestDTO.java
package com.example.employeemodel.dto;

import com.example.employeemodel.model.Address;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class RequestDTO {
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
    private List<Address> addresses;
    private String email;
}
