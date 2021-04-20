package com.popov.course_work.repo;

import com.popov.course_work.entity.DangerLevels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DangerLevelsRepo extends JpaRepository<DangerLevels, Long> {
}
