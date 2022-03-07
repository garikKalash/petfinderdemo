package com.example.demo.repository;

import com.example.demo.entities.AnimalType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalTypeRepository extends CrudRepository<AnimalType, Long> {
    Optional<AnimalType> findByName(String name);
}
