package com.popov.course_work.service;

import com.popov.course_work.entity.Errors;
import com.popov.course_work.repo.ErrorsRepo;
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
class ErrorsServiceTest {

    @Autowired
    ErrorsService errorsService;

    @MockBean
    ErrorsRepo errorsRepo;

    Errors errors = new Errors();
    Errors errors2 = new Errors();

    @BeforeEach
    void setUp() {
        errors.setError_code(Long.valueOf(555));
        errors.setDescription("test description");
        errors2.setError_code(Long.valueOf(444));
        errors2.setDescription("araarararar");
    }

    @Test
    void create() {
        doReturn(errors).when(errorsRepo).save(errors);
        Errors returnValue = errorsService.create(errors);
        Assertions.assertNotNull(returnValue, "must be not null");
    }

    @Test
    void update() {
        errors2.setDescription("brbrbrbrbr");
        doReturn(errors2).when(errorsRepo).save(errors2);
        Errors returnValue = errorsService.update(errors2);
        Assertions.assertNotNull(returnValue, "must be not null");
        Assertions.assertSame(errors2, returnValue, "must be same");

    }

    @Test
    void findAll() {
        doReturn(Arrays.asList(errors, errors2)).when(errorsRepo).findAll();
        List<Errors> errorsList = errorsService.findAll();
        Assertions.assertEquals(2, errorsList.size(), "must be 2");
    }

    @Test
    void find() {
        doReturn(Optional.of(errors)).when(errorsRepo).findById(Long.valueOf(555));
        Optional<Errors> returnValue = errorsService.find(Long.valueOf(555));
        Assertions.assertSame(errors, returnValue.get(), "must be same");

    }
}