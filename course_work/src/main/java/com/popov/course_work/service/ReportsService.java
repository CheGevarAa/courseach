package com.popov.course_work.service;

import com.popov.course_work.entity.Reports;
import com.popov.course_work.repo.ReportsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportsService {
    @Autowired
    private ReportsRepo reportsRepo;

    public void create(Reports reports){reportsRepo.save(reports);}

    public void update(Reports reports){reportsRepo.save(reports);}

    public void delete(Reports reports){reportsRepo.delete(reports);}

    public List<Reports> findAll(){return reportsRepo.findAll();}

    public Optional<Reports> find(Long id){return reportsRepo.findById(id);}
}
