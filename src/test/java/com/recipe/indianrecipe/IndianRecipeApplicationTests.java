package com.recipe.indianrecipe;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipe.indianrecipe.model.Bill;
import com.recipe.indianrecipe.service.BillService;

@SpringBootTest
class IndianRecipeApplicationTests {

	@Mock BillService billService;
	@Test
	void contextLoads() {
		when(billService.getBillById(0)).thenReturn(new Bill());
	}
	

}
