package com.popov.course_work.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class OldPasswords {
    /**
     * Класс сущности Старого Пароля(OldPasswords)
     * В данном модуле происходит описание сущности БД и задаются ее параметры
     * вместе со связями с другими сущностями
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String old_pass;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    private Employees employee;
}
