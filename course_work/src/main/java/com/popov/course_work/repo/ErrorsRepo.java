package com.popov.course_work.repo;

import com.popov.course_work.entity.Errors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorsRepo extends JpaRepository<Errors, Long> {
    /**
     * Интерфейс Репозитория сущности Ошибки(ErrorsRepo)
     * Данный модуль недобходим для упрощения написания алгоритмов взаимодействия клиента и сервера
     * и включает в себя реальизацию самых простых запросов, что упрощает написание всего кода вцелом
     */
}
