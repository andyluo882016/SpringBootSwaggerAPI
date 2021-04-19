package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
 
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest
class HomeControllerTest {

	@Autowired
    private MockMvc mockMvc;
 
    @MockBean
    EmployeeService employeeService;
    
    private static ObjectMapper mapper = new ObjectMapper();
    
    @Test
    public void testGetExample() throws Exception {
        List<Employee> employees = new LinkedList<>();
        Employee employee = new Employee();
        employee.setId(200);
        employee.setName("Peter Oliver");
        employee.setSalary(27653.98); 
        employees.add(employee);
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(get("/swagger-app/getall")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Peter Oliver")));
    }
    
    @Test
    public void testPostExample() throws Exception {
         Employee employee = new Employee();
        employee.setId(300);
        employee.setName("Peter Oliver");
        employee.setSalary(27653.98); 
        Mockito.when(employeeService.SaveOneEmployee(ArgumentMatchers.any())).thenReturn(employee);
        String json = mapper.writeValueAsString(employee);
        mockMvc.perform(post("/swagger-app/save").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(300)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Peter Oliver")))
                .andExpect(jsonPath("$.salary", Matchers.equalTo(27653.98)));
    }
    
    @Test
    public void testPutExample() throws Exception {
       Employee employee = new Employee();
        employee .setId(100);
        employee .setName("Steven Smith");
        employee.setSalary(227653.98); 
        Mockito.when(employeeService.updatedOneEmployee(ArgumentMatchers.any())).thenReturn(employee);
        String json = mapper.writeValueAsString(employee);
        mockMvc.perform(put("/swagger-app/update").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(100)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Steven Smith")))
                 .andExpect(jsonPath("$.salary", Matchers.equalTo(227653.98)));
    }
    
    @Test
    public void testDeleteExample() throws Exception {
        Mockito.when(employeeService.deleteEmployee(ArgumentMatchers.anyInt())).thenReturn("employee has been deleted");
        MvcResult requestResult = mockMvc.perform(delete("/swagger-app/delete").param("id", "100"))
                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "employee has been deleted");
    }
}
