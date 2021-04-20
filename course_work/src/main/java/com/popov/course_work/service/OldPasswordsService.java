package com.popov.course_work.service;

import com.popov.course_work.entity.OldPasswords;
import com.popov.course_work.repo.OldPasswordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OldPasswordsService {
    @Autowired
    private OldPasswordsRepo oldPasswordsRepo;

    public void create(OldPasswords oldPasswords){oldPasswordsRepo.save(oldPasswords);}

    public void update(OldPasswords oldPasswords){oldPasswordsRepo.save(oldPasswords);}

    public void delete(OldPasswords oldPasswords){oldPasswordsRepo.delete(oldPasswords);}

    public List<OldPasswords> findAll(){return oldPasswordsRepo.findAll();}

    public Optional<OldPasswords> find(Long id){return oldPasswordsRepo.findById(id);}
}
