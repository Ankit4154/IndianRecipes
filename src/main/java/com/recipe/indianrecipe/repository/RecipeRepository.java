package com.recipe.indianrecipe.repository;

import org.springframework.data.repository.CrudRepository;

import com.recipe.indianrecipe.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Integer>{

	Recipe findById(int id);
}
