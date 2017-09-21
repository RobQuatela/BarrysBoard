package com.barrysboard.servicetest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import com.barrysboard.model.Sales;
import com.barrysboard.service.SalesService;

public class SalesServiceTest {

	@Test
	public void testGetSalesList() {
		InputStream file = null;
		ArrayList<Sales> salesTest = null;
		//sales.add(new Sales("961684", "9996", "SSB", LocalDate.of(2017, 8, 25), "REG", "R", "H", 156.50, 156.50));
		//sales.add(new Sales("962488", "9996", "SSB", LocalDate.of(2017, 8, 25), "REG", "R", "H", 218.40, 218.40));

		try {
			SalesService.readSales(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < 2; i++) {
			//assertEquals(sales.get(i).getDate(), salesTest.get(i).getDate());
			//assertEquals(sales.get(i).getOrderID(), salesTest.get(i).getOrderID());
		}
	}

}
