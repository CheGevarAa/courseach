package com.popov.course_work.repo;

import com.popov.course_work.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeesRepo extends JpaRepository<Employees, Long> {
    /**
     * Интерфейс Репозитория сущности Сотрудники(EmployeesRepo)
     * Данный модуль недобходим для упрощения написания алгоритмов взаимодействия клиента и сервера
     * и включает в себя реальизацию самых простых запросов, что упрощает написание всего кода вцелом,
     * также в этом интерфейсеможно реализоватть дополнительные методы, необходимые для сревера
     */
    public Optional<Employees> findByUsername(String username);
}
