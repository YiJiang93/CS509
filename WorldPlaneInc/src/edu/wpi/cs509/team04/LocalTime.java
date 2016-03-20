package edu.wpi.cs509.team04;

import java.util.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class provides an method for us to convert time between depart airport and arrival airport
 * We use google map time-zone API to get the date and time 
 * This API gives a json format.
 */
public class LocalTime{

	
	/**
	 * This class has 3 parameters:longtitude, latitude and a given time 
	 * It will return a time and a date of the depart airport compared with arrival airport
	 * @param longtitute 
	 * @param latitute
	 * @param time
	 * @return
	 */
	public static Date convert(double longtitute, double latitute, Date time){
		String key = ConfigSingleton.getInstance().get("google-api-key");
		String base = ConfigSingleton.getInstance().get("google-api-base");
		String currentLoc = ConfigSingleton.getInstance().get("current-location");
		
		// Connection with API and get the content in the url provided longtitute and latitute
		String surl = base + "?location="+latitute+","+longtitute+"&timestamp="+time.getTime()/1000+"&key=" + key;
		final String mysurl = base + "?location=" + currentLoc + "&timestamp="+time.getTime()/1000+"&key=" + key;
		
		/**
		 * Instantiate an rdate and url and check whether they are validated 
		 */
		Date rdate = null;
		URL url = null, myurl = null;
		try {
			url = new URL(surl);
			myurl = new URL(mysurl);
		} catch (MalformedURLException e) {			
			e.printStackTrace();
			return null;
		}
		/**
		 * Instantiate an request and myrequest and check whether they are validated 
		 * If they are validated, build the connection to corresponding url
		 */

		HttpURLConnection request = null,  myrequest = null;
		try {
			request = (HttpURLConnection)url.openConnection();
			myrequest = (HttpURLConnection)myurl.openConnection();
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		
	    try {
			request.connect();
			myrequest.connect();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		    

		// Convert to a JSON object to print data
	    JsonParser jp = new JsonParser();
	    JsonElement dat = null, mydat = null;
		try {
			//Convert the input stream to a json element
			dat = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			mydat = jp.parse(new InputStreamReader((InputStream) myrequest.getContent())); 
		} catch (JsonIOException e) {
			e.printStackTrace();
			return null;
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} //Convert the input stream to a json element
	    
	    JsonObject dateobj = dat.getAsJsonObject(); //May be an array, may be an object. 
	    JsonObject mydateobj = mydat.getAsJsonObject(); //May be an array, may be an object.
	    long localTime;
	    try{
	    	/**
			 * A formula to convert time between airports
			 * The local time of a given location have fields:dstOffset,rawOffset,
			 * status,timeZoneId and timeZoneName
			 */
	    	
	    	localTime = time.getTime()-(mydateobj.get("rawOffset").getAsLong()-dateobj.get("rawOffset").getAsLong()+mydateobj.get("dstOffset").getAsLong()-dateobj.get("dstOffset").getAsLong())*1000;	
	    }
	    catch(Exception ex){
	    	ex.printStackTrace();
			return null;
	    }
	    rdate = new Date(localTime);
		return rdate;
	}
	

	
	
	
	
	public static void main(String[] args){
		ServerInterface resSys = new ServerInterface();
		String team = ConfigSingleton.getInstance().get("team");
		
		String xmlAirport = resSys.getAirports(team);
				
		Airports ports = new Airports();
		ports.addAll(xmlAirport);

		for(int i =0; i < 50; i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Airport airport1 = ports.get(i);
			System.out.println(i + " " + airport1.name());			
			Date now = new Date();
			System.out.println(now);
			Date convertedTime = convert(airport1.longitude(), airport1.latitude(), now);			
			System.out.println(convertedTime);	
			System.out.println("------------------------------------------");
		}		
	}
}

