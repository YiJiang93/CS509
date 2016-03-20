package edu.wpi.cs509.team04;

import java.util.*;
import java.text.*;

public class DateTransformer{
	public static final String DATE_FORMAT="MM/dd/yyyy HH:mm:ss";
	
	public static String getTime(Date date,DateFormat formatter){
		return formatter.format(date);
	}
	
	public static String dataTransformBetweenTimeZone(Date sourceDate,DateFormat formatter,
			TimeZone DepartTimeZone, TimeZone ArrivalTimeZone){
		long ArrivalTime= sourceDate.getTime()-DepartTimeZone.getRawOffset()+ArrivalTimeZone.getRawOffset();	 
		return DateTransformer.getTime(new Date(ArrivalTime), formatter);
	}
	public static void main(String[] args){
		DateFormat formatter= new SimpleDateFormat(DATE_FORMAT);
		Date date=Calendar.getInstance().getTime();
		TimeZone departTimeZone=TimeZone.getTimeZone("America/Los_Angeles");
		System.out.println(departTimeZone);
		TimeZone arrivalTimeZone=TimeZone.getTimeZone("Asia/Singapore");
		System.out.println(arrivalTimeZone);
		System.out.println(DateTransformer.dataTransformBetweenTimeZone(date, formatter, departTimeZone, arrivalTimeZone));
	}
}

