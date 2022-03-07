package com.example.demo.repository;

import com.example.demo.entities.Criteria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CriteriaRepository extends CrudRepository<Criteria, Long> {
    Optional<Criteria> findByName(String name);
}
