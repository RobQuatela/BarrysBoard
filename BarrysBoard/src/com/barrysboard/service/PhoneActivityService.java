package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.barrysboard.model.BackLog;
import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Orders;
import com.opencsv.CSVReader;

public class PhoneActivityService {

	//public static final File file = new File("C:/Users/rquatela/Desktop/phoneactivity.csv");
	public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static ArrayList<CustomerServiceRepresentative> getCSRList(File file) throws IOException {
		
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				String csrID;
				try {
					csrID = nextLine[1].substring(nextLine[1].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[1];
				}
				CustomerServiceRepresentative newCSR = new CustomerServiceRepresentative(
						csrID, nextLine[2] + ", " + nextLine[3]);
				csrs.add(newCSR);
			}
		}
		
		return csrs;
	}
	
/*	public static ArrayList<Orders> getOrdersList(File file) throws IOException {
		ArrayList<Orders> orders = new ArrayList<>();
		
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				String csrID;
				try {
					csrID = nextLine[0].substring(nextLine[1].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[0];
				}
				orders.add(new Orders(
						1, csrID, nextLine[2] + ", " + nextLine[3], nextLine[0], LocalDate.parse(nextLine[6], format),
						Integer.parseInt(nextLine[8]), Integer.parseInt(nextLine[11]), Integer.parseInt(nextLine[9]),
						Integer.parseInt(nextLine[10]), Integer.parseInt(nextLine[15]), LocalDateTime.now(), LocalDateTime.now()));
				orders.add(new Orders(
						nextLine[1], csrID, 
			}
		}
		
		return orders;
	}*/
	
	public static ArrayList<BackLog> getBackLogList(File file) throws IOException {
		ArrayList<BackLog> backLog = new ArrayList<>();
		
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				int actual, prior;
				String csrID;
				LocalDate date = DateTimeConversion.convertToDate(nextLine[5]);
				
				try {
					actual = Integer.parseInt(nextLine[13]);
				} catch(NumberFormatException e) {
					actual = 0;
				}
				try {
					prior = Integer.parseInt(nextLine[14]);
				} catch(NumberFormatException e) {
					prior = 0;
				}
				
				try {
					csrID = nextLine[1].substring(nextLine[1].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[1];
				}
				
				backLog.add(new BackLog(
						csrID, nextLine[0], date, 
						actual, prior, LocalDateTime.now(), LocalDateTime.now()));
			}
		}
		
		return backLog;
	}
}
