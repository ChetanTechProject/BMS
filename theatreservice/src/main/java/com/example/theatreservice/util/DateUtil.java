/**
 * 
 */
package com.example.moviebooking.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author chetan.kalegowda
 *
 */
public class DateUtil {
	
	public static String convertDateToString(java.sql.Date date) {
		String covertedDate = null;
		if(date != null) {
			java.util.Date dt = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			covertedDate = dateFormat.format(dt);		
		}
		
		return covertedDate;
		
	}
	
	public static String convertTimeToString(Time time) {
		String covertedTime = null;
		if(time != null) {
			DateFormat format = new SimpleDateFormat("HH:mm");
			covertedTime = format.format(time.getTime()); 
		}
		
		return covertedTime;
		
	}
	
	public static Time convertStringToTime(String time) throws ParseException {
		Time sqlTime = null;

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		long ms = sdf.parse(time).getTime();
		sqlTime = new Time(ms);

		return sqlTime;
	}
	
}
