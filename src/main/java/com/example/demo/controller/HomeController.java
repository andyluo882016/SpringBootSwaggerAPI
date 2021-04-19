package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.*;
import com.example.demo.service.EmployeeService;

import java.util.*;

@RestController
@RequestMapping("/swagger-app")
public class HomeController {

	@Autowired
	EmployeeService employeeService;
	//ResponseEntity<List<Student>>
	@GetMapping(path="/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getallEmployees() {
		List<Employee> list= employeeService.getAllEmployees();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping(path="/find/{id}", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getoneById(@PathVariable("id") final Integer id) {
		Employee employee=employeeService.getOneEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@PostMapping(path="/save", consumes =MediaType.APPLICATION_JSON_VALUE , produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> saveOne(@RequestBody Employee employee) {
		     Employee msg =employeeService.SaveOneEmployee(employee);
		     return new ResponseEntity<>(msg, HttpStatus.CREATED);
		  
	}
	@PutMapping(path="/update", consumes =MediaType.APPLICATION_JSON_VALUE , produces =MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Employee> updatedOne(@RequestBody Employee employee) {
		Employee msg =employeeService.updatedOneEmployee(employee);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	@DeleteMapping(path="/delete", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteOneById(@RequestParam("id") final Integer id) {
		String msg=employeeService.deleteEmployee(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
