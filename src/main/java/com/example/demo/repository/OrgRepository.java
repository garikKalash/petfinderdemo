package com.example.demo.repository;

import com.example.demo.entities.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgRepository extends CrudRepository<Organization, Long> {
    Optional<Organization> findByExternalId(String externalId);
}
