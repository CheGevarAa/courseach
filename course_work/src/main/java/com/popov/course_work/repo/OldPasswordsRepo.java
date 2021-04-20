package com.popov.course_work.repo;

import com.popov.course_work.entity.OldPasswords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OldPasswordsRepo extends JpaRepository<OldPasswords, Long> {
}
