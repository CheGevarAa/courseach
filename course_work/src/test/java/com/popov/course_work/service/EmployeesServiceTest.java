package com.popov.course_work.service;

import com.popov.course_work.entity.Employees;
import com.popov.course_work.repo.EmployeesRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class EmployeesServiceTest {

    @Autowired
    EmployeesService employeesService;

    @MockBean
    EmployeesRepo employeesRepo;

    Employees employees = new Employees();
    Employees employees2 = new Employees();

    @BeforeEach
    void setUp() {
        employees.setId(Long.valueOf(555));
        employees.setUsername("Ivan");
        employees.setDepartment(null);
        employees2.setId(Long.valueOf(444));
        employees2.setUsername("Anton");
        employees2.setDepartment(null);
    }

    @Test
    void create() {
        doReturn(employees).when(employeesRepo).save(employees);
        Employees returnValue = employeesService.create(employees);
        Assertions.assertNotNull(returnValue, "must be not null");
    }

    @Test
    void update() {
        employees2.setUsername("brbrbrbrbr");
        doReturn(employees2).when(employeesRepo).save(employees2);
        Employees returnValue = employeesService.update(employees2);
        Assertions.assertNotNull(returnValue, "must be not null");
        Assertions.assertSame(employees2, returnValue, "must be same");

    }

    @Test
    void findAll() {
        doReturn(Arrays.asList(employees, employees2)).when(employeesRepo).findAll();
        List<Employees> employeesList = employeesService.findAll();
        Assertions.assertEquals(2, employeesList.size(), "must be 2");
    }

    @Test
    void find() {
        doReturn(Optional.of(employees)).when(employeesRepo).findById(Long.valueOf(555));
        Optional<Employees> returnValue = employeesService.find(Long.valueOf(555));
        Assertions.assertSame(employees, returnValue.get(), "must be same");

    }

    @Test
    void findByUsername() {
        doReturn(Optional.of(employees)).when(employeesRepo).findByUsername("Ivan");
        Optional<Employees> returnValue = employeesService.findByUsername("Ivan");
        Assertions.assertSame(employees, returnValue.get(), "must be same");
    }
}