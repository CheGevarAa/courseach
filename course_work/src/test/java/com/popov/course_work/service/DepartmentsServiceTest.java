package com.popov.course_work.service;

import com.popov.course_work.entity.Departments;
import com.popov.course_work.repo.DepartmentsRepo;
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
class DepartmentsServiceTest {

    @Autowired
    DepartmentsService departmentsService;

    @MockBean
    DepartmentsRepo departmentsRepo;

    Departments departments = new Departments();
    Departments departments2 = new Departments();

    @BeforeEach
    void setUp() {
        departments.setId(Long.valueOf(555));
        departments.setDepartment_name("test description");
        departments2.setId(Long.valueOf(444));
        departments2.setDepartment_name("araarararar");
    }

    @Test
    void create() {
        doReturn(departments).when(departmentsRepo).save(departments);
        Departments returnValue = departmentsService.create(departments);
        Assertions.assertNotNull(returnValue, "must be not null");
    }

    @Test
    void update() {
        departments2.setDepartment_name("brbrbrbrbr");
        doReturn(departments2).when(departmentsRepo).save(departments2);
        Departments returnValue = departmentsService.update(departments2);
        Assertions.assertNotNull(returnValue, "must be not null");
        Assertions.assertSame(departments2, returnValue, "must be same");

    }

    @Test
    void findAll() {
        doReturn(Arrays.asList(departments, departments2)).when(departmentsRepo).findAll();
        List<Departments> departmentsList = departmentsService.findAll();
        Assertions.assertEquals(2, departmentsList.size(), "must be 2");
    }

    @Test
    void find() {
        doReturn(Optional.of(departments)).when(departmentsRepo).findById(Long.valueOf(555));
        Optional<Departments> returnValue = departmentsService.find(Long.valueOf(555));
        Assertions.assertSame(departments, returnValue.get(), "must be same");

    }
}