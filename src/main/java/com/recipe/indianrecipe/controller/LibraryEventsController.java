package com.recipe.indianrecipe.controller;

import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.recipe.indianrecipe.model.LibraryEvent;
import com.recipe.indianrecipe.model.LibraryEventType;
import com.recipe.indianrecipe.producer.LibraryEventProducer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LibraryEventsController {
	
	@Autowired
	LibraryEventProducer libraryEventProducer;
	
	@PostMapping("/v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException{
		
		// invoke kafka producer
		log.info("before send library event");
		//libraryEventProducer.sendLibraryEvent(libraryEvent);
		libraryEvent.setLibraryEventType(LibraryEventType.NEW);
		libraryEventProducer.sendLibraryEvent2(libraryEvent);
		//SendResult<Integer,String> sendResult = libraryEventProducer.sendLibraryEventSynchronous(libraryEvent);
		//log.info("SendResult is {}",sendResult.toString());
		log.info("after send library event");
		
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
