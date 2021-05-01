package com.popov.course_work.repo;

import com.popov.course_work.entity.DangerLevels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DangerLevelsRepo extends JpaRepository<DangerLevels, Long> {
    /**
     * Интерфейс Репозитория сущности Степени Угроз(DangerLevelsRepo)
     * Данный модуль недобходим для упрощения написания алгоритмов взаимодействия клиента и сервера
     * и включает в себя реальизацию самых простых запросов, что упрощает написание всего кода вцелом
     */
}
