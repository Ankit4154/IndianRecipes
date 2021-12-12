package com.recipe.indianrecipe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {
	private Integer bookId;
	private String bookName;
	private String bookAuthor;
}
