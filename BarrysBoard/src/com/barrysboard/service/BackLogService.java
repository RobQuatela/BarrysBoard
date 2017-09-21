package com.barrysboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.barrysboard.model.BackLog;
import com.barrysboard.model.CustomerServiceRepresentative;
import com.opencsv.CSVReader;

public class BackLogService {

	public static void readBackLog(InputStream file) throws IOException {
		
		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				int amount;
				String csrID;
				LocalDate date = DateTimeConversion.convertToDate(nextLine[5]);
				
				try {
					amount = Integer.parseInt(nextLine[14]);
				} catch(NumberFormatException e) {
					amount = 0;
				}
				
				try {
					csrID = nextLine[1].substring(nextLine[1].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[1];
				}
				
				BackLog backlog = new BackLog(
						csrID, nextLine[0], date, 
						amount, LocalDateTime.now(), LocalDateTime.now());
				
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(backlog.getCsrID(), "Empty",
						"A");
				csr.authenticate();
				backlog.authenticate();
			}
		}
	}
}
