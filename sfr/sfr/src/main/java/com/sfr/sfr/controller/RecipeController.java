package com.sfr.sfr.controller;

import com.sfr.sfr.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class RecipeController {

    private final RecipeService recipeService;

}
