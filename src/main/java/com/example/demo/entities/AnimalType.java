package com.example.demo.entities;


import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
@Entity
@Table(name = "animal_type")
public class AnimalType extends IdentifiedEntity<Long> {
    private String name;

    @ManyToMany(cascade = CascadeType.ALL,
            mappedBy = "animalTypes",
            fetch = FetchType.EAGER)
    private Set<CriteriaValue> criteriaValues = new HashSet<>();

    @Type(type = "string-array")
    @Column(
            name = "breeds",
            columnDefinition = "text[]"
    )
    private String[] breeds;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getBreeds() {
        return breeds;
    }

    public void setBreeds(String[] breeds) {
        this.breeds = breeds;
    }

    public Set<CriteriaValue> getCriteriaValues() {
        return criteriaValues;
    }

    public void setCriteriaValues(Set<CriteriaValue> criteriaValues) {
        this.criteriaValues = criteriaValues;
    }
}
