package edu.wpi.cs509.team04.server;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.common.Flight;
import edu.wpi.cs509.team04.common.Flights;
import edu.wpi.cs509.team04.server.ServerInterface;

public class ServerInterfaceTest {

	@Test
	public void testGetAirports () {
		ServerInterface resSys = ServerInterface.getInstance();
		String xmlAirport = resSys.getAirports();
		assertTrue(xmlAirport.length() > 6000);
	}
	
	@Test
	public void testgetAirplanes(){
		ServerInterface resSys = ServerInterface.getInstance();
		String xmlAirplanes = resSys.getAirplanes();
		assertTrue(xmlAirplanes.length()>1000);
		
	}

	@Test
	public void testgetFlights(){
		ServerInterface resSys = ServerInterface.getInstance();
		String xmlFlights = resSys.getFlights("BOS", "2016_05_10");
		assertTrue(xmlFlights.length()>0);
	}

	@Test
	public void testlock(){
		ServerInterface resSys = ServerInterface.getInstance();
		boolean locksuccess = resSys.lock();
		assertTrue(locksuccess);	
	}
	
	@Test
	public void testbuyTickets(){
		ServerInterface resSys = ServerInterface.getInstance();
		Flights flights = new Flights();
		Flight flight = flights.get(11);
		String flightNumber = flight.getmNumber();
		
		resSys.lock();	
		boolean BuySuccess = resSys.buyTickets(flightNumber, "Coach");
		resSys.unlock();
		assertTrue(BuySuccess);	
	}
}
