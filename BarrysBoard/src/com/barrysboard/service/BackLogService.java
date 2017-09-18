package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.barrysboard.model.BackLog;
import com.opencsv.CSVReader;

public class BackLogService {

	public static ArrayList<BackLog> getBackLogList(InputStream file) throws IOException {
		ArrayList<BackLog> backLog = new ArrayList<>();
		
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
				
				backLog.add(new BackLog(
						csrID, nextLine[0], date, 
						amount, LocalDateTime.now(), LocalDateTime.now()));
			}
		}
		
		return backLog;
	}
}
