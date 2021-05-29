package com.popov.course_work.service;

import com.popov.course_work.entity.Errors;
import com.popov.course_work.repo.ErrorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ErrorsService {
    /**
     * Модуль сервиса сущности Ошибки(ErrorsService)
     * Здесь реализованы обращения к стандартным запросам сервера
     */
    @Autowired
    private ErrorsRepo errorsRepo;

    public Errors create(Errors errors){return errorsRepo.save(errors);}

    public Errors update(Errors errors){return errorsRepo.save(errors);}

    public void delete(Errors errors){errorsRepo.delete(errors);}

    public List<Errors> findAll(){return errorsRepo.findAll();}

    public Optional<Errors> find(Long id){return errorsRepo.findById(id);}
}
