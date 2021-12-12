package com.recipe.indianrecipe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.indianrecipe.model.LibraryEvent;

@RestController
public class LibraryEventsController {
		
	
	@PostMapping("/v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent){
		
		// invoke kafka producer
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
		
		
		/*
		 * Input request body
		 * {
				"libraryEventId": null,
				"book": {
					"bookId": 456,
					"bookName": "Kafka Using Spring Boot",
					"bookAuthor": "Dilip"
				}
			}

		 */
		
	}
}
