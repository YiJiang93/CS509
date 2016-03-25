package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.ConfigSingleton;
import edu.wpi.cs509.team04.QueryFactory;
import edu.wpi.cs509.team04.ServerInterface;

public class ServerInterfaceTest {

	@Test
	public void testgetAirports () {
		ServerInterface servInter = new ServerInterface();
		servInter.getAirports(ConfigSingleton.getInstance().get("team"));
		assertEquals(result.toString(), servInter.getAirports(ConfigSingleton.getInstance().get("team")));
	}


	

}
