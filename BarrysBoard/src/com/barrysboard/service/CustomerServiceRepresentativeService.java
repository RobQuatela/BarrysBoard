package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.opencsv.CSVReader;

public class CustomerServiceRepresentativeService {

	public static ArrayList<CustomerServiceRepresentative> getCSRList(File file) throws IOException {
		
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				String csrID;
				try {
					csrID = nextLine[0].substring(nextLine[0].length() - 4);
				} catch(StringIndexOutOfBoundsException e) {
					csrID = nextLine[0];
				}
				CustomerServiceRepresentative newCSR = new CustomerServiceRepresentative(
						csrID, nextLine[1] + ", " + nextLine[2], nextLine[13]);
				csrs.add(newCSR);
			}
		}
		
		return csrs;
	}
}
