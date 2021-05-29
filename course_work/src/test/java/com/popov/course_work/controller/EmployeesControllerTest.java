package com.popov.course_work.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.popov.course_work.entity.Departments;
import com.popov.course_work.entity.Employees;
import com.popov.course_work.service.EmployeesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeesControllerTest {

    @MockBean
    EmployeesService employeesService;

    @Autowired
    private MockMvc mockMvc;

    Employees employees = new Employees();
    Employees employees2 = new Employees();
    Departments departments = new Departments();


    @BeforeEach
    void setUp() {
        employees.setId(Long.valueOf(1));
        employees.setUsername("A");


        employees2.setId(Long.valueOf(2));
        employees2.setUsername("B");

        departments.setId(Long.valueOf(11));

        employees.setDepartment(departments);
        employees2.setDepartment(departments);
    }

    @Test
    void create() throws Exception{
        doReturn(employees).when(employeesService).create(any());
        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employees)))
                .andExpect(status().is(201));
    }

    @Test
    void findALL() throws Exception {
        doReturn(Lists.newArrayList(employees, employees2)).when(employeesService).findAll();
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("A")))
                .andExpect(jsonPath("$[0].department.id", is(11)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("B")))
                .andExpect(jsonPath("$[1].department.id", is(11)));

    }

    @Test
    void find() throws  Exception{
        doReturn(Optional.of(employees)).when(employeesService).find(Long.valueOf(1));
        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("A")))
                .andExpect(jsonPath("$.department.id", is(11)));
    }

    @Test
    void findByUsername() throws Exception{
        doReturn(Optional.of(employees)).when(employeesService).findByUsername("A");
        mockMvc.perform(get("/api/employees/usernames/A"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("A")))
                .andExpect(jsonPath("$.department.id", is(11)));
    }

    @Test
    void updateEmployees() throws Exception{
        Employees test = employees;
        test.setUsername("tttt");
        doReturn(Optional.of(employees)).when(employeesService).find(Long.valueOf(1));
        doReturn(test).when(employeesService).update(employees);
        mockMvc.perform(put("/api/employees/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.IF_MATCH, 2)
                .content(asJsonString(test)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("tttt")))
                .andExpect(jsonPath("$.department.id", is(11)));
    }

    @Test
    void deleteEmployee() throws Exception{
        doReturn(Optional.of(employees)).when(employeesService).find(Long.valueOf(1));
        doNothing().when(employeesService).delete(employees);
        mockMvc.perform(delete("/api/employees/{id}", 1))
                .andExpect(status().isOk());

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}