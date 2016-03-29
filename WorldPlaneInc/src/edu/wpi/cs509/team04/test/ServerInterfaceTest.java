package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.ConfigSingleton;
import edu.wpi.cs509.team04.QueryFactory;
import edu.wpi.cs509.team04.ServerInterface;

public class ServerInterfaceTest {

	@Test
	public void testGetAirports () {
		ServerInterface resSys = new ServerInterface();
		String team = ConfigSingleton.getInstance().get("team");
		
		String xmlAirport = resSys.getAirports(team);
		assertTrue(xmlAirport.length() > 6000);
	}


	

}
