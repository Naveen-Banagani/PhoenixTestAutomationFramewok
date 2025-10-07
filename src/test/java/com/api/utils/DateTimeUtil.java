package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
	
	private DateTimeUtil() {
		//private constructor to restrict object creation of the class
	}
	
    public static String getTimeWithDaysAgo(int days) {
        return Instant.now().minus(days, ChronoUnit.DAYS).toString();
    }

}
