package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import edu.wpi.cs509.team04.Airplanes;
import edu.wpi.cs509.team04.Airports;
import edu.wpi.cs509.team04.ConfigSingleton;
import edu.wpi.cs509.team04.ServerInterface;

public class AirportsTest {

	@Test
	public void testaddAll() {
		ServerInterface resSys = new ServerInterface();
		String team = ConfigSingleton.getInstance().get("team");
		String xmlAirports = resSys.getAirports(team);
		Airports ports = new Airports();
		boolean AddSuccess=ports.addAll(xmlAirports);		
		assertTrue(AddSuccess);
	}

}
