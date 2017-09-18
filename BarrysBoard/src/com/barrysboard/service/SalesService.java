package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	public static ArrayList<Sales> getSalesList(InputStream file) throws IOException {
		ArrayList<Sales> sales = new ArrayList<>();
		
		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				LocalDate saleDate = DateTimeConversion.convertToDate(nextLine[10]);
				String company = nextLine[9].substring(0, 3);
				
				String csrID;
				try {
					csrID = nextLine[0].substring(nextLine[0].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[0];
				}
				
				sales.add(new Sales(nextLine[1], csrID, company, saleDate,
					nextLine[19], nextLine[21], nextLine[17], Double.parseDouble(nextLine[5]),
					Double.parseDouble(nextLine[4]), LocalDateTime.now(), LocalDateTime.now()));
			}
		}
		
		return sales;
	}
}
