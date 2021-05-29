package com.popov.course_work.service;

import com.popov.course_work.entity.DangerLevels;
import com.popov.course_work.repo.DangerLevelsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DangerLevelsService {
    /**
     * Модуль сервиса сущности Степени угроз(DangerLevelService)
     * Здесь реализованы обращения к стандартным запросам сервера
     */
    @Autowired
    private DangerLevelsRepo dangerLevelsRepo;

    public DangerLevels create(DangerLevels dangerLevels){return dangerLevelsRepo.save(dangerLevels);}

    public DangerLevels update(DangerLevels dangerLevels){return dangerLevelsRepo.save(dangerLevels);}

    public void delete(DangerLevels dangerLevels){dangerLevelsRepo.delete(dangerLevels);}

    public List<DangerLevels> findAll(){return dangerLevelsRepo.findAll();}

    public Optional<DangerLevels> find(Long id){return dangerLevelsRepo.findById(id);}
}
