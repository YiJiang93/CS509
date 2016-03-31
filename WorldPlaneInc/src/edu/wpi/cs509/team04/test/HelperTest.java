package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Test;

import edu.wpi.cs509.team04.Airplane;
import edu.wpi.cs509.team04.Airplanes;
import edu.wpi.cs509.team04.ConfigSingleton;
import edu.wpi.cs509.team04.Flight;
import edu.wpi.cs509.team04.Flights;
import edu.wpi.cs509.team04.Helper;
import edu.wpi.cs509.team04.SearchModel;
import edu.wpi.cs509.team04.ServerInterface;

public class HelperTest {

	@Test
	public void testGetAvailableSeats() {

		ServerInterface resSys = new ServerInterface();
		String team = ConfigSingleton.getInstance().get("team");
		

		//Create a sample Flight with now seats booked on a 737 (100 First Class seats and 28 Coach seats) 
		Flights flights = new Flights();
		String xmlFlights = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Flights><Flight Airplane=\"737\" FlightTime=\"25\" Number=\"2807\"><Departure><Code>BOS</Code><Time>2016 May 10 00:05 GMT</Time></Departure><Arrival><Code>LGA</Code><Time>2016 May 10 00:30 GMT</Time></Arrival><Seating><FirstClass Price=\"$67.11\">0</FirstClass><Coach Price=\"$18.79\">0</Coach></Seating></Flight></Flights>";
		flights.addAll(xmlFlights);
		
		Flight flight = flights.get(0);
		
		int maxFirstClass = 100;
		int maxCoach = 28;

		//Build seats construct
		Dictionary<String, Integer> seats = new Hashtable<String, Integer>(); 
		seats.put("FirstClassSeats", maxFirstClass);
		seats.put("CoachSeats", maxCoach);

		//Validate the seats construct is equal to the return of the method.
		assertEquals(seats, Helper.getAvailableSeats(flight, resSys, team));
	}

}
