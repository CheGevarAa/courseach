package com.popov.course_work.controller;

import com.popov.course_work.entity.Reports;
import com.popov.course_work.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReportsController {
    private final ReportsService reportsService;

    @Autowired
    public ReportsController(ReportsService reportsService){
        this.reportsService = reportsService;
    }
    @PostMapping("/api/reports")
    public ResponseEntity<?>create(@RequestBody Reports reports){
        reportsService.create(reports);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/reports")
    public ResponseEntity<List<Reports>> findALL(){
        final List<Reports> reportsList = reportsService.findAll();
        return reportsList != null && !reportsList.isEmpty()
                ? new ResponseEntity<>(reportsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/reports/{id}")
    public ResponseEntity<Optional<Reports>> find(@PathVariable(name = "id")Long id){
        final Optional<Reports> reports = reportsService.find(id);
        return reports!=null
                ? new ResponseEntity<>(reports, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/reports/{id}")
    public ResponseEntity<?> updateReports(@PathVariable(name = "id")Long id, @RequestBody Reports reportsUpdate){
        return reportsService.find(id).map(reports -> {
            reports.setCreation_date(reportsUpdate.getCreation_date());
            reports.setEmployee(reportsUpdate.getEmployee());
            reports.setDepartment_id(reportsUpdate.getDepartment_id());
            reports.setDangerLevel(reportsUpdate.getDangerLevel());
            reports.setErrors(reportsUpdate.getErrors());
            reportsService.update(reports);
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
