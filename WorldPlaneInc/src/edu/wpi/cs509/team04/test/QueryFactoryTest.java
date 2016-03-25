package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.QueryFactory;

public class QueryFactoryTest {

	@Test
	public void testGetAirports() {
		assertEquals("?team=XXXX&action=list&list_type=airports", QueryFactory.getAirports("XXXX"));
	}
	
	@Test
	public void testGetFlightsDeparting() {
		assertEquals("?team=XXXX&action=list&list_type=departing&airport=YYYY&day=ZZZZ", QueryFactory.getFlightsDeparting("XXXX", "YYYY", "ZZZZ"));
	}
	
	@Test
	public void testgetAirplanes() {
		assertEquals("?team=XXXX&action=list&list_type=airplanes", QueryFactory.getAirplanes("XXXX"));
	}
	
	@Test
	public void testlock() {
		assertEquals("team=XXXX&action=lockDB", QueryFactory.lock("XXXX"));
	}
	
	@Test
	public void testunlock() {
		assertEquals("team=XXXX&action=unlockDB", QueryFactory.unlock("XXXX"));			
	}
	
	@Test
	public void testreserve() {
		assertEquals("team=XXXX&action=buyTickets&flightData=YYYY", QueryFactory.reserve("XXXX","YYYY"));
	}
	

	
}
