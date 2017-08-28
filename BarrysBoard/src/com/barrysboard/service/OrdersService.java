package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.barrysboard.model.Orders;
import com.opencsv.CSVReader;

public class OrdersService {

	public static ArrayList<Orders> getOrdersList() throws IOException {
		File file = new File("C:/Users/rquatela/Desktop/phoneactivity.csv");
		ArrayList<Orders> orders = new ArrayList<>();
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy");
				//LocalDate date = 
				orders.add(new Orders(
						1, nextLine[1], nextLine[2] + ", " + nextLine[3], nextLine[0], LocalDate.parse(nextLine[6], format),
						Integer.parseInt(nextLine[8]), Integer.parseInt(nextLine[11]), Integer.parseInt(nextLine[9]),
						Integer.parseInt(nextLine[10]), Integer.parseInt(nextLine[15])));
			}
		}
		
		return orders;
	}
}
