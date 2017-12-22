package com.barrysboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Sales;
import com.opencsv.CSVReader;

public class SalesService {

	public static void readSales(InputStream file) throws IOException {

		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			int counter = 1;
			double timer = 0;
			
			while((nextLine = reader.readNext()) != null) {
				LocalDate saleDate = DateTimeConversion.convertToDate(nextLine[10]);
				String company = nextLine[9].substring(0, 3);
				String[] location = DateTimeConversion.convertLocation(nextLine[14]);
				long startTime = System.currentTimeMillis();
				double elapsedTime;
				
				String csrID;
				try {
					csrID = nextLine[0].substring(nextLine[0].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[0];
				}

				Sales sale = new Sales(nextLine[1], csrID, company, saleDate,
						nextLine[19], nextLine[21], nextLine[17], Double.parseDouble(nextLine[5]),
						Double.parseDouble(nextLine[4]), nextLine[20], location[0], location[1], location[2], nextLine[18], 
						LocalDateTime.now(), LocalDateTime.now());
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(sale.getCsrID(), "Empty",
						"A");
				
				sale = sale.updateOriginCSR();
				
				csr.authenticate();
				sale.authenticate();
				
				elapsedTime = System.currentTimeMillis() - startTime;
				timer += elapsedTime;
				System.out.println("" + counter + ": Sale: " + sale.getOrderID() + " Time: " + elapsedTime + " Total Time: " + timer + "");
				counter++;
			}
			
			System.out.println("Total time for upload (seconds): " + timer / 1000);
			System.out.println("Total records / second: " + (counter / (timer / 1000)));
			
		}
	}
	
	public static void readSalesCOMM(InputStream file) throws IOException {
		
		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				String comm = "";
				try {
					comm = nextLine[0].substring(nextLine[0].length() - 4);
				} catch(NullPointerException | StringIndexOutOfBoundsException e) {
					comm = nextLine[0];
				}
				
				Sales commSale = new Sales(nextLine[5], comm);
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(comm, "Empty", "A");
				
				csr.authenticate();
				commSale.updateCOMM();
			}
		}
	}
}
