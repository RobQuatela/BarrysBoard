package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.barrysboard.model.Loss;
import com.opencsv.CSVReader;

public class LossService {

	public static ArrayList<Loss> getLossList(InputStream file) throws IOException {
		ArrayList<Loss> losses = new ArrayList<>();
		
		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				LocalDate lossDate = DateTimeConversion.convertToDate(nextLine[10]);
				
				String csr;
				try {
					csr = nextLine[2].substring(nextLine[2].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csr = nextLine[2];
				}
				
				losses.add(new Loss(nextLine[5], csr, nextLine[7], lossDate, Double.parseDouble(nextLine[8]), nextLine[9],
						LocalDateTime.now(), LocalDateTime.now()));
			}
		}
		
		return losses;
	}
}
