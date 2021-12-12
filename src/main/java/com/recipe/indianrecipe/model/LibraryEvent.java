package com.recipe.indianrecipe.model;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@Valid
	private Book book;
}
