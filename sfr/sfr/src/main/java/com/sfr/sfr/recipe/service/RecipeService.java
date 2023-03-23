package com.sfr.sfr.recipe.service;

import com.sfr.sfr.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

}
