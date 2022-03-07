package com.example.demo.model.innerpf;

import com.example.demo.entities.Organization;

public class OrganizationModel {
    public String orgId;
    public String name;
    public String location;

    public OrganizationModel(Organization organization){
        this.name = organization.getName();
        this.orgId = organization.getExternalId();
        this.location = String.join(",", organization.getAddress1(),
                organization.getAddress2(), organization.getCity(),
                organization.getCountry(), organization.getPostcode(), organization.getState());
    }
}
