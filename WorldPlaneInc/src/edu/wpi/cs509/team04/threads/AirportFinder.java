package edu.wpi.cs509.team04.threads;

import java.util.Dictionary;
import java.util.Hashtable;

import edu.wpi.cs509.team04.common.Airport;
import edu.wpi.cs509.team04.common.Airports;
import edu.wpi.cs509.team04.server.ServerInterface;

public class AirportFinder {
	
	public static AirportFinder instance = null;
	private Dictionary<String, Airport> airports = new Hashtable<String, Airport>();
	
	public static AirportFinder getInstance() {
		if (instance == null) {
			instance = new AirportFinder();
		}
		return instance;
	}
	
	private AirportFinder() {
		ServerInterface serverInterface = ServerInterface.getInstance();
		String xmlAirport = serverInterface.getAirports();
		Airports ports = new Airports();
		ports.addAll(xmlAirport);
		for (Airport airport : ports) {
			airports.put(airport.code(), airport);
		}
	}
	
	public Airport getAirport(String code) {
		return airports.get(code);
	}
}
