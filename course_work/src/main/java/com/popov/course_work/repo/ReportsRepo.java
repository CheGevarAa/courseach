package com.popov.course_work.repo;

import com.popov.course_work.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepo extends JpaRepository<Reports, Long> {
    /**
     * Интерфейс Репозитория сущности Отчёты(ReportsRepo)
     * Данный модуль недобходим для упрощения написания алгоритмов взаимодействия клиента и сервера
     * и включает в себя реальизацию самых простых запросов, что упрощает написание всего кода вцелом
     */
}
