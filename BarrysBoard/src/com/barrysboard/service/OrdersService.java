package com.barrysboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Orders;
import com.opencsv.CSVReader;

public class OrdersService {

	public static HashMap<String, Orders> readOrders(InputStream file) throws IOException {

		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			//ArrayList<Orders> addressMatch = new ArrayList<>();
			HashMap<String, Orders> orderMatch = new HashMap<>();
			int counter = 1;
			double timer = 0;
			
			while((nextLine = reader.readNext()) != null) {
				String company = nextLine[9].substring(0, 3);
				String csrID;
				String[] location = DateTimeConversion.convertLocation(nextLine[14]);
				long startTime = System.currentTimeMillis();
				double elapsedTime;
				
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
						dateScheduled, nextLine[18], LocalDateTime.now(), LocalDateTime.now());
				
				Orders prevOrder = null;
				
				if(CustomerServiceRepresentative.isEstimator(order.getCsrID())) 
						prevOrder = order.matchOrder();
				
				//check for address already listed in system within 30 days, if so, replace with old csr name and put new csr name in comm id
				if(prevOrder != null && !prevOrder.getCsrID().equalsIgnoreCase(order.getCsrID())) {
					order.setCommID(order.getCsrID());
					order.setCsrID(prevOrder.getCsrID());
					//addressMatch.add(order);
					orderMatch.put(prevOrder.getOrderID(), order);
				} else {
					CustomerServiceRepresentative csr = new CustomerServiceRepresentative(order.getCsrID(), "Empty",
							"A");

					csr.authenticate();
					order.authenticate();	
				}
				
				elapsedTime = System.currentTimeMillis() - startTime;
				timer += elapsedTime;
				System.out.println("" + counter + ": Order: " + order.getOrderID() + " Time: " + elapsedTime + " Total Time: " + timer + "");
				counter++;
			}
			
			//if(addressMatch.isEmpty())
			if(orderMatch.isEmpty())
				return null;
			else
				//return addressMatch;
				return orderMatch;
		}	
	}
	
	public static void readMatchedOrders(HashMap<String, Orders> orders) throws IOException {

		for(Map.Entry<String, Orders> order : orders.entrySet()) {
			CustomerServiceRepresentative csr = new CustomerServiceRepresentative(order.getValue().getCsrID(), "Empty",
					"A");
			csr.authenticate();
			order.getValue().authenticate();
		}
			
	}
}
