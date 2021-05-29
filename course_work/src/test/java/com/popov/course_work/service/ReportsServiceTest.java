package com.popov.course_work.service;

import com.popov.course_work.entity.*;
import com.popov.course_work.repo.ReportsRepo;
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
class ReportsServiceTest {

    @Autowired
    ReportsService reportsService;

    @MockBean
    ReportsRepo reportsRepo;

    Reports reports = new Reports();
    Reports reports2 = new Reports();
    Departments departments = new Departments();
    DangerLevels dangerLevels = new DangerLevels();
    Employees employees = new Employees();
    Errors errors = new Errors();

    @BeforeEach
    void setUp() {
        reports.setId(Long.valueOf(555));
        dangerLevels.setId(Long.valueOf(123));
        dangerLevels.setDescription("xxx");
        errors.setError_code(Long.valueOf(222));
        errors.setDescription("sss");
        employees.setId(Long.valueOf(100));
        employees.setUsername("Ere");

        reports2.setId(Long.valueOf(444));

        reports.setErrors(errors);
        reports.setDangerLevel(dangerLevels);
        reports.setEmployee(employees);

        reports2.setEmployee(employees);
        reports2.setDangerLevel(dangerLevels);
        reports2.setErrors(errors);
    }

    @Test
    void create() {
        doReturn(reports).when(reportsRepo).save(reports);
        Reports returnValue = reportsService.create(reports);
        Assertions.assertNotNull(returnValue, "must be not null");
    }

    @Test
    void update() {
        reports2.getEmployee().setUsername("weiufheuigiu");
        doReturn(reports2).when(reportsRepo).save(reports2);
        Reports returnValue = reportsService.update(reports2);
        Assertions.assertNotNull(returnValue, "must be not null");
        Assertions.assertSame(reports2, returnValue, "must be same");

    }

    @Test
    void findAll() {
        doReturn(Arrays.asList(reports, reports2)).when(reportsRepo).findAll();
        List<Reports> reportsList = reportsService.findAll();
        Assertions.assertEquals(2, reportsList.size(), "must be 2");
    }

    @Test
    void find() {
        doReturn(Optional.of(reports)).when(reportsRepo).findById(Long.valueOf(555));
        Optional<Reports> returnValue = reportsService.find(Long.valueOf(555));
        Assertions.assertSame(reports, returnValue.get(), "must be same");

    }

    @Test
    void findByUser() {
        doReturn(Arrays.asList(reports, reports2)).when(reportsRepo).findByEmployee_Id(Long.valueOf(555));
        List<Reports> returnValue = reportsService.findByUser(Long.valueOf(555));
        Assertions.assertEquals(2, returnValue.size(), "must be same");
    }
}