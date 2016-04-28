/**
 * File: ServerInterface.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.wpi.cs509.team04.gui.ReservationView;
import edu.wpi.cs509.team04.resources.ConfigSingleton;

/**
 * The ServerInterface class provides an interface for
 * communicating with the CS 509 server. More specifically,
 * the ServerInterface class provides sample methods for
 * performing HTTP GET requests and HTTP POST requests
 * 
 * @author Blake Nelson (benelson at wpi.edu)
 * @version April 3, 2016
 */
public class ServerInterface {
	
	/**
	 * The singleton instance of the ServerInterface
	 */
	private static ServerInterface instance = null;
	
	/**
	 * The URL used for connecting to the CS 509 server
	 */
	private final String mUrlBase = ConfigSingleton.getInstance().get("url");
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static ServerInterface getInstance() {
		if (instance == null) {
			instance = new ServerInterface();
		}
		return instance;
	}
	
	
	/**
	 * Constructor for the instance
	 */
	private ServerInterface() {
		
	}
	
	
	/**
	 * This method returns an XML list of all of the airports by
	 * retrieving the list of airports available to the specified
	 * ticket agency through an HTTP GET request issued to the
	 * CS 509 server
	 * 
	 * @return An XML string listing all of the airports
	 */
	public String getAirports() {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		ConfigSingleton configSingleton = ConfigSingleton.getInstance();
		String team = configSingleton.get("team");
		
		try {
			// Create an HTTP connection to the server for a GET request
			url = new URL(mUrlBase + QueryFactory.getAirports(team));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", team);
			
			// If response code of SUCCESS is received read the XML string
			// returned line by line to build the full return string
			int responseCode = connection.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	/**
	 * This method returns an XML list of all of the airplanes by
	 * retrieving the list of airplanes available to the specified
	 * ticket agency through an HTTP GET request issued to the
	 * CS 509 server
	 * 
	 * @return An XML string listing all of the airplanes
	 */
	public String getAirplanes() {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		ConfigSingleton configSingleton = ConfigSingleton.getInstance();
		String team = configSingleton.get("team");
		
		try {
			// Create an HTTP connection to the server for a GET request
			url = new URL(mUrlBase + QueryFactory.getAirplanes(team));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			// If response code of SUCCESS is received read the XML string
			// returned line by line to build the full return string
			int responseCode = connection.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "URF-8" : encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	/**
	 * This method returns an XML list of all flights by
	 * retrieving the list of flights available for the
	 * specified team, departure airport, and day
	 * 
	 * @param airportCode Identifies the departure airport
	 * @param day Identifies the departure date
	 * @return An XML string listing flights available for the specified criteria
	 */
	public String getFlights(String airportCode, String day) {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		ConfigSingleton configSingleton = ConfigSingleton.getInstance();
		String team = configSingleton.get("team");
		
		try {
			// Create an HTTP connection to the server for a GET request
			url = new URL(mUrlBase + QueryFactory.getFlightsDeparting(team, airportCode, day));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", team);
			
			// If response code of SUCCESS is received read the XML string
			// returned line by line to build the full return string
			int responseCode = connection.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	/**
	 * This method is used to lock the database
	 * 
	 * @return true if database is successfully locked; false otherwise
	 */
	public boolean lock() {
		URL url;
		HttpURLConnection connection;
		ConfigSingleton configSingleton = ConfigSingleton.getInstance();
		String team = configSingleton.get("team");
		
		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", team);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String params = QueryFactory.lock(team);
			
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to lock database");
			System.out.println(("\nResponse Code : " + responseCode));
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
			
			System.out.println(response.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * This method is used to unlock the database
	 * 
	 * @return true if database is successfully unlocked; false otherwise
	 */
	public boolean unlock() {
		URL url;
		HttpURLConnection connection;
		ConfigSingleton configSingleton = ConfigSingleton.getInstance();
		String team = configSingleton.get("team");
		
		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			String params = QueryFactory.unlock(team);
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
		    
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to unlock database");
			System.out.println(("\nResponse Code : " + responseCode));

			if ((responseCode >= 200) && (responseCode <= 299)) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * This method is used to reserve a flight by posting a
	 * flight reservation to the database for an update
	 * 
	 * @param flightNumber The number of the flight being reserved
	 * @param seating The type of seating requested in the reservation
	 * @return true if successful, false otherwise
	 */
	public boolean buyTickets(String flightNumber, String seating) {
		
		ConfigSingleton configSingleton = ConfigSingleton.getInstance();
		String team = configSingleton.get("team");
		
		String xmlReservation = "<Flights>"
				+ "<Flight number=\"" + flightNumber + "\" seating=\""+ seating + "\"/>"
				+ "</Flights>";
		
		URL url;
		HttpURLConnection connection;

		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			String params = QueryFactory.reserve(team, xmlReservation);

			System.out.println("\nSending 'POST' to ReserveFlights");
			System.out.println("\nSending " + params);
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to ReserveFlights");
			System.out.println(("\nResponse Code : " + responseCode));

			if ((responseCode >= 200) && (responseCode <= 299)) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString());
				return true;
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString());
				return false;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
