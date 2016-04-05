package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.xml.sax.InputSource;

import edu.wpi.cs509.team04.Airplanes;
import edu.wpi.cs509.team04.ConfigSingleton;
import edu.wpi.cs509.team04.ServerInterface;

public class AirplanesTest {

	@Test
	public void testaddAll() {
		ServerInterface resSys = new ServerInterface();
		String team = ConfigSingleton.getInstance().get("team");
		String xmlAirplanes = resSys.getAirplanes(team);
		Airplanes planes = new Airplanes();
		boolean AddSuccess=planes.addAll(xmlAirplanes);		
		assertTrue(AddSuccess);
	}
	
	

}
