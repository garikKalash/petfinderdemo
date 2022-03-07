package com.example.demo.repository;

import com.example.demo.entities.Criteria;
import com.example.demo.entities.CriteriaValue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CriteriaValueRepository extends CrudRepository<CriteriaValue, Long> {
    Optional<CriteriaValue> findByValueAndCriteria(String value, Criteria criteria);

    @Modifying
    @Transactional
    @Query(value = "insert into f_animal_criteria_value (criteria_val_Id, animal_Id) VALUES (?, ?)", nativeQuery = true)
    void insertCriteriaValue(Long criteriaValId, Long animalTypeId);
}
