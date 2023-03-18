package com.sfr.sfr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class RecipeModel {

    private int id;
    private String name;
    private String description;
    private String ingredients;
    private String instructions;
    private int evaluation;
    private int preparationTime;
    private String category;

}
