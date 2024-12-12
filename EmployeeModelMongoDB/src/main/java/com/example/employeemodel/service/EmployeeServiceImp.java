package com.example.employeemodel.service;

import com.example.employeemodel.dto.RequestDTO;
import com.example.employeemodel.exception.CustomException;
import com.example.employeemodel.model.Address;
import com.example.employeemodel.model.Employee;
import com.example.employeemodel.repository.AddressRepository;
import com.example.employeemodel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public RequestDTO addEmployee(RequestDTO requestDTO) {
        Employee employee = mapToEntity(requestDTO);

        List<Address> savedAddresses = new ArrayList<>();
        if (requestDTO.getAddresses() != null) {
            for (Address address : requestDTO.getAddresses()) {
                Address savedAddress = addressRepository.save(address);
                savedAddresses.add(savedAddress);
            }
        }
        employee.setAddress(savedAddresses);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToDTO(savedEmployee);
    }

    @Override
    public RequestDTO getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new CustomException("Employee not found with ID: " + id));
        return mapToDTO(employee);
    }

    @Override
    public void deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<RequestDTO> getAllEmployee() {
        return employeeRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public RequestDTO updateEmployeeById(String id, RequestDTO requestDTO) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new CustomException("Employee not found with ID: " + id));
        existingEmployee.setFName(requestDTO.getFName());
        existingEmployee.setLName(requestDTO.getLName());
        existingEmployee.setAge(requestDTO.getAge());
        existingEmployee.setSalary(requestDTO.getSalary());
        existingEmployee.setProfilePic(requestDTO.getProfilePic());
        existingEmployee.setDepartments(requestDTO.getDepartments());
        existingEmployee.setGender(requestDTO.getGender());
        existingEmployee.setNotes(requestDTO.getNotes());

        if (requestDTO.getAddresses() != null) {
            List<Address> updatedAddresses = new ArrayList<>();
            for (Address address : requestDTO.getAddresses()) {
                Address updatedAddress = addressRepository.save(address);
                updatedAddresses.add(updatedAddress);
            }
            existingEmployee.setAddress(updatedAddresses);
        }

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return mapToDTO(updatedEmployee);
    }

    private RequestDTO mapToDTO(Employee employee) {
        RequestDTO dto = new RequestDTO();
        dto.setEmpId(employee.getEmpId());
        dto.setFName(employee.getFName());
        dto.setLName(employee.getLName());
        dto.setAge(employee.getAge());
        dto.setSalary(employee.getSalary());
        dto.setProfilePic(employee.getProfilePic());
        dto.setDepartments(employee.getDepartments());
        dto.setDoj(employee.getDoj());
        dto.setGender(employee.getGender());
        dto.setNotes(employee.getNotes());
        dto.setAddresses(employee.getAddress());
        dto.setEmail(employee.getEmail());
        return dto;
    }

    private Employee mapToEntity(RequestDTO dto) {
        Employee employee = new Employee();
        employee.setEmpId(dto.getEmpId());
        employee.setFName(dto.getFName());
        employee.setLName(dto.getLName());
        employee.setAge(dto.getAge());
        employee.setSalary(dto.getSalary());
        employee.setProfilePic(dto.getProfilePic());
        employee.setDepartments(dto.getDepartments());
        employee.setDoj(LocalDate.now());
        employee.setGender(dto.getGender());
        employee.setNotes(dto.getNotes());
        employee.setEmail(dto.getEmail());
        return employee;
    }
}






