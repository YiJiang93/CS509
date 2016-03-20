package edu.wpi.cs509.team04;

import java.util.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LocalTime{

	
	public static Date convert(double longtitute, double latitute, Date time){
		String surl = "https://maps.googleapis.com/maps/api/timezone/json?location="+latitute+","+longtitute+"&timestamp="+time.getTime()/1000+"&key=AIzaSyC-y99KjXBa0oaAdcubEiWp6jwEnwqPfo4";
		final String mysurl = "https://maps.googleapis.com/maps/api/timezone/json?location=42.2740894,-71.8064854&timestamp="+time.getTime()/1000+"&key=AIzaSyC-y99KjXBa0oaAdcubEiWp6jwEnwqPfo4";
		Date rdate = null;
		try {
			URL url = new URL(surl);
			URL myurl = new URL(mysurl);
			HttpURLConnection request =  (HttpURLConnection)url.openConnection();
			HttpURLConnection myrequest = (HttpURLConnection)myurl.openConnection();
		    request.connect();
		    myrequest.connect();

		    // Convert to a JSON object to print data
		    JsonParser jp = new JsonParser();
		    JsonElement dat = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
		    JsonElement mydat = jp.parse(new InputStreamReader((InputStream) myrequest.getContent())); //Convert the input stream to a json element

		    JsonObject dateobj = dat.getAsJsonObject(); //May be an array, may be an object. 
		    JsonObject mydateobj = mydat.getAsJsonObject(); //May be an array, may be an object.
		    long LocalTime = time.getTime()-(mydateobj.get("rawOffset").getAsLong()-dateobj.get("rawOffset").getAsLong()+mydateobj.get("dstOffset").getAsLong()-dateobj.get("dstOffset").getAsLong())*1000;
		    System.out.println(new Date(time.getTime()));
		    rdate = new Date(LocalTime);
		    
		} catch (Exception e) {
			System.out.println("error");
		}
		
		return rdate;

				
		
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

