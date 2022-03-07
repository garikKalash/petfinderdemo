package com.example.demo.controller;

import com.example.demo.model.innerpf.AnimalTypeModel;
import com.example.demo.model.innerpf.OrganizationModel;
import com.example.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pf/data")
public class DataController {
    @Autowired
    private DataService dataService;

    /**
     *
     * @return animal types with related characteristics
     */
    @GetMapping("types")
    public List<AnimalTypeModel> getTypes(){
        return dataService.getAnimalTypes().stream().map(AnimalTypeModel::new).collect(Collectors.toList());
    }

    /**
     *
     * @return returns 100 organizations
     */
    @GetMapping("organizations")
    public List<OrganizationModel> getOrganizations() {
        return dataService.getOrganizations().stream().map(OrganizationModel::new).collect(Collectors.toList());
    }
}
