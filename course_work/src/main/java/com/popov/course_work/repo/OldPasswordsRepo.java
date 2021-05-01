package com.popov.course_work.repo;

import com.popov.course_work.entity.OldPasswords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OldPasswordsRepo extends JpaRepository<OldPasswords, Long> {
    /**
     * Интерфейс Репозитория сущности Старые Пароли(OldPasswordsRepo)
     * Данный модуль недобходим для упрощения написания алгоритмов взаимодействия клиента и сервера
     * и включает в себя реальизацию самых простых запросов, что упрощает написание всего кода вцелом
     */
}
