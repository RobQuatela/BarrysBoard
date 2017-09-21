package com.barrysboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.barrysboard.model.Incompletes;
import com.opencsv.CSVReader;

public class IncompletesService {

	public static void readIncompletes(InputStream file) throws IOException {

		try(CSVReader reader = new CSVReader(new InputStreamReader(file))) {
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				if(nextLine[2].isEmpty()) {
					String company = nextLine[0].substring(0, 3);
					String csrID;
					try {
						csrID = nextLine[8].substring(nextLine[8].length() - 4);
					} catch (StringIndexOutOfBoundsException e) {
						csrID = nextLine[8];
					}
					String[] dateTimes = DateTimeConversion.convertToStringArray(nextLine[3]);
					LocalDate date = DateTimeConversion.convertToDate(dateTimes[0]);
					LocalTime time = DateTimeConversion.convertToTime(dateTimes[1]);

					Incompletes incomplete = new Incompletes(nextLine[1], csrID, company, date, time, nextLine[4],
							nextLine[9], LocalDateTime.now(), LocalDateTime.now());
					CustomerServiceRepresentative csr = new CustomerServiceRepresentative(incomplete.getCsrID(), "Empty",
							"A");

					csr.authenticate();
					incomplete.authenticate();
				}
				
			}
		}
		
	}
}
