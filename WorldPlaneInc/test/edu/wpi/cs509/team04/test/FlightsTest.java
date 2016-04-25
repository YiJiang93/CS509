package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.common.Flights;
import edu.wpi.cs509.team04.resources.ConfigSingleton;
import edu.wpi.cs509.team04.server.ServerInterface;

public class FlightsTest {

	@Test
	public void testaddAll() {
		ServerInterface resSys = ServerInterface.getInstance();
		String team = ConfigSingleton.getInstance().get("team");
		String xmlFlights = resSys.getFlights(team, "BOS", "2016_05_10");
		
		Flights flights = new Flights();
		boolean AddSuccess=flights.addAll(xmlFlights);
		assertTrue(AddSuccess);
	}

}
