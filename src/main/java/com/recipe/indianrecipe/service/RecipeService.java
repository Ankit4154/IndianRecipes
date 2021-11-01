package com.recipe.indianrecipe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.recipe.indianrecipe.model.Recipe;
import com.recipe.indianrecipe.repository.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	RecipeRepository repo;
	
	@Autowired
	RestTemplate restTemplate; 
	
	@Autowired
	Environment env;
	
	public List<Recipe> getAllRecipe() {
		List<Recipe> recipes = new ArrayList<>();
		repo.findAll().forEach(recipes::add);
		return recipes;
	}

	public void saveAllRecipe() {
		String recipesUrl = String.format(env.getProperty("recipe.url"));

		ResponseEntity<List<Recipe>> recipeListResponse = restTemplate.exchange(recipesUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Recipe>>() {
				});
		List<Recipe> recipeList = recipeListResponse.getBody();
		repo.saveAll(recipeList);
	}
	
	public Recipe saveRecipe(Recipe recipe) {
		return repo.save(recipe);
	}
	
	public Recipe getRecipeById(int id) {
		return repo.findById(id);
	}
	
	public String getImageURL(int id) {
		return repo.findById(id).getImage();
	}

	public byte[] getImageById(int id) {
		String url = getImageURL(id);
		return restTemplate.getForObject(url, byte[].class);
	}

}
