package com.barrysboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Orders;
import com.opencsv.CSVReader;

public class OrdersService {

	public static ArrayList<Orders> readOrders(InputStream file) throws IOException {

		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			ArrayList<Orders> addressMatch = new ArrayList<>();
			
			while((nextLine = reader.readNext()) != null) {
				String company = nextLine[9].substring(0, 3);
				String csrID;
				String[] location = DateTimeConversion.convertLocation(nextLine[14]);
				
				try {
					csrID = nextLine[0].substring(nextLine[0].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[0];
				}
				
				String[] dateTimes = DateTimeConversion.convertToStringArray(nextLine[15]);
				LocalDate date = DateTimeConversion.convertToDate(dateTimes[0]);
				LocalTime time = DateTimeConversion.convertToTime(dateTimes[1]);
				LocalDate dateScheduled;
				if(nextLine[10].equalsIgnoreCase(""))
					dateScheduled = date;
				else
					dateScheduled = DateTimeConversion.convertToDate(nextLine[10]);
				
				
				Orders order = new Orders(
						nextLine[1], csrID, company, date, time, nextLine[19],
						nextLine[21], nextLine[17], Double.parseDouble(nextLine[5]),
						Double.parseDouble(nextLine[4]), nextLine[20], location[0], location[1], location[2], 
						dateScheduled, LocalDateTime.now(), LocalDateTime.now());
				
				
				Orders prevOrder = null;
				
				if(CustomerServiceRepresentative.isEstimator(order.getCsrID())) 
						prevOrder = order.checkAddress();
				
				//check for address already listed in system within 30 days, if so, replace with old csr name and put new csr name in comm id
				if(prevOrder != null && !prevOrder.getCsrID().equalsIgnoreCase(order.getCsrID())) {
					order.setCommID(order.getCsrID());
					order.setCsrID(prevOrder.getCsrID());
					addressMatch.add(order);
				} else {
					CustomerServiceRepresentative csr = new CustomerServiceRepresentative(order.getCsrID(), "Empty",
							"A");

					csr.authenticate();
					order.authenticate();	
				}
			}
			
			if(addressMatch.isEmpty())
				return null;
			else
				return addressMatch;
		}	
	}
	
	public static void readMatchedOrdersOrders(ArrayList<Orders> orders) throws IOException {

		for(Orders order : orders) {
			CustomerServiceRepresentative csr = new CustomerServiceRepresentative(order.getCsrID(), "Empty",
					"A");
			csr.authenticate();
			order.authenticate();
		}
			
	}
}
