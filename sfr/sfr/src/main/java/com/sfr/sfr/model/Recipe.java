package com.sfr.sfr.model;

import org.springframework.stereotype.Component;

@Component
public class Recipe {

    private int id;
    private String name;
    private String description;
    private String ingredients;
    private String instructions;
    private int evaluation;
    private int preparationTime;
    private String category;
}
