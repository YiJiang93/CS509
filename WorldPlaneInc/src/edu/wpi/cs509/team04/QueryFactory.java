package edu.wpi.cs509.team04;

/**
 * QueryFactory Class 
 * 
 * Helper functions to create proper query for different operations 
 */
public class QueryFactory {

	/**
	 * create query to get list of all airports 
	 * 
	 * @param ticketAgency
	 * @return
	 */
	public static String getAirports(String ticketAgency) {
		return "?team=" + ticketAgency + "&action=list&list_type=airports";
	}
	
	
	/**
	 * Create query to get list of all airplanes  
	 * 
	 * @param ticketAgency
	 * @return
	 */
	public static String getAirplanes(String ticketAgency) {
		return "?team=" + ticketAgency + "&action=list&list_type=airplanes";
	}
	
	/**
	 * Create query to get list of departing flights from a airport on particular day
	 * 
	 * @param team
	 * @param airportCode
	 * @param day
	 * @return
	 */
	public static String getFlightsDeparting(String team, String airportCode, String day) {
		String query = "?team=" + team;
		query = query + "&action=list";
		query = query + "&list_type=departing";
		query = query + "&airport=" + airportCode;
		query = query + "&day=" + day;
		return query;
	}
	
	public static String getFlightsArriving(String team, String airportCode, String day) {
		String query = "?team=" + team;
		query = query + "&action=list";
		query = query + "&list_type=arriving";
		query = query + "&airport=" + airportCode;
		query = query + "&day=" + day;
		return query;
	}
	
	/**
	 * Create a query to lock the database 
	 * 
	 * @param ticketAgency
	 * @return
	 */
	public static String lock (String ticketAgency) {
		return "team=" + ticketAgency + "&action=lockDB";
	}
	
	
	/**
	 * Create a query to unlock the database
	 * 
	 * @param ticketAgency
	 * @return
	 */
	public static String unlock (String ticketAgency) {
		return "team=" + ticketAgency + "&action=unlockDB";
	}
	
	
	/**
	 * Create a query to reserve a flight
	 * 
	 * @param ticketAgency
	 * @param xmlFlights
	 * @return
	 */
	public static String reserve (String ticketAgency, String xmlFlights) {
		String query = "team=" + ticketAgency;
		query = query + "&action=buyTickets";
		query = query + "&flightData=" + xmlFlights;
		return query;
	}
}