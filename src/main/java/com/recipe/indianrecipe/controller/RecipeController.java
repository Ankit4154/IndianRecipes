package com.recipe.indianrecipe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.indianrecipe.model.Recipe;
import com.recipe.indianrecipe.service.RecipeService;


@RestController
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
		
	@GetMapping("/")
	public ResponseEntity<List<Recipe>> getAllReciepe() {
		return ResponseEntity.ok(recipeService.getAllRecipe());
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe) {
		recipeService.saveRecipe(recipe);
		return new ResponseEntity<Recipe>(recipe, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Recipe> getById(@PathVariable int id) {
		return ResponseEntity.ok(recipeService.getRecipeById(id));
	}
	
	@GetMapping(value = "/{id}/show", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageById(@PathVariable int id, HttpServletResponse response) throws IOException {
		return ResponseEntity.ok(recipeService.getImageById(id));
	}
}
