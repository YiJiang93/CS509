package edu.wpi.cs509.team04;

import java.util.*;
import java.text.*;

public class LocalTime{
	public static final String DATE_FORMAT="MM/dd/yyyy HH:mm:ss";
	
	public static String getTime(Date date,DateFormat formatter){
		return formatter.format(date);
	}
	
	public static String dataTransformBetweenTimeZone(Date sourceDate,DateFormat formatter,
			TimeZone DepartTimeZone, TimeZone ArrivalTimeZone){
		long ArrivalTime= sourceDate.getTime()-DepartTimeZone.getRawOffset()+ArrivalTimeZone.getRawOffset();	 
		return LocalTime.getTime(new Date(ArrivalTime), formatter);
	}
	
	public static Date convert(double longtitute, double latitute, Date time){
				
		return null;
	}
	
	public static void main(String[] args){
		ServerInterface resSys = new ServerInterface();
		String team = ConfigSingleton.getInstance().get("team");
		
		String xmlAirport = resSys.getAirports(team);
		
		
		Airports ports = new Airports();
		ports.addAll(xmlAirport);
		
		Airport airport1 = ports.get(0);
		
		Date now = new Date();
		System.out.println(now);
		
		Date convertedTime = convert(airport1.longitude(), airport1.latitude(), now);
		
		System.out.println(convertedTime);
		
		
//		DateFormat formatter= new SimpleDateFormat(DATE_FORMAT);
//		Date date=Calendar.getInstance().getTime();
//		TimeZone departTimeZone=TimeZone.getTimeZone("America/Los_Angeles");
//		System.out.println(departTimeZone);
//		TimeZone arrivalTimeZone=TimeZone.getTimeZone("Asia/Singapore");
//		System.out.println(arrivalTimeZone);
//		System.out.println(LocalTime.dataTransformBetweenTimeZone(date, formatter, departTimeZone, arrivalTimeZone));
//		
		
	}
}

