package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.barrysboard.model.Sales;
import com.opencsv.CSVReader;

public class SalesService {

	public static ArrayList<Sales> getSalesList() throws IOException {
		ArrayList<Sales> sales = new ArrayList<>();
		File file = new File("C:/Users/rquatela/Desktop/sales8-24-17.csv");
		
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				if (nextLine[17] != "C") {
					sales.add(new Sales(nextLine[1], nextLine[0], nextLine[9], LocalDate.parse(nextLine[8], format),
							nextLine[11], nextLine[13], Double.parseDouble(nextLine[5]),
							Double.parseDouble(nextLine[6])));
				}
			}
		}
		
		return sales;
	}
}
