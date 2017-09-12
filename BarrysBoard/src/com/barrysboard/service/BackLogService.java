package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.barrysboard.model.BackLog;
import com.opencsv.CSVReader;

public class BackLogService {

	public static ArrayList<BackLog> getBackLogList(File file) throws IOException {
		ArrayList<BackLog> backLog = new ArrayList<>();
		
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				int actual, prior;
				String csrID;
				LocalDate date = DateTimeConversion.convertToDate(nextLine[6]);
				
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
