package com.barrysboard.servicetest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import com.barrysboard.service.DateTimeConversion;

public class DateTimeConversionTest {

	@Test
	public void testConvertToStringArray() {
		String dateTime = "8/25/2017 09:30:09:955";
		String[] objs = DateTimeConversion.convertToStringArray(dateTime);
		
		assertEquals("8/25/2017", objs[0]);
		assertEquals("09:30:09:955", objs[1]);
	}
	
	@Test
	public void testConvertToTime() {
		String time = "09:30:09:955";
		assertEquals(LocalTime.of(9, 30, 9), DateTimeConversion.convertToTime(time));
	}
	
	@Test
	public void testConvertToDate() {
		String file = "8/25/2017";
		assertEquals(LocalDate.of(2017, 8, 25), DateTimeConversion.convertToDate(file));
	}

}
