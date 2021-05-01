package com.popov.course_work.controller;

import com.popov.course_work.entity.Employees;
import com.popov.course_work.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeesController {
    /**
     * Класс контроллера Сотрудников(EmployeesController)
     * Данный модуль обеспечивает взаимодействе с объектами отчетов в БД и клиентским приложением,
     * оно обеспечивается при помощи методов поиска, обновления и создания нового объекта.
     * Сами эти методы реализованы с помощью аннотаций, указывающих на тип запроса к БД, а также
     * ссылок на таблицы, к которой метод должен подключиться.
     *
     *
     */
    private final EmployeesService employeesService;

    @Autowired
    public EmployeesController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }
    @PostMapping("/api/employees")
    public ResponseEntity<?>create(@RequestBody Employees employees){
        employeesService.create(employees);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/employees")
    public ResponseEntity<List<Employees>> findALL(){
        final List<Employees> employeesList = employeesService.findAll();
        return employeesList != null && !employeesList.isEmpty()
                ? new ResponseEntity<>(employeesList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/employees/{id}")
    public ResponseEntity<Optional<Employees>> find(@PathVariable(name = "id")Long id){
        final Optional<Employees> employees = employeesService.find(id);
        return employees!=null
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/employees/usernames/{username}")
    public ResponseEntity<Optional<Employees>> findByUsername(@PathVariable(name = "username") String username){
        final Optional<Employees> employees = employeesService.findByUsername(username);
        return employees!=null
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/employees/{id}")
    public ResponseEntity<?> updateEmployees(@PathVariable(name = "id")Long id, @RequestBody Employees employeesUpdate){
        return employeesService.find(id).map(employees -> {
            employees.setUsername(employeesUpdate.getUsername());
            employees.setPassword(employeesUpdate.getPassword());
            employees.setDepartment(employeesUpdate.getDepartment());
            employees.setOldPasswords(employeesUpdate.getOldPasswords());
            employees.setReportsList(employeesUpdate.getReportsList());
            employeesService.update(employees);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping("/api/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") Long id) {
        return employeesService.find(id).map(employees -> {
            employeesService.delete(employees);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
