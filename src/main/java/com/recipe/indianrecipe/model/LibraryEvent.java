package com.recipe.indianrecipe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LibraryEvent {
	private Integer libraryEvenId;
	private LibraryEventType libraryEventType;
	private Book book;
}
