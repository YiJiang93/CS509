package edu.wpi.cs509.team04.common;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.common.Airports;
import edu.wpi.cs509.team04.resources.ConfigSingleton;
import edu.wpi.cs509.team04.server.ServerInterface;

public class AirportsTest {

	@Test
	public void testaddAll() {
		ServerInterface resSys = ServerInterface.getInstance();
		String team = ConfigSingleton.getInstance().get("team");
		String xmlAirports = resSys.getAirports(team);
		Airports ports = new Airports();
		boolean AddSuccess=ports.addAll(xmlAirports);		
		assertTrue(AddSuccess);
	}

}
