package com.example.demo.service;

import com.example.demo.entities.AnimalType;
import com.example.demo.entities.Criteria;
import com.example.demo.entities.CriteriaValue;
import com.example.demo.model.petfinder.Organization;
import com.example.demo.model.petfinder.Type;
import com.example.demo.repository.AnimalTypeRepository;
import com.example.demo.repository.CriteriaRepository;
import com.example.demo.repository.CriteriaValueRepository;
import com.example.demo.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoadData {

    @Autowired
    PetFinderClient petFinderClient;

    @Autowired
    AnimalTypeRepository animalTypeRepository;

    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    CriteriaValueRepository criteriaValueRepository;

    @Autowired
    OrgRepository orgRepository;

    @Scheduled(fixedDelay = 3 * 60 * 60 * 1000, initialDelay = 0)
    public void loadTypes() {
         new Thread(()-> {
             List<Type> types = petFinderClient.getTypes();
             for (Type type : types) {

                AnimalType animalType = animalTypeRepository.findByName(type.name).orElse(new AnimalType());
                animalType.setName(type.name);
                animalType.setBreeds(type.breeds.toArray(new String[0]));

                Criteria criteria = criteriaRepository.findByName("color").orElseThrow(NullPointerException::new);
                List<Long> criteriaValIds = new ArrayList<>();
                for (String color : type.colors) {
                    Optional<CriteriaValue> criteriaValueOpt = criteriaValueRepository.findByValueAndCriteria(color, criteria);
                    if (criteriaValueOpt.isPresent()) {
                        criteriaValIds.add(criteriaValueOpt.get().getId());
                        continue;
                    }
                    CriteriaValue criteriaValue = new CriteriaValue();
                    criteriaValue.setValue(color);
                    criteriaValue.setCriteria(criteria);
                    criteriaValue.getAnimalTypes().add(animalType);
                    animalType.getCriteriaValues().add(criteriaValue);
                }

                criteria = criteriaRepository.findByName("coat").orElseThrow(NullPointerException::new);
                for (String color : type.coats) {
                    Optional<CriteriaValue> criteriaValueOpt = criteriaValueRepository.findByValueAndCriteria(color, criteria);
                    if (criteriaValueOpt.isPresent()) {
                        criteriaValIds.add(criteriaValueOpt.get().getId());
                        continue;
                    }
                    CriteriaValue criteriaValue = new CriteriaValue();
                    criteriaValue.setValue(color);
                    criteriaValue.setCriteria(criteria);
                    criteriaValue.getAnimalTypes().add(animalType);
                    animalType.getCriteriaValues().add(criteriaValue);
                }


                criteria = criteriaRepository.findByName("gender").orElseThrow(NullPointerException::new);
                for (String color : type.genders) {
                    Optional<CriteriaValue> criteriaValueOpt = criteriaValueRepository.findByValueAndCriteria(color, criteria);
                    if (criteriaValueOpt.isPresent()) {
                        criteriaValIds.add(criteriaValueOpt.get().getId());
                        continue;
                    }
                    CriteriaValue criteriaValue = new CriteriaValue();
                    criteriaValue.setValue(color);
                    criteriaValue.setCriteria(criteria);
                    criteriaValue.getAnimalTypes().add(animalType);
                    animalType.getCriteriaValues().add(criteriaValue);
                }

                AnimalType saved = animalTypeRepository.save(animalType);
                for (Long cvId : criteriaValIds) {
                    criteriaValueRepository.insertCriteriaValue(cvId, saved.getId());
                }
            }
        }).start();

    }



    @Scheduled(fixedDelay = 3 * 60 * 60 * 1000, initialDelay = 0)
    public void loadOrganizations(){
        new Thread(()-> {
            List<Organization> organizations = petFinderClient.getOrganizations();
            List<com.example.demo.entities.Organization> entitiesToSave = new ArrayList<>();
            for (Organization organization : organizations) {
                if (orgRepository.findByExternalId(organization.id).isPresent()) {
                    continue;
                }

                com.example.demo.entities.Organization orgEntity = new com.example.demo.entities.Organization();
                orgEntity.setName(organization.name);
                orgEntity.setExternalId(organization.id);
                orgEntity.setAddress1(organization.address.address1);
                orgEntity.setAddress2(organization.address.address2);
                orgEntity.setCity(organization.address.city);
                orgEntity.setPostcode(organization.address.postcode);
                orgEntity.setState(organization.address.state);
                orgEntity.setCountry(organization.address.country);

                entitiesToSave.add(orgEntity);
            }

            orgRepository.saveAll(entitiesToSave);
        }).start();
    }

}
