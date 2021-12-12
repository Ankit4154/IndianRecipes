package com.recipe.indianrecipe.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.indianrecipe.model.Bill;
import com.recipe.indianrecipe.service.BillService;


@RestController
public class BillController {
	
	@Autowired
	BillService billService;
		
	@GetMapping("/bills")
	public ResponseEntity<List<Bill>> getAllBills() {
		return ResponseEntity.ok(billService.getAllBills());
	}
	
	@PostMapping("/bills")
	public ResponseEntity<?> createBill(@RequestBody Bill bill) {
		billService.saveBill(bill);
		return new ResponseEntity<Bill>(bill, HttpStatus.CREATED);
	}
	
	@GetMapping("bills/{id}")
	public ResponseEntity<Bill> getBillById(@PathVariable int id) {
		return ResponseEntity.ok(billService.getBillById(id));
	}
	
	@GetMapping("bills/due/{date}")
	public ResponseEntity<Date> getBillsDueDate(@PathVariable Date date, HttpServletResponse response) throws IOException {
		return ResponseEntity.ok(billService.getBillsDueDate(date));
	}
}
