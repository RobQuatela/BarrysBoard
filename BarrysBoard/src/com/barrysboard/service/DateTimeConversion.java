package com.barrysboard.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeConversion {
	
	private static Pattern pattern;
	private static Matcher matcher;
	
	private static final String DATE_PATTERN = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)";
	//private static final String TIME_PATTERN = "([01]?[0-9]|2?[0-3]):([0-5]?[0-9]):([0-5]?[0-9]):(0?[0-9]?[0-9]?[0-9])";
	//private static final String TIME_PATTERN_SHORT = "([01]?[0-9]|2?[0-3]):([0-5]?[0-9]):([0-5]?[0-9])";
	private static final String TIME_PATTERN_SHORTER = "([01]?[0-9]|2?[0-3]):([0-5]?[0-9])";
	private static final String WHITE_SPACE = "\\s";
	private static final String WHITE_SPACE_DOUBLE = "\\s\\s";

	public static LocalDate convertToDate(String date) {
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(date);
		LocalDate newDate = null;
		
		if(matcher.matches()) {
			matcher.reset();
			if(matcher.find()) {
				newDate = LocalDate.of(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(1)),
						Integer.parseInt(matcher.group(2)));
			}
		}
		
		return newDate;
	}
	
	public static LocalTime convertToTime(String time) {
		//pattern = Pattern.compile(TIME_PATTERN);
		//matcher = pattern.matcher(time);
		LocalTime newTime = null;
		
/*		if(matcher.matches()) {
			matcher.reset();
			if(matcher.find()) 
				newTime = LocalTime.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
						Integer.parseInt(matcher.group(3)));
		} else {*/
			pattern = Pattern.compile(TIME_PATTERN_SHORTER);
			matcher = pattern.matcher(time);
			//if(matcher.matches()) {
				//matcher.reset();
				if(matcher.find()) {
					newTime = LocalTime.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
							0);
				}
			//}
		//}
		
		return newTime;
	}
	
	public static String[] convertToStringArray(String str) {
		pattern = Pattern.compile(WHITE_SPACE_DOUBLE);
		matcher = pattern.matcher(str);
		String[] whitespace = null;
		
		if(matcher.find()) {
			whitespace = str.split("  ");
		} else {
			pattern = Pattern.compile(WHITE_SPACE);
			matcher = pattern.matcher(str);
			if(matcher.find())
				whitespace = str.split(" ");
		}

		return whitespace;
	}
	
}
