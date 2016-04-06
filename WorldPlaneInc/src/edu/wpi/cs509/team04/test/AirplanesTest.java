package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.Airplanes;
import edu.wpi.cs509.team04.ConfigSingleton;
import edu.wpi.cs509.team04.ServerInterface;

public class AirplanesTest {

	@Test
	public void testaddAll() {
		ServerInterface resSys = ServerInterface.getInstance();
		String team = ConfigSingleton.getInstance().get("team");
		String xmlAirplanes = resSys.getAirplanes(team);
		Airplanes planes = new Airplanes();
		boolean AddSuccess=planes.addAll(xmlAirplanes);		
		assertTrue(AddSuccess);
	}
	
	

}
