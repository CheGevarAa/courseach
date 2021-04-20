package com.popov.course_work.service;

import com.popov.course_work.entity.Employees;
import com.popov.course_work.repo.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepo employeesRepo;

    public void create(Employees employees){employeesRepo.save(employees);}

    public void update(Employees employees){employeesRepo.save(employees);}

    public void delete(Employees employees){employeesRepo.delete(employees);}

    public List<Employees> findAll(){return employeesRepo.findAll();}

    public Optional<Employees> find(Long id){return employeesRepo.findById(id);}

    public Optional<Employees> findByUsername(String username){ return employeesRepo.findByUsername(username);}
}
