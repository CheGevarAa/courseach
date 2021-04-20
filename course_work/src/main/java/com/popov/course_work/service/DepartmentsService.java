package com.popov.course_work.service;

import com.popov.course_work.entity.Departments;
import com.popov.course_work.repo.DepartmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsService {
    @Autowired
    private DepartmentsRepo departmentsRepo;

    public void create(Departments departments){departmentsRepo.save(departments);}

    public void update(Departments departments){departmentsRepo.save(departments);}

    public void delete(Departments departments){departmentsRepo.delete(departments);}

    public List<Departments> findAll(){return departmentsRepo.findAll();}

    public Optional<Departments> find(Long id){return departmentsRepo.findById(id);}
}
