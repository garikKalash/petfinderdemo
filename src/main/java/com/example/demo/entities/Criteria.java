package com.example.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "criteria")
public class Criteria extends IdentifiedEntity<Long>{
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL,
            mappedBy="criteria")
    private Set<CriteriaValue> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CriteriaValue> getValues() {
        return values;
    }

    public void setValues(Set<CriteriaValue> values) {
        this.values = values;
    }
}
