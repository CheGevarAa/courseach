package com.popov.course_work.service;

import com.popov.course_work.entity.DangerLevels;
import com.popov.course_work.repo.DangerLevelsRepo;
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
class DangerLevelsServiceTest {

    @Autowired
    DangerLevelsService dangerLevelsService;

    @MockBean
    DangerLevelsRepo dangerLevelsRepo;

    DangerLevels dangerLevels = new DangerLevels();
    DangerLevels dangerLevels2 = new DangerLevels();

    @BeforeEach
    void setUp() {
        dangerLevels.setId(Long.valueOf(555));
        dangerLevels.setDescription("test description");
        dangerLevels2.setId(Long.valueOf(444));
        dangerLevels2.setDescription("araarararar");
    }

    @Test
    void create() {
        doReturn(dangerLevels).when(dangerLevelsRepo).save(dangerLevels);
        DangerLevels returnValue = dangerLevelsService.create(dangerLevels);
        Assertions.assertNotNull(returnValue, "must be not null");
    }

    @Test
    void update() {
        dangerLevels2.setDescription("brbrbrbrbr");
        doReturn(dangerLevels2).when(dangerLevelsRepo).save(dangerLevels2);
        DangerLevels returnValue = dangerLevelsService.update(dangerLevels2);
        Assertions.assertNotNull(returnValue, "must be not null");
        Assertions.assertSame(dangerLevels2, returnValue, "must be same");

    }

    @Test
    void findAll() {
        doReturn(Arrays.asList(dangerLevels, dangerLevels2)).when(dangerLevelsRepo).findAll();
        List<DangerLevels> dangerLevelsList = dangerLevelsService.findAll();
        Assertions.assertEquals(2, dangerLevelsList.size(), "must be 2");
    }

    @Test
    void find() {
        doReturn(Optional.of(dangerLevels)).when(dangerLevelsRepo).findById(Long.valueOf(555));
        Optional<DangerLevels> returnValue = dangerLevelsService.find(Long.valueOf(555));
        Assertions.assertSame(dangerLevels, returnValue.get(), "must be same");

    }
}