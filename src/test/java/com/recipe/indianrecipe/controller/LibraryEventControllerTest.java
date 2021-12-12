package com.recipe.indianrecipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.recipe.indianrecipe.model.Book;
import com.recipe.indianrecipe.model.LibraryEvent;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryEventControllerTest {
	
	@Autowired
	TestRestTemplate restTemplate;
		
	@Test
	void postLibraryEvent() {
		// given
		Book book = Book.builder()
				.bookId(123)
				.bookAuthor("Ankit")
				.bookName("spring kafka test")
				.build();
		
		LibraryEvent libraryEvent = LibraryEvent.builder()
									.libraryEvenId(null)
									.book(book)
									.build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", MediaType.APPLICATION_JSON.toString());
		HttpEntity<LibraryEvent> request = new HttpEntity<>(libraryEvent, headers);
		
		// when
		ResponseEntity<LibraryEvent> responseEntity = restTemplate.exchange("/v1/libraryevent", HttpMethod.POST, request, LibraryEvent.class);
		
		// then
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
}
