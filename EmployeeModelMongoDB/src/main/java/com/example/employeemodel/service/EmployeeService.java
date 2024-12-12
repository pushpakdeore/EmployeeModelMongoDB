package com.example.employeemodel.service;

import com.example.employeemodel.dto.RequestDTO;

import java.util.List;

public interface EmployeeService {
    RequestDTO addEmployee(RequestDTO employee);

    RequestDTO getEmployeeById(String id);

    void deleteEmployeeById(String id);

    List<RequestDTO> getAllEmployee();

    RequestDTO updateEmployeeById(String id, RequestDTO employee);
}


