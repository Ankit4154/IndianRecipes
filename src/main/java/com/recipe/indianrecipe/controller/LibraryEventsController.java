package com.recipe.indianrecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.recipe.indianrecipe.model.LibraryEvent;
import com.recipe.indianrecipe.producer.LibraryEventProducer;

@RestController
public class LibraryEventsController {
	
	@Autowired
	LibraryEventProducer libraryEventProducer;
	
	@PostMapping("/v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException{
		
		// invoke kafka producer
		libraryEventProducer.sendLibraryEvent(libraryEvent);
		
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
