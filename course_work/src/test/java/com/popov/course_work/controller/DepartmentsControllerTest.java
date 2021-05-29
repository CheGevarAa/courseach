package com.popov.course_work.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.popov.course_work.entity.Departments;
import com.popov.course_work.service.DepartmentsService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentsControllerTest {

    @MockBean
    DepartmentsService departmentsService;

    @Autowired
    private MockMvc mockMvc;

    Departments departments = new Departments();
    Departments departments2 =new Departments();

    @BeforeEach
    void setUp() {
        departments.setId(Long.valueOf(123));
        departments.setDepartment_name("ererer");
        departments2.setId(Long.valueOf(222));
        departments2.setDepartment_name("tretete");
    }

    @Test
    void create() throws Exception {
        doReturn(departments).when(departmentsService).create(any());
        mockMvc.perform(post("/api/departments").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(departments))).andExpect(status().is(201));
    }

    @Test
    void findALL() throws Exception{
        doReturn(Lists.newArrayList(departments, departments2)).when(departmentsService).findAll();
        mockMvc.perform(get("/api/departments")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(123)))
                .andExpect(jsonPath("$[0].department_name", is("ererer")))
                .andExpect(jsonPath("$[1].id", is(222)))
                .andExpect(jsonPath("$[1].department_name", is("tretete")));

    }

    @Test
    void find() throws Exception{
        doReturn(Optional.of(departments)).when(departmentsService).find(Long.valueOf(123));
        mockMvc.perform(get("/api/departments/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.department_name", is("ererer")));
    }

    @Test
    void updateDepartments() throws Exception{
        Departments test = departments;
        test.setDepartment_name("lalalallaal");
        doReturn(Optional.of(test)).when(departmentsService).find(Long.valueOf(123));
        doReturn(test).when(departmentsService).update(test);
        mockMvc.perform(put("/api/departments/{id}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.IF_MATCH, 2)
                .content(asJsonString(test)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.department_name", is("lalalallaal")));
    }

    @Test
    void deleteDepartment() throws Exception{
        doReturn(Optional.of(departments)).when(departmentsService).find(Long.valueOf(123));
        doNothing().when(departmentsService).delete(departments);
        mockMvc.perform(delete("/api/departments/{id}", 123))
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