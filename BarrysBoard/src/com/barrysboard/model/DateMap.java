package com.barrysboard.model;

import java.time.LocalDate;

public class DateMap {

	private LocalDate date;
	private String day;
	private String month;
	private String year;
	
	public DateMap(LocalDate date, String day, String month, String year) {
		this.date = date;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
}
