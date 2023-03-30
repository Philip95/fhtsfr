package com.sfr.sfr.recipe.service;

import com.sfr.sfr.recipe.model.RecipeModel;
import com.sfr.sfr.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<RecipeModel> findAll() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeModel> findById(int id) {
        return recipeRepository.findById(id);
    }

    public RecipeModel save(RecipeModel recipeModel) {
        return recipeRepository.save(recipeModel);
    }

    public void deleteById(int id) {
        recipeRepository.deleteById(id);
    }

    public void deleteAll() {
        recipeRepository.deleteAll();
    }

    public List<RecipeModel> findByName(String name) {
        return recipeRepository.findByName(name);
    }

}
