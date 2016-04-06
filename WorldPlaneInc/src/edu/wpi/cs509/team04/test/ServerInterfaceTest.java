package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.ConfigSingleton;
import edu.wpi.cs509.team04.Flight;
import edu.wpi.cs509.team04.Flights;
import edu.wpi.cs509.team04.ServerInterface;

public class ServerInterfaceTest {

	@Test
	public void testGetAirports () {
		ServerInterface resSys = ServerInterface.getInstance();
		String team = ConfigSingleton.getInstance().get("team");
		
		String xmlAirport = resSys.getAirports(team);
		assertTrue(xmlAirport.length() > 6000);
	}
	
	@Test
	public void testgetAirplanes(){
		ServerInterface resSys = ServerInterface.getInstance();
		String team = ConfigSingleton.getInstance().get("team");
		
		String xmlAirplanes = resSys.getAirplanes(team);
		assertTrue(xmlAirplanes.length()>1000);
		
	}

	@Test
	public void testgetFlights(){
		ServerInterface resSys = ServerInterface.getInstance();
		String team = ConfigSingleton.getInstance().get("team");
		
		String xmlFlights = resSys.getFlights(team, "BOS", "2016_05_10");
		assertTrue(xmlFlights.length()>0);
	}

	@Test
	public void testlock(){
		ServerInterface resSys = ServerInterface.getInstance();
		String team = ConfigSingleton.getInstance().get("team");
			
		boolean locksuccess = resSys.lock(team);
		assertTrue(locksuccess);	
	}
	
	@Test
	public void testbuyTickets(){
		ServerInterface resSys = ServerInterface.getInstance();
		String team = ConfigSingleton.getInstance().get("team");
		Flights flights = new Flights();
		Flight flight = flights.get(11);
		String flightNumber = flight.getmNumber();
		
		resSys.lock(team);	
		boolean BuySuccess = resSys.buyTickets(team, flightNumber, "Coach");
		resSys.unlock(team);
		assertTrue(BuySuccess);	
	}


	

}
