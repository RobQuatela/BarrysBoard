package com.barrysboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Loss;
import com.opencsv.CSVReader;

public class LossService {

	public static void readLoss(InputStream file) throws IOException {
		
		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				LocalDate lossDate = DateTimeConversion.convertToDate(nextLine[10]);
				
				String csrID;
				try {
					csrID = nextLine[2].substring(nextLine[2].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[2];
				}
				double amount;
				try {
					amount = Double.parseDouble(nextLine[8]);
				} catch(NumberFormatException e) {
					amount = 0;
				}
				
				Loss loss = new Loss(nextLine[5], csrID, nextLine[7], lossDate, amount, nextLine[9],
						LocalDateTime.now(), LocalDateTime.now());
				
				CustomerServiceRepresentative csr = new CustomerServiceRepresentative(loss.getCsrID(), "Empty",
						"A");
				csr.authenticate();
				loss.authenticate();
			}
		}
	}
}
