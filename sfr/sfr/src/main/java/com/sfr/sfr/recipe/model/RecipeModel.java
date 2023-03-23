package com.sfr.sfr.recipe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@Entity
@Table(name = "recipe")
public class RecipeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private String ingredients;
    @Column(nullable = false)
    private String instructions;
    @Column
    private int evaluation;
    @Column
    private int preparationTime;
    @Column
    private String category;

}
