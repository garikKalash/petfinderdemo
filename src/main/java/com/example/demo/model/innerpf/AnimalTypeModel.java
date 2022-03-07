package com.example.demo.model.innerpf;

import com.example.demo.entities.AnimalType;
import com.example.demo.entities.CriteriaValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AnimalTypeModel {
    public List<String> breeds;
    public List<String> colors;
    public List<String> coats;
    public List<String> genders;
    public String name;
    public AnimalTypeModel(AnimalType animalType) {
        this.breeds = Arrays.asList(animalType.getBreeds());
        this.coats = getSpecifications(animalType, "coat");
        this.colors = getSpecifications(animalType, "color");
        this.genders = getSpecifications(animalType, "gender");
        this.name = animalType.getName();
    }

    private List<String> getSpecifications(AnimalType animalType, String name) {
        return animalType.getCriteriaValues().stream()
                .filter(criteriaValue -> criteriaValue.getCriteria().getName().equals(name))
                .map(CriteriaValue::getValue).collect(Collectors.toList());
    }
}
