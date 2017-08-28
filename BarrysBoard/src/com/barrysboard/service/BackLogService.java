package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.barrysboard.model.BackLog;
import com.opencsv.CSVReader;

public class BackLogService {

	public static ArrayList<BackLog> getBackLogList() throws IOException {
		ArrayList<BackLog> backLog = new ArrayList<>();
		
		File file = new File("C:/Users/rquatela/Desktop/phoneactivity.csv");
		
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				backLog.add(new BackLog(
						nextLine[1], nextLine[0], LocalDate.parse(nextLine[6]), 
						Integer.parseInt(nextLine[13]), Integer.parseInt(nextLine[14])));
			}
		}
		
		return backLog;
	}
}
