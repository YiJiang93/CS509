package edu.wpi.cs509.team04;

import java.util.Dictionary;
import java.util.Hashtable;

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

}
