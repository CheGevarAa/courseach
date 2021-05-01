package com.popov.course_work.controller;

import com.popov.course_work.entity.Errors;
import com.popov.course_work.service.ErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ErrorsController {
    /**
     * Класс контроллера Ошибок(ErrorsController)
     * Данный модуль обеспечивает взаимодействе с объектами отчетов в БД и клиентским приложением,
     * оно обеспечивается при помощи методов поиска, обновления и создания нового объекта.
     * Сами эти методы реализованы с помощью аннотаций, указывающих на тип запроса к БД, а также
     * ссылок на таблицы, к которой метод должен подключиться.
     *
     *
     */
    private final ErrorsService errorsService;

    @Autowired
    public ErrorsController(ErrorsService errorsService){
        this.errorsService = errorsService;
    }
    @PostMapping("/api/errors")
    public ResponseEntity<?>create(@RequestBody Errors errors){
        errorsService.create(errors);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/errors")
    public ResponseEntity<List<Errors>> findALL(){
        final List<Errors> errorsList = errorsService.findAll();
        return errorsList != null && !errorsList.isEmpty()
                ? new ResponseEntity<>(errorsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/errors/{id}")
    public ResponseEntity<Optional<Errors>> find(@PathVariable(name = "id")Long id){
        final Optional<Errors> errors = errorsService.find(id);
        return errors!=null
                ? new ResponseEntity<>(errors, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/errors/{id}")
    public ResponseEntity<?> updateErrors(@PathVariable(name = "id")Long id, @RequestBody Errors errorsUpdate){
        return errorsService.find(id).map(errors -> {
            errors.setDescription(errorsUpdate.getDescription());
            errors.setError_code(errorsUpdate.getError_code());
            errorsService.update(errors);
            return new ResponseEntity<>(errors, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping("/api/errors/{id}")
    public ResponseEntity<?> deleteError(@PathVariable(name = "id") Long id) {
        return errorsService.find(id).map(errors -> {
            errorsService.delete(errors);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
