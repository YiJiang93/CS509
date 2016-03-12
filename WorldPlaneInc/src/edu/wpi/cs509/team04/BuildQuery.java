package edu.wpi.cs509.team04;

public class BuildQuery {

		public static String getAirports(String teamID){
			return "?team=" + teamID + "&action=list&list_type=airports";
		}
		
		public static String getAirplanes(String teamID) {
			return "?team=" + teamID + "&action=list&list_type=airplanes";
		}
		
		public static String getFlights(String teamID, String airportCode, String date){
			return "?team=" + teamID + "&action=list&list_type=departing&airport=" + airportCode + "&day=" + date;
		}
		
		public static String getLock(String teamID){
			return "?team=" + teamID + "&action=lockDB";
		}
		
		public static String releaseLock(String teamID){
			return "?team=" + teamID + "&action=unlockDB";
		}
		
		public static String reserveFlights(String teamID, String xmlFlightData){
			return "?team=" + teamID + "&action=buyTickets&flightData=" + xmlFlightData;
		}
}
