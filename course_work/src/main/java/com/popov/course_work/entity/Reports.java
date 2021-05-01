package com.popov.course_work.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Reports {
    /**
     * Класс сущности Отчета(Reports)
     * В данном модуле происходит описание сущности БД и задаются ее параметры
     * вместе со связями с другими сущностями
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate creation_date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employees employee;


    @ManyToOne(fetch = FetchType.LAZY)
    private DangerLevels dangerLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Errors errors;
}
