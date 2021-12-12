package com.recipe.indianrecipe.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.indianrecipe.model.Book;
import com.recipe.indianrecipe.model.LibraryEvent;
import com.recipe.indianrecipe.producer.LibraryEventProducer;

@WebMvcTest(LibraryEventsController.class)
@AutoConfigureMockMvc
public class LibraryEventControllerUnitTest {

	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	LibraryEventProducer libraryEventProducer;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void postLibraryEvent() throws JsonProcessingException {
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
		
		String json = objectMapper.writeValueAsString(libraryEvent);
		doNothing().when(libraryEventProducer).sendLibraryEvent2(isA(LibraryEvent.class));
		// when
	/*	 mockMvc.perform(MockMvcRequestBuilders.post("/v1/libraryevent")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect();
				//.andExpect(HttpStatus.CREATED);
	*/
		// then
	}
	
}
