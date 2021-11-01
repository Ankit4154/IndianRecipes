package com.recipe.indianrecipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.recipe.indianrecipe.model.Recipe;
import com.recipe.indianrecipe.repository.RecipeRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	RecipeRepository repo;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	Environment env;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		String recipesUrl = String.format(env.getProperty("recipe.url"));

		ResponseEntity<List<Recipe>> recipeListResponse = restTemplate.exchange(recipesUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Recipe>>() {
				});
		List<Recipe> recipeList = recipeListResponse.getBody();
		repo.saveAll(recipeList);
	}

}
