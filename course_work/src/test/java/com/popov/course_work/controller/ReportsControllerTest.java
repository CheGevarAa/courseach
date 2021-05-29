package com.popov.course_work.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.popov.course_work.entity.*;
import com.popov.course_work.service.ReportsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReportsControllerTest {

    @MockBean
    ReportsService reportsService;

    @Autowired
    private MockMvc mockMvc;

    Reports reports = new Reports();
    Reports reports2 = new Reports();
    Departments departments = new Departments();
    DangerLevels dangerLevels = new DangerLevels();
    Errors errors = new Errors();
    Employees employees = new Employees();

    @BeforeEach
    void setUp() {
        departments.setId(Long.valueOf(1));
        departments.setDepartment_name("qwqqwqqwqww");

        dangerLevels.setId(Long.valueOf(1));
        dangerLevels.setDescription("21222e2wdcdd");

        errors.setError_code(Long.valueOf(1));
        errors.setDescription("jdifjrigr");

        employees.setId(Long.valueOf(1));
        employees.setUsername("efbrjggirgnjignjttighjtirhgruhfyeiriy");

        reports.setId(Long.valueOf(1));
        reports.setDangerLevel(dangerLevels);
        reports.setEmployee(employees);
        reports.setErrors(errors);
        reports2.setId(Long.valueOf(2));
        reports2.setDangerLevel(dangerLevels);
        reports2.setEmployee(employees);
        reports2.setErrors(errors);

    }

    @Test
    void create() throws Exception{
        doReturn(reports).when(reportsService).create(any());
        mockMvc.perform(post("/api/reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reports)))
                .andExpect(status().is(201));
    }

    @Test
    void findALL() throws Exception{
        doReturn(Lists.newArrayList(reports, reports2)).when(reportsService).findAll();
        mockMvc.perform(get("/api/reports"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].employee.username", is("efbrjggirgnjignjttighjtirhgruhfyeiriy")))
                .andExpect(jsonPath("$[0].errors.error_code", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].employee.username", is("efbrjggirgnjignjttighjtirhgruhfyeiriy")))
                .andExpect(jsonPath("$[1].errors.error_code", is(1)));
    }

    @Test
    void find() throws Exception{
        doReturn(Optional.of(reports)).when(reportsService).find(Long.valueOf(1));
        mockMvc.perform(get("/api/reports/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.employee.username", is("efbrjggirgnjignjttighjtirhgruhfyeiriy")))
                .andExpect(jsonPath("$.errors.error_code", is(1)));
    }

    @Test
    void findByUser() throws Exception{
        doReturn(Lists.newArrayList(reports, reports2)).when(reportsService).findByUser(Long.valueOf(1));
        mockMvc.perform(get("/api/reports/by_user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].employee.username", is("efbrjggirgnjignjttighjtirhgruhfyeiriy")))
                .andExpect(jsonPath("$[0].errors.error_code", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].employee.username", is("efbrjggirgnjignjttighjtirhgruhfyeiriy")))
                .andExpect(jsonPath("$[1].errors.error_code", is(1)));
    }

    @Test
    void updateReports() throws Exception{
        Reports test = reports;
        test.getEmployee().setUsername("wewewew");
        doReturn(Optional.of(test)).when(reportsService).find(Long.valueOf(1));
        mockMvc.perform(get("/api/reports/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.employee.username", is("wewewew")))
                .andExpect(jsonPath("$.errors.error_code", is(1)));
    }

    @Test
    void deleteReport() throws Exception{
        doReturn(Optional.of(reports)).when(reportsService).find(Long.valueOf(1));
        doNothing().when(reportsService).delete(reports);
        mockMvc.perform(delete("/api/reports/{id}", 1))
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