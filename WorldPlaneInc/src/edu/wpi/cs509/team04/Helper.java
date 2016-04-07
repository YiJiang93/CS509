package edu.wpi.cs509.team04;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	/**
	 * Return String in format HH:MM
	 * 
	 * Given a DepartTime or ArrivalTime from a Flight and an Integer of number of hours to adjust the time,
	 *  split the Strings and add the offset.  Return the String in format HH:MM.
	 * 
	 * @param time identifies the time from a flight object.
	 * @param offset identifies the number of hours to modify the time.
	 * @return String in format HH:MM
	 */
	private static String adjustTime(String time, int offset){
		
		//Break String into Array
		String[] timeArray = time.split(" ");
		
		//Get just the hours and minutes
		String[] hm = timeArray[3].split(":");
		
		//Adjust the time
		hm[0] = Integer.toString(Integer.parseInt(hm[0]) + offset);
		
		//Return HH:MM
		return String.join(":", hm);
	}
	
	/**
	 * Return List<Dictionary<String, Flight>> pair with:
	 *    keys: "First, Second, Third"
	 *    values: Flight Object for the leg of the trip, or a null Flight.
	 * 
	 * Retrieve the list of possible trips from departure airport to arrival airport with up to two stop-overs.
	 *  At least one hour after first flight lands before the next flight leaves, and no more than three hours.
	 *  If the flight does not have two or three legs, null objects are returned.
	 * 
	 * @param team identifies the team instance on the database to connect.
	 * @param departCode identifies the Departure Airport Code.
	 * @param arriveCode identifies the final Arrival Airport Code.
	 * @param departDate identifies the Departure Date as YYYY_MM_DD.
	 * @return List<Dictionary<String, Flight>> with list of flights.
	 */
	public static List<Dictionary<String, Flight>> getFlightList(String team, String departCode, String arriveCode, String departDate) throws ParseException {
		
		ServerInterface resSys = ServerInterface.getInstance();
		String xmlFlights = resSys.getFlights(team, departCode, departDate);
		Flights flights = new Flights();
		Flight nullFlight = new Flight("", "", "", "", "", "", "", "", 0, "", 0);

		// Create the aggregate flights
		flights = new Flights();
		flights.addAll(xmlFlights);

		List<Dictionary<String, Flight>> flightArray = new ArrayList<Dictionary<String, Flight>>();

		DateFormat f = new SimpleDateFormat("hh:mm");
		for(int i=0; i<flights.size() - 1; i++) {
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
				Date departTime2min = f.parse(adjustTime(arriveTime2, 1));
				Date departTime2max = f.parse(adjustTime(arriveTime2, 3));
				String xmlFlights2 = resSys.getFlights(team, departCode2, departDate);
				Flights flights2 = new Flights();
				flights2 = new Flights();
				flights2.addAll(xmlFlights2);
				for(int j=0; j<flights2.size() - 1; j++) {
					System.out.println("FlightList 2, Flight " + j);
					Flight flight2 = flights2.get(j);
					Date departTime2 = f.parse(adjustTime(flight2.getmTimeDepart(), 0));
					if(departTime2min.getTime() <= departTime2.getTime() && departTime2.getTime() <= departTime2max.getTime()){
						//if(flight2.getmArrivalCode().equals(departCode)) IGNORE
						if(flight2.getmCodeArrival().equals(arriveCode)) {
							Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>(); 
							flightStruct.put("First", flight);
							flightStruct.put("Second",  flight2);
							flightStruct.put("Third", nullFlight);
							flightArray.add(flightStruct);
						} else {
							String departCode3 = flight2.getmCodeArrival();
							String arriveTime3 = flight2.getmTimeArrival();
							Date departTime3min = f.parse(adjustTime(arriveTime3, 1));
							Date departTime3max = f.parse(adjustTime(arriveTime3, 3));
							String xmlFlights3 = resSys.getFlights(team, departCode3, departDate);
							Flights flights3 = new Flights();
							flights3 = new Flights();
							flights3.addAll(xmlFlights3);
							for(int k=0; k < flights3.size() - 1; k++) {
								System.out.println("FlightList 3, Flight " + k);
								Flight flight3 = flights3.get(k);
								Date departTime3 = f.parse(adjustTime(flight3.getmTimeDepart(), 0));
								if(departTime3min.getTime() <= departTime3.getTime() && departTime3.getTime() <= departTime3max.getTime()){
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
			}
		}
		return flightArray;
	}
}
