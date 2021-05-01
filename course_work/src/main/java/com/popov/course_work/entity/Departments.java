package com.popov.course_work.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Departments {
    /**
     * Класс сущности Департаменты(Departments)
     * В данном модуле происходит описание сущности БД и задаются ее параметры
     * вместе со связями с другими сущностями
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department_name;
}
