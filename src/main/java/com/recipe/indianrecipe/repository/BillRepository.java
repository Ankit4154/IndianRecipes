package com.recipe.indianrecipe.repository;

import org.springframework.data.repository.CrudRepository;

import com.recipe.indianrecipe.model.Bill;

public interface BillRepository extends CrudRepository<Bill, Integer>{

	Bill findById(int id);
}
