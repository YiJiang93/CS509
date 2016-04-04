package edu.wpi.cs509.team04;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Helper {
	
	/**
	 * Return Dictionary<String, Integer> pair with:
	 *    keys: "FirstClassSeats, CoachSeats"
	 *    values: Number of First Class Seats Available, Number of Coach Seats Available
	 * 
	 * Retrieve the list of airplanes from the server, find the airplane object for the plane on the
	 * 	given flight, and store maximum seats for the plane.  Calculate the available seats for both
	 * 	First Class and Coach seats and store in a Dictionary.  Return the Dictionary.
	 * 	
	 * 
	 * @param flight identifies the selected flight to match the airplane.
	 * @param resSys identifies the Server Interface.
	 * @param team identifies the team instance on the database to connect.
	 * @return Dictionary<String, Integer> with available seat information
	 */
	public static Dictionary<String, Integer> getAvailableSeats(Flight flight, ServerInterface resSys, String team) {

		//Get airplane data
		String xmlAirplanes = resSys.getAirplanes(team);
		
		// Create the aggregate flights
		Airplanes planes = new Airplanes();
		planes.addAll(xmlAirplanes);
		
		int maxFirstClass = 0;
		int bookedFirstClass = flight.getmSeatsFirstclass();
		int maxCoach = 0;
		int bookedCoach = flight.getmSeatsCoach();
		
		for(int i=0; i< planes.size(); i++){
			Airplane plane = planes.get(i);
			if(plane.model().equals(flight.getmAirplane())) {
				maxFirstClass = plane.firstclass();
				maxCoach = plane.coach();
				break;
			}
		}

		Dictionary<String, Integer> seats = new Hashtable<String, Integer>(); 

		seats.put("FirstClassSeats", maxFirstClass - bookedFirstClass);
		seats.put("CoachSeats", maxCoach - bookedCoach);

		return seats;
	}
	
	public static List<Dictionary<String, Flight>> testing(ServerInterface resSys, String team) {
		
		String departCode = "BOS";
		String arriveCode = "ATL";
		String departDate = "2016_05_10";

		String xmlFlights = resSys.getFlights(team, departCode, departDate);
		Flights flights = new Flights();
		Flight nullFlight = new Flight("", "", "", "", "", "", "", "", 0, "", 0);

		// Create the aggregate flights
		flights = new Flights();
		flights.addAll(xmlFlights);

		List<Dictionary<String, Flight>> flightArray = new ArrayList<Dictionary<String, Flight>>();

		//for(int i=0; i<flights.size() - 1; i++) {
		for(int i=0; i < 1; i++) {
			System.out.println("FlightList 1, Flight " + i);
			Flight flight = flights.get(i);
			if(flight.getmCodeArrival().equals(arriveCode)) {
				Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>(); 
				flightStruct.put("First", flight);
				flightStruct.put("Second",  nullFlight);
				flightStruct.put("Third", nullFlight);
				flightArray.add(flightStruct);
			} else {
				String departCode2 = flight.getmCodeArrival();
				String arriveTime2 = flight.getmTimeArrival();
				String xmlFlights2 = resSys.getFlights(team, departCode2, departDate);
				Flights flights2 = new Flights();
				flights2 = new Flights();
				flights2.addAll(xmlFlights2);
				for(int j=0; j<flights2.size() - 1; j++) {
					System.out.println("FlightList 2, Flight " + j);
					Flight flight2 = flights2.get(j);
					//if(arriveTime2 + 1 <= flight2.getmTimeDepart() && flight2.getmTimeDepart() <= arriveTime2 + 3))
					//if(flight2.getmArrivalCode().equals(departCode)) IGNORE
					if(flight2.getmCodeArrival().equals(arriveCode)) {
						Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>(); 
						flightStruct.put("First", flight);
						flightStruct.put("Second",  flight2);
						flightStruct.put("Third", nullFlight);
						flightArray.add(flightStruct);
					} else {
						String departCode3 = flight.getmCodeArrival();
						String arriveTime3 = flight.getmTimeArrival();
						String xmlFlights3 = resSys.getFlights(team, departCode3, departDate);
						Flights flights3 = new Flights();
						flights3 = new Flights();
						flights3.addAll(xmlFlights3);
						for(int k=0; k < flights3.size() - 1; k++) {
							System.out.println("FlightList 3, Flight " + k);
							Flight flight3 = flights3.get(k);
							//if(arriveTime3 + 1 <= flight3.getmTimeDepart() && flight3.getmTimeDepart() <= arriveTime3 + 3))
							if(flight3.getmCodeArrival().equals(arriveCode)) {
								Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>(); 
								flightStruct.put("First", flight);
								flightStruct.put("Second",  flight2);
								flightStruct.put("Third", flight3);
								flightArray.add(flightStruct);
							}
						}
					}
				}
			}
		}

		return flightArray;
	}
}
