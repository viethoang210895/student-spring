package com.codegym.repository;

import com.codegym.model.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassRepository extends PagingAndSortingRepository<Classes,Long> {
    Page<Classes> findAllByNameContaining(String name, Pageable pageable);
}
