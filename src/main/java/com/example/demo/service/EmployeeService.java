package com.example.demo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.model.*;
@Service
@Scope("singleton")
public class EmployeeService {

	List<Employee> elist=new LinkedList<>();
	{
		elist.add(new Employee(100, "Keven Smith", 28907.21));
		elist.add(new Employee(101, "Julie Park", 87690.27));
		elist.add(new Employee(102, "Steven Garber", 110987.87));
		elist.add(new Employee(103, "Hallen Oliver", 367531.98));
		elist.add(new Employee(104, "Alexi Fenklin", 38769.09));
	};
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	public List<Employee> getAllEmployees() {
		return elist;
	}
	
	public Employee getOneEmployeeById(Integer id) {
		
		 Optional<Employee> opt=elist.stream().filter(ep ->ep.getId().equals(id)).findFirst();
		 return opt.isPresent() ? opt.get() : new Employee(id, "employee-not-exsit", null);
	}
	
	public Employee SaveOneEmployee(Employee employee) {
		
		   elist.add(employee);
		   return employee;
	}
	
	public Employee updatedOneEmployee(Employee employee) {
		
		Integer pid =employee.getId();
		
		for(Employee emp : elist) {
			if (emp.getId().equals(pid)) {
				int index=elist.indexOf(emp);
				elist.remove(emp);
				elist.add(index, employee);
				break;
				
			}
		}
		
		return employee;
	}
	
	
	
	public String deleteEmployee(Integer id) {
		Employee result=new Employee();
		Iterator<Employee> iter =elist.iterator();
		while(iter.hasNext()) {
			Employee empl=iter.next();
			if (empl.getId().equals(id)) {
				result=empl;
				elist.remove(empl);
				
				break;
			}
		}
		
		return "employee has been deleted";
	}
}
