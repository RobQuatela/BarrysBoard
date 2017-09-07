package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.barrysboard.model.Sales;
import com.opencsv.CSVReader;

public class SalesService {

	public static ArrayList<Sales> getSalesList(File file) throws IOException {
		ArrayList<Sales> sales = new ArrayList<>();
		
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String company = nextLine[9].substring(0, 3);
				String csrID;
				try {
					csrID = nextLine[0].substring(nextLine[0].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[0];
				}
				if (nextLine[17].equalsIgnoreCase("H")) {
					sales.add(new Sales(nextLine[1], csrID, company, LocalDate.parse(nextLine[10], format),
							nextLine[19], nextLine[21], Double.parseDouble(nextLine[5]),
							Double.parseDouble(nextLine[4]), LocalDateTime.now(), LocalDateTime.now()));
				}
			}
		}
		
		return sales;
	}
}
