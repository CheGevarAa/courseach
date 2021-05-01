package com.popov.course_work.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Errors {
    /**
     * Класс сущности Ошибки(Errors)
     * В данном модуле происходит описание сущности БД и задаются ее параметры
     * вместе со связями с другими сущностями
     */
    @Id
    private Long error_code;

    private String description;
}
