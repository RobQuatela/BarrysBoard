package com.barrysboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Orders;
import com.opencsv.CSVReader;

public class OrdersService {

	public static void readOrders(InputStream file) throws IOException {

		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				String company = nextLine[9].substring(0, 3);
				String csrID;
				try {
					csrID = nextLine[0].substring(nextLine[0].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[0];
				}
				String[] dateTimes = DateTimeConversion.convertToStringArray(nextLine[15]);
				LocalDate date = DateTimeConversion.convertToDate(dateTimes[0]);
				LocalTime time = DateTimeConversion.convertToTime(dateTimes[1]);
				
				Orders order = new Orders(
						nextLine[1], csrID, company, date, time, nextLine[19],
						nextLine[21], nextLine[17], Double.parseDouble(nextLine[5]),
						Double.parseDouble(nextLine[4]), LocalDateTime.now(), LocalDateTime.now());
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(order.getCsrID(), "Empty",
						"A");
				
				csr.authenticate();
				order.authenticate();		
			}
		}	
	}
}
