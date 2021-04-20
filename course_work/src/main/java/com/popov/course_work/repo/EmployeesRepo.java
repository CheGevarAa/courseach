package com.popov.course_work.repo;

import com.popov.course_work.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeesRepo extends JpaRepository<Employees, Long> {
    public Optional<Employees> findByUsername(String username);
}
