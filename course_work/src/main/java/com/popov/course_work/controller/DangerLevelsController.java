package com.popov.course_work.controller;

import com.popov.course_work.entity.DangerLevels;
import com.popov.course_work.service.DangerLevelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DangerLevelsController {
    /**
     * Класс контроллера Степеней угроз(DangerLevelsController)
     * Данный модуль обеспечивает взаимодействе с объектами отчетов в БД и клиентским приложением,
     * оно обеспечивается при помощи методов поиска, обновления и создания нового объекта.
     * Сами эти методы реализованы с помощью аннотаций, указывающих на тип запроса к БД, а также
     * ссылок на таблицы, к которой метод должен подключиться.
     *
     *
     */

    private final DangerLevelsService dangerLevelsService;

    @Autowired
    public DangerLevelsController(DangerLevelsService dangerLevelsService){
        this.dangerLevelsService = dangerLevelsService;
    }
    @PostMapping("/api/dangerLevels")
    public ResponseEntity<?>create(@RequestBody DangerLevels dangerLevels){
        dangerLevelsService.create(dangerLevels);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/dangerLevels")
    public ResponseEntity<List<DangerLevels>> findALL(){
        final List<DangerLevels> dangerLevelsList = dangerLevelsService.findAll();
        return dangerLevelsList != null && !dangerLevelsList.isEmpty()
                ? new ResponseEntity<>(dangerLevelsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/dangerLevels/{id}")
    public ResponseEntity<Optional<DangerLevels>> find(@PathVariable(name = "id")Long id){
        final Optional<DangerLevels> dangerLevels = dangerLevelsService.find(id);
        return dangerLevels!=null
                ? new ResponseEntity<>(dangerLevels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/dangerLevels/{id}")
    public ResponseEntity<?> updateDangerLevels(@PathVariable(name = "id")Long id, @RequestBody DangerLevels dangerLevelsUpdate){
        return dangerLevelsService.find(id).map(dangerLevels -> {
            dangerLevels.setDescription(dangerLevelsUpdate.getDescription());
            dangerLevelsService.update(dangerLevels);
            return new ResponseEntity<>(dangerLevels, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping("/api/dangerLevels/{id}")
    public ResponseEntity<?> deleteDangerLevel(@PathVariable(name = "id") Long id) {
        return dangerLevelsService.find(id).map(dangerLevels -> {
            dangerLevelsService.delete(dangerLevels);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
