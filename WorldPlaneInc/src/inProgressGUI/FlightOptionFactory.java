/**
 * File: FlightOptionFactory.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The FlightOptionFactory class is used for producing
 * lists of FlightOptions when provided with a list of
 * Flights for input
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 28, 2016
 */
public class FlightOptionFactory {

	/**
	 * The singleton instance of the FlightOptionFactory
	 */
	public static FlightOptionFactory instance = null;
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static FlightOptionFactory getInstance() {
		if (instance == null) {
			instance = new FlightOptionFactory();
		}
		return instance;
	}
	
	
	/**
	 * Private constructor for the FlightOptionFactory
	 */
	private FlightOptionFactory() {
		
	}
	
	
	/**
	 * This method constructs a collection of
	 * FlightOptions from a collection of flights
	 * 
	 * @param flights A collection of flights
	 * @return A collection of FlightOptions
	 */
	public Collection<FlightOption> buildFlightOptions(Collection<String> flights) {
		Collection<FlightOption> flightOptions = new ArrayList<FlightOption>();
		for (String flight : flights) {
			System.out.println(flight);
			// build a new flight option
			// set the information in the flight option accordingly
		}
		return flightOptions;
	}
}
