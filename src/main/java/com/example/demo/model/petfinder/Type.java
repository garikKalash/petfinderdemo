package com.example.demo.model.petfinder;

import java.util.HashSet;
import java.util.Set;

public class Type {
    public String name;
    public Set<String> coats;
    public Set<String> colors;
    public Set<String> genders;
    public Set<String> breeds = new HashSet<>();
}
