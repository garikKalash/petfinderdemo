package com.example.demo.model.innerpf;

import com.example.demo.model.petfinder.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class AnimalModel {
    public Integer id;
    public String organization;
    public String url;
    public String breed;
    public String age;
    public String name;
    public List<String> photos;
    public String description;

    public AnimalModel(Animal animal){
        this.id = animal.id;
        this.organization = animal.organization_id;;
        this.url = animal.url;
        this.breed = animal.breeds.primary;
        this.age = animal.age;
        this.name = animal.name;
        this.photos = animal.photos.stream().map(photo -> photo.medium).collect(Collectors.toList());
        this.description = animal.description;
    }
}
