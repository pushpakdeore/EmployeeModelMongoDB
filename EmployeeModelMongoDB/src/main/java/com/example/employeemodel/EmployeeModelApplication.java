package com.example.employeemodel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class EmployeeModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeModelApplication.class, args);
		log.error("Error");
		log.info("info");
		log.debug("debug");
	}

}
