package com.barrysboard.model;

import java.time.LocalDate;

public class DateMap {

	private LocalDate date;
	private String day;
	private int week;
	private String month;
	private int year;
	
	public DateMap(LocalDate date, String day, int week, String month, int year) {
		this.date = date;
		this.day = day;
		this.week = week;
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
	
	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
