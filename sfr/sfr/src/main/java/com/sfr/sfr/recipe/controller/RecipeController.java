package com.sfr.sfr.recipe.controller;

import com.sfr.sfr.recipe.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class RecipeController {

    private final RecipeService recipeService;

}
