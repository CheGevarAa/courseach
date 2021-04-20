package com.popov.course_work.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date creation_date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employees employee;
    private Long department_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private DangerLevels dangerLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Errors errors;
}
