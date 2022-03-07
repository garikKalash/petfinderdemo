package com.example.demo.service;

import com.example.demo.interceptor.HttpRequestInterceptor;
import com.example.demo.model.petfinder.Organization;
import com.example.demo.model.petfinder.Type;
import com.example.demo.model.pfresponse.AnimalResponse;
import com.example.demo.model.pfresponse.BreedsResponse;
import com.example.demo.model.pfresponse.OrgResponse;
import com.example.demo.model.pfresponse.TypesResponse;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PetFinderClient {
    public final static String BASE_URL = "https://api.petfinder.com/v2";

    public final static String ANIMALS = "/animals";
    public final static String TYPES = "/types";
    public final static String BREEDS = "/breeds";
    public final static String ORGANIZATIONS = "/organizations?limit=100";

    private RestTemplate restTemplate ;

    @Autowired
    private TokenRefresher tokenRefresher;

    @PostConstruct
    public void init(){
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new HttpRequestInterceptor(tokenRefresher)));
    }

    public AnimalResponse getAnimals(GetAnimalsUrlBuilder getAnimalsUrlBuilder){
        return restTemplate.getForObject(getAnimalsUrlBuilder.build(), AnimalResponse.class);
    }

    public List<Type> getTypes() {
        TypesResponse typesResponse = restTemplate.getForObject(BASE_URL + TYPES, TypesResponse.class);
        if(typesResponse != null){
            for(Type type : typesResponse.types) {
                type.breeds = getBreeds(type.name);
            }
            return typesResponse.types;
        }
        return Collections.emptyList();
    }

    public Set<String> getBreeds(String typeName){
        BreedsResponse breedsResponse = restTemplate.getForObject(BASE_URL + TYPES + "/" + typeName +BREEDS, BreedsResponse.class);
        return breedsResponse != null ? breedsResponse.breeds.stream()
                .map(breed -> breed.name).collect(Collectors.toSet()) : Collections.emptySet();
    }

    public List<Organization> getOrganizations(){
        OrgResponse orgResponse = restTemplate.getForObject(BASE_URL + ORGANIZATIONS, OrgResponse.class);
        return orgResponse == null ? Collections.emptyList() : orgResponse.organizations;
    }

    public static final class GetAnimalsUrlBuilder {
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(BASE_URL+ANIMALS);

        private GetAnimalsUrlBuilder(){

        }

        public static GetAnimalsUrlBuilder instance() {
            return new GetAnimalsUrlBuilder();
        }

        public GetAnimalsUrlBuilder setType(String type){
            if (!StringUtil.isNullOrEmpty(type)) {
                uriComponentsBuilder.queryParam("type", type);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setBreed(List<String> breeds){
            if (!CollectionUtils.isEmpty(breeds)) {
                uriComponentsBuilder.queryParam("breed", String.join(",", breeds));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setSize(List<String> sizes){
            if (!CollectionUtils.isEmpty(sizes)) {
                uriComponentsBuilder.queryParam("size", String.join(",", sizes));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setGender(List<String> genders){
            if (!CollectionUtils.isEmpty(genders)) {
                uriComponentsBuilder.queryParam("gender", String.join(",", genders));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setAges(List<String> ages){
            if (!CollectionUtils.isEmpty(ages)) {
                uriComponentsBuilder.queryParam("age", String.join(",", ages));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setColors(List<String> colors){
            if (!CollectionUtils.isEmpty(colors)) {
                uriComponentsBuilder.queryParam("color", String.join(",", colors));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setCoat(List<String> coats){
            if (!CollectionUtils.isEmpty(coats)) {
                uriComponentsBuilder.queryParam("coat", String.join(",", coats));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setStatus(List<String> status){
            if (!CollectionUtils.isEmpty(status)) {
                uriComponentsBuilder.queryParam("status", String.join(",", status));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setName(String name){
            if (!StringUtil.isNullOrEmpty(name)) {
                uriComponentsBuilder.queryParam("name", String.join(",", name));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setOrganization(List<String> organization){
            if (!CollectionUtils.isEmpty(organization)) {
                uriComponentsBuilder.queryParam("organization", String.join(",", organization));
            }
            return this;
        }

        public GetAnimalsUrlBuilder setGoodWithChildren(Boolean goodWithChildren){
            if (goodWithChildren != null) {
                uriComponentsBuilder.queryParam("good_with_children", goodWithChildren);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setGoodWithDogs(Boolean goodWithChildren){
            if (goodWithChildren != null) {
                uriComponentsBuilder.queryParam("good_with_dogs", goodWithChildren);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setGoodWithCats(Boolean goodWithChildren){
            if (goodWithChildren != null) {
                uriComponentsBuilder.queryParam("good_with_cats", goodWithChildren);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setHouseTrained(Boolean houseTrained){
            if (houseTrained != null) {
                uriComponentsBuilder.queryParam("house_trained", houseTrained);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setDeclawed(Boolean declawed){
            if (declawed != null) {
                uriComponentsBuilder.queryParam("declawed", declawed);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setSpecialNeeds(Boolean special_needs){
            if (special_needs != null) {
                uriComponentsBuilder.queryParam("special_needs", special_needs);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setLocation(String location){
            if (!StringUtil.isNullOrEmpty(location)) {
                uriComponentsBuilder.queryParam("location", location);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setBefore(String location){
            if (!StringUtil.isNullOrEmpty(location)) {
                uriComponentsBuilder.queryParam("before", location);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setAfter(String after){
            if (!StringUtil.isNullOrEmpty(after)) {
                uriComponentsBuilder.queryParam("after", after);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setSort(String location){
            if (!StringUtil.isNullOrEmpty(location)) {
                uriComponentsBuilder.queryParam("sort", location);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setDistance(Integer distance){
            if (distance != null) {
                uriComponentsBuilder.queryParam("distance", distance);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setPage(Integer page){
            if (page != null) {
                uriComponentsBuilder.queryParam("page", page);
            }
            return this;
        }

        public GetAnimalsUrlBuilder setLimit(Integer limit){
            if (limit != null) {
                uriComponentsBuilder.queryParam("limit", limit);
            }
            return this;
        }

        public String build(){
            return uriComponentsBuilder.build().toUriString();
        }
    }
}
