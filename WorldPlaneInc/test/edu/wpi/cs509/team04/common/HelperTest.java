package edu.wpi.cs509.team04.common;

import static org.junit.Assert.*;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Test;

import edu.wpi.cs509.team04.common.Flight;
import edu.wpi.cs509.team04.common.Flights;
import edu.wpi.cs509.team04.threads.Helper;

public class HelperTest {

	@Test
	public void testGetAvailableSeats() {
		
		//Create a sample Flight with no seats booked on a 737 (100 First Class seats and 28 Coach seats) 
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
		assertEquals(seats, Helper.getAvailableSeats(flight));
	}
	
	@Test
	public void testAreSeatsAvailable_Empty() {

		//Build seats construct with 24 First Class Seats available and 100 Coach Seats
		Dictionary<String, Integer> seats = new Hashtable<String, Integer>(); 
		seats.put("FirstClassSeats", 24);
		seats.put("CoachSeats", 100);
		
		System.out.println("\nType of Seating: First Class");
		System.out.println("Number of Seats available: " + seats.get("FirstClassSeats"));
		System.out.println("Should return True \n");

		//assertTrue(Helper.areSeatsAvailable(seats, "First Class"));
		
		System.out.println("Type of Seating: Coach");
		System.out.println("Number of Seats available: " + seats.get("CoachSeats"));
		System.out.println("Should return True");
		//assertTrue(Helper.areSeatsAvailable(seats, "Coach"));

	}

	@Test
	public void testAreSeatsAvailable_Full() {

		//Build seats construct with 0 First Class Seats available and 0 Coach Seats
		Dictionary<String, Integer> seats = new Hashtable<String, Integer>(); 
		seats.put("FirstClassSeats", 0);
		seats.put("CoachSeats", 0);
		
		System.out.println("\nType of Seating: First Class");
		System.out.println("Number of Seats available: " + seats.get("FirstClassSeats"));
		System.out.println("Should return False \n");

		//assertFalse(Helper.areSeatsAvailable(seats, "First Class"));
		
		System.out.println("Type of Seating: Coach");
		System.out.println("Number of Seats available: " + seats.get("CoachSeats"));

		//assertTrue(Helper.areSeatsAvailable(seats, "Coach"));
		System.out.println("Should return False");
		//assertFalse(Helper.areSeatsAvailable(seats, "Coach"));
	}
}
