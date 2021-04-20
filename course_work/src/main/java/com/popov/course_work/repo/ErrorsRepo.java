package com.popov.course_work.repo;

import com.popov.course_work.entity.Errors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorsRepo extends JpaRepository<Errors, Long> {
}
