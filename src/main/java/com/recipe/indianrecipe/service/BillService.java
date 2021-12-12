package com.recipe.indianrecipe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.recipe.indianrecipe.model.Bill;
import com.recipe.indianrecipe.repository.BillRepository;

@Service
public class BillService {

	@Autowired
	BillRepository repo;
	
	@Autowired
	Environment env;
	
	public List<Bill> getAllBills() {
		List<Bill> bills = new ArrayList<>();
		repo.findAll().forEach(bills::add);
		return bills;
	}

	public Bill saveBill(Bill bill) {
		return repo.save(bill);
	}
	
	public Bill getBillById(int id) {
		return repo.findById(id);
	}

	public Date getBillsDueDate(Date date) {
		return new Date();
	}

}
