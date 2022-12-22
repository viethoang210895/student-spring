package com.codegym.service;

import com.codegym.model.Student;
import com.codegym.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public  class StudentService implements ICRUDService<Student,Long>, IStudentService {
    @Autowired
    private IStudentRepository iStudentRepository;


    @Override
    public Page<Student> findAll(Pageable page) {
        return iStudentRepository.findAll((Pageable) page);
    }

    @Override
    public Student findById(Long id) {
        return iStudentRepository.findById(id).get();
    }

    @Override
    public void save(Student student) {
        iStudentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        iStudentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findByName(String name, Pageable pageable) {
        return null;
    }


    @Override
    public Page<Student> findContaining(String name, Pageable pageable) {
        return findContaining(name,pageable);
    }
}
