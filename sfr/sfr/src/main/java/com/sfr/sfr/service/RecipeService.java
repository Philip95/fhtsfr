package com.sfr.sfr.service;

import com.sfr.sfr.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

}
