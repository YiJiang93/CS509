/**
 * File: QueryFactory.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04;

/**
 * The QueryFactory class provides a means for constructing
 * queries that can be understood by the CS 509 database
 * @version April 4, 2016
 */
public class QueryFactory {
	
	
	/**
	 * This method constructs a query in order to
	 * acquire a list of airports from the database 
	 * 
	 * @param ticketAgency The ticket agency making the query
	 * @return The query string for acquiring the list of airports
	 */
	public static String getAirports(String ticketAgency) {
		return "?team=" + ticketAgency + "&action=list&list_type=airports";
	}
	
	
	/**
	 * This method constructs a query in order to
	 * acquire a list of airplanes from the database
	 * 
	 * @param ticketAgency The ticket agency making the query
	 * @return The query string for acquiring the list of airplanes
	 */
	public static String getAirplanes(String ticketAgency) {
		return "?team=" + ticketAgency + "&action=list&list_type=airplanes";
	}
	
	
	/**
	 * This method constructs a query for getting a
	 * list of departing flights from an airport on
	 * a particular day
	 * 
	 * @param team The ticket agency making the query
	 * @param airportCode The 3-letter departure airport code
	 * @param day The day of departure
	 * @return The query string for acquiring the list of departing flights
	 */
	public static String getFlightsDeparting(String team, String airportCode, String day) {
		String query = "?team=" + team;
		query = query + "&action=list";
		query = query + "&list_type=departing";
		query = query + "&airport=" + airportCode;
		query = query + "&day=" + day;
		return query;
	}
	
	
	/**
	 * This method constructs a query for getting a
	 * list of arriving flights from an airport on
	 * a particular day
	 * 
	 * @param team The ticket agency making the query
	 * @param airportCode The 3-letter arrival airport code
	 * @param day The day of departure
	 * @return The query string for acquiring the list of arriving flights
	 */
	public static String getFlightsArriving(String team, String airportCode, String day) {
		String query = "?team=" + team;
		query = query + "&action=list";
		query = query + "&list_type=arriving";
		query = query + "&airport=" + airportCode;
		query = query + "&day=" + day;
		return query;
	}
	
	/**
	 * This method constructs a query for locking the database
	 * @param ticketAgency The ticket agency making the query
	 * @return The query string for locking the database
	 */
	public static String lock(String ticketAgency) {
		return "team=" + ticketAgency + "&action=lockDB";
	}
	
	
	/**
	 * This method constructs a query for unlocking the database
	 * @param ticketAgency The ticket agency making the query
	 * @return The query string for unlocking the database
	 */
	public static String unlock(String ticketAgency) {
		return "team=" + ticketAgency + "&action=unlockDB";
	}
	
	
	/**
	 * This method constructs a query for reserving a flight
	 * @param ticketAgency The ticket agency making the query
	 * @param xmlFlights The XML string of flights to reserve
	 * @return The query string for reserving a flight
	 */
	public static String reserve(String ticketAgency, String xmlFlights) {
		String query = "team=" + ticketAgency;
		query = query + "&action=buyTickets";
		query = query + "&flightData=" + xmlFlights;
		return query;
	}
}