package com.barrysboard.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.barrysboard.model.CustomerServiceRepresentative;
import com.opencsv.CSVReader;

import javafx.stage.FileChooser;

public class CustomerServiceRepresentativeService {

	public static ArrayList<CustomerServiceRepresentative> getCSRList() throws IOException {
		
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		
		//FileChooser fileChooser = new FileChooser();
		//File file = fileChooser.showOpenDialog(null);
		File newFile = new File("C:/Users/rquatela/Desktop/phoneactivity.csv");
		//InputStream is = CustomerServiceRepresentativeService.class.getClassLoader().getResourceAsStream("C:/Users/rquatela/Desktop/phoneactivity.csv");
		
		try(CSVReader reader = new CSVReader(new FileReader(newFile))) {
			
			String[] nextLine;
			reader.readNext();
			
			while((nextLine = reader.readNext()) != null) {
				CustomerServiceRepresentative newCSR = new CustomerServiceRepresentative(
						nextLine[1], nextLine[2] + ", " + nextLine[3], nextLine[0]);
				csrs.add(newCSR);
			}
		}
		
		return csrs;
	}
}
