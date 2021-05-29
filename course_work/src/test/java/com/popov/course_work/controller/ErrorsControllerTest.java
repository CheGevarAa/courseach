package com.popov.course_work.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.popov.course_work.entity.Errors;
import com.popov.course_work.service.ErrorsService;
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
class ErrorsControllerTest {

    @MockBean
    ErrorsService errorsService;

    @Autowired
    private MockMvc mockMvc;

    Errors errors = new Errors();
    Errors errors2 =new Errors();

    @BeforeEach
    void setUp() {
        errors.setError_code(Long.valueOf(123));
        errors.setDescription("ererer");
        errors2.setError_code(Long.valueOf(222));
        errors2.setDescription("tretete");
    }

    @Test
    void create() throws Exception {
        doReturn(errors).when(errorsService).create(any());
        mockMvc.perform(post("/api/errors").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(errors))).andExpect(status().is(201));
    }

    @Test
    void findALL() throws Exception{
        doReturn(Lists.newArrayList(errors, errors2)).when(errorsService).findAll();
        mockMvc.perform(get("/api/errors")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].error_code", is(123)))
                .andExpect(jsonPath("$[0].description", is("ererer")))
                .andExpect(jsonPath("$[1].error_code", is(222)))
                .andExpect(jsonPath("$[1].description", is("tretete")));

    }

    @Test
    void find() throws Exception{
        doReturn(Optional.of(errors)).when(errorsService).find(Long.valueOf(123));
        mockMvc.perform(get("/api/errors/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error_code", is(123)))
                .andExpect(jsonPath("$.description", is("ererer")));
    }

    @Test
    void updateErrors() throws Exception{
        Errors test = errors;
        test.setDescription("lalalallaal");
        doReturn(Optional.of(test)).when(errorsService).find(Long.valueOf(123));
        doReturn(test).when(errorsService).update(test);
        mockMvc.perform(put("/api/errors/{id}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.IF_MATCH, 2)
                .content(asJsonString(test)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error_code", is(123)))
                .andExpect(jsonPath("$.description", is("lalalallaal")));
    }

    @Test
    void deleteError() throws Exception{
        doReturn(Optional.of(errors)).when(errorsService).find(Long.valueOf(123));
        doNothing().when(errorsService).delete(errors);
        mockMvc.perform(delete("/api/errors/{id}", 123))
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