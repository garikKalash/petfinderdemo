package com.example.demo.controller;

import com.example.demo.model.innerpf.AnimalModel;
import com.example.demo.model.pfresponse.AnimalResponse;
import com.example.demo.service.PetFinderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pf/animals")
public class PetController {
    @Autowired
    PetFinderClient petFinderClient;

    @GetMapping("search")
    public List<AnimalModel> find(@RequestParam(value = "type", required = false) String type,
                       @RequestParam(value = "breed", required = false) List<String> breeds,
                       @RequestParam(value = "size", required = false) List<String> size,
                       @RequestParam(value = "gender", required = false) List<String> gender,
                       @RequestParam(value = "age", required = false) List<String> age,
                       @RequestParam(value = "color", required = false) List<String> color,
                       @RequestParam(value = "coat", required = false) List<String> coat,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "status", required = false) List<String> status,
                       @RequestParam(value = "organization", required = false) List<String> organization,
                       @RequestParam(value = "goodWithChildren", required = false) Boolean goodWithChildren,
                       @RequestParam(value = "goodWithDogs", required = false) Boolean goodWithDogs,
                       @RequestParam(value = "goodWithCats", required = false) Boolean goodWithCats,
                       @RequestParam(value = "houseTrained", required = false) Boolean houseTrained,
                       @RequestParam(value = "declawed", required = false) Boolean declawed,
                       @RequestParam(value = "specialNeeds", required = false) Boolean specialNeeds,
                       @RequestParam(value = "location", required = false) String location,
                       @RequestParam(value = "after", required = false) String after,
                       @RequestParam(value = "before", required = false) String before,
                       @RequestParam(value = "sort", required = false) String sort,
                       @RequestParam(value = "distance", required = false) Integer distance,
                       @RequestParam(value = "page", required = false) Integer page,
                       @RequestParam(value = "limit", required = false) Integer limit){
        AnimalResponse animalResponse = petFinderClient.getAnimals(PetFinderClient.GetAnimalsUrlBuilder.instance()
                .setAfter(after)
                .setAges(age)
                .setBefore(before)
                .setBreed(breeds)
                .setCoat(coat)
                .setColors(color)
                .setDeclawed(declawed)
                .setLimit(limit)
                .setDistance(distance)
                .setGender(gender)
                .setGoodWithCats(goodWithCats)
                .setGoodWithChildren(goodWithChildren)
                .setGoodWithDogs(goodWithDogs)
                .setHouseTrained(houseTrained)
                .setLocation(location)
                .setName(name)
                .setOrganization(organization)
                .setPage(page)
                .setSize(size)
                .setSort(sort)
                .setSpecialNeeds(specialNeeds)
                .setStatus(status)
                .setType(type));

        if(animalResponse == null
                || animalResponse.animals == null
                || animalResponse.animals.isEmpty()) {
            return Collections.emptyList();
        }

        return animalResponse.animals.stream().map(AnimalModel::new)
                .collect(Collectors.toList());
    }

}
