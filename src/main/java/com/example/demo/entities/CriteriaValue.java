package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "criteria_value")
public class CriteriaValue extends IdentifiedEntity<Long> {
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    private Criteria criteria;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "f_animal_criteria_value",
            joinColumns = { @JoinColumn(name = "criteria_val_Id") },
            inverseJoinColumns = { @JoinColumn(name = "animal_Id") }
    )
    private Set<AnimalType> animalTypes = new HashSet<>();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Set<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(Set<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }
}
