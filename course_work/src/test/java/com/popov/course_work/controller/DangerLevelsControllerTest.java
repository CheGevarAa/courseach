package com.popov.course_work.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popov.course_work.entity.DangerLevels;
import com.popov.course_work.service.DangerLevelsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.common.collect.Lists;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DangerLevelsControllerTest {

    @MockBean
    DangerLevelsService dangerLevelsService;

    @Autowired
    private MockMvc mockMvc;

    DangerLevels dangerLevels = new DangerLevels();
    DangerLevels dangerLevels2 =new DangerLevels();

    @BeforeEach
    void setUp() {
        dangerLevels.setId(Long.valueOf(123));
        dangerLevels.setDescription("ererer");
        dangerLevels2.setId(Long.valueOf(222));
        dangerLevels2.setDescription("tretete");
    }

    @Test
    void create() throws Exception {
        doReturn(dangerLevels).when(dangerLevelsService).create(any());
        mockMvc.perform(post("/api/dangerLevels").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dangerLevels))).andExpect(status().is(201));
    }

    @Test
    void findALL() throws Exception{
        doReturn(Lists.newArrayList(dangerLevels, dangerLevels2)).when(dangerLevelsService).findAll();
        mockMvc.perform(get("/api/dangerLevels")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(123)))
                .andExpect(jsonPath("$[0].description", is("ererer")))
                .andExpect(jsonPath("$[1].id", is(222)))
                .andExpect(jsonPath("$[1].description", is("tretete")));

    }

    @Test
    void find() throws Exception{
        doReturn(Optional.of(dangerLevels)).when(dangerLevelsService).find(Long.valueOf(123));
        mockMvc.perform(get("/api/dangerLevels/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.description", is("ererer")));
    }

    @Test
    void updateDangerLevels() throws Exception{
        DangerLevels test = dangerLevels;
        test.setDescription("lalalallaal");
        doReturn(Optional.of(test)).when(dangerLevelsService).find(Long.valueOf(123));
        doReturn(test).when(dangerLevelsService).update(test);
        mockMvc.perform(put("/api/dangerLevels/{id}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.IF_MATCH, 2)
                .content(asJsonString(test)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.description", is("lalalallaal")));
    }

    @Test
    void deleteDangerLevel() throws Exception{
        doReturn(Optional.of(dangerLevels)).when(dangerLevelsService).find(Long.valueOf(123));
        doNothing().when(dangerLevelsService).delete(dangerLevels);
        mockMvc.perform(delete("/api/dangerLevels/{id}", 123))
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