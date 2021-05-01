package com.popov.course_work.repo;

import com.popov.course_work.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {
    /**
     * Интерфейс Репозитория сущности Департаменты(DepartmentsRepo)
     * Данный модуль недобходим для упрощения написания алгоритмов взаимодействия клиента и сервера
     * и включает в себя реальизацию самых простых запросов, что упрощает написание всего кода вцелом
     */
}
