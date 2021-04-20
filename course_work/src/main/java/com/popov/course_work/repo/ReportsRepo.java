package com.popov.course_work.repo;

import com.popov.course_work.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepo extends JpaRepository<Reports, Long> {
}
