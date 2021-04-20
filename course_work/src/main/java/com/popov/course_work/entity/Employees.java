package com.popov.course_work.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Departments department;

    @OneToOne(mappedBy = "employee")
    private OldPasswords oldPasswords;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Reports> reportsList = new ArrayList<>();

    public void addReport(Reports report){
        report.setEmployee(this);
        this.reportsList.add(report);
    }

    public void removeReport(Reports report){
        this.reportsList.remove(report);
    }
}
