package com.example.demo.service;

import com.example.demo.cache.Cache;
import com.example.demo.entities.AnimalType;
import com.example.demo.entities.Organization;
import com.example.demo.repository.AnimalTypeRepository;
import com.example.demo.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DataService {
    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    @Autowired
    private OrgRepository orgRepository;

    public List<AnimalType> getAnimalTypes() {
        List<AnimalType> animalTypes;
        if(Cache.get("animals") != null) {
           animalTypes = (List<AnimalType>) Cache.get("animals");
        } else {
            animalTypes = StreamSupport
                    .stream(animalTypeRepository.findAll().spliterator(), true)
                    .collect(Collectors.toList());
            Cache.put("animals", animalTypes);
        }
        return animalTypes;
    }

    public List<Organization> getOrganizations() {
        List<Organization> result;

        if (Cache.get("organization") != null) {
            result = (List<Organization>) Cache.get("organization");
        } else {
            result = StreamSupport
                    .stream(orgRepository.findAll().spliterator(), true)
                    .collect(Collectors.toList());
            Cache.put("organization", result);
        }

        return result;
    }
}
