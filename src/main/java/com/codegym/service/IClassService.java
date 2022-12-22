package com.codegym.service;

import com.codegym.model.Classes;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IClassService {
    List<Classes> findClass();
}
