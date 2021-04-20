package com.popov.course_work.service;

import com.popov.course_work.entity.Errors;
import com.popov.course_work.repo.ErrorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ErrorsService {
    
    @Autowired
    private ErrorsRepo errorsRepo;

    public void create(Errors errors){errorsRepo.save(errors);}

    public void update(Errors errors){errorsRepo.save(errors);}

    public void delete(Errors errors){errorsRepo.delete(errors);}

    public List<Errors> findAll(){return errorsRepo.findAll();}

    public Optional<Errors> find(Long id){return errorsRepo.findById(id);}
}
