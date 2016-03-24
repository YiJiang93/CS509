package edu.wpi.cs509.team04.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs509.team04.QueryFactory;

public class QueryFactoryTest {

	@Test
	public void test() {
		assertEquals("?team=XXXX&action=list&list_type=airports", QueryFactory.getAirports("XXXX"));
	}

	
}
