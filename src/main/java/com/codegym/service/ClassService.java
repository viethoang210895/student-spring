package com.codegym.service;

import com.codegym.model.Classes;
import com.codegym.repository.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService implements ICRUDService<Classes,Long>,IClassService {
@Autowired
private IClassRepository iClassRepository;


    @Override
    public Page<Classes> findAll(Pageable page) {
        return iClassRepository.findAll((Pageable) page);
    }

    @Override
    public Classes findById(Long id) {
        return iClassRepository.findById(id).get();
    }

    @Override
    public void save(Classes aClass) {
        iClassRepository.save(aClass);
    }

    @Override
    public void delete(Long id) {
        iClassRepository.deleteById(id);
    }

    @Override
    public Page<Classes> findByName(String name, Pageable pageable) {
        return iClassRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public List<Classes> findClass() {
        return (List<Classes>) iClassRepository.findAll();
    }
}
