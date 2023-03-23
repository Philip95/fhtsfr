package com.sfr.sfr.recipe.repository;

import com.sfr.sfr.recipe.model.RecipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel, Integer> {
}
