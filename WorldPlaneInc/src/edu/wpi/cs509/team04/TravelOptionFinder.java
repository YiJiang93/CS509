/**
 * File: TravelOptionFinder.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04;

import java.text.ParseException;

/**
 * The TravelOptionFinder class provides a means for
 * asynchronously calling the ServerInterface in order
 * to find a set of travel options that match the specified
 * search criteria
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version April 4, 2016
 */
public class TravelOptionFinder implements Runnable {
	
	/**
	 * The 3-letter code of the departure airport
	 */
	private String departureCode;
	
	/**
	 * The 3-letter code of the arrival airport
	 */
	private String arrivalCode;
	
	/**
	 * The date during which the retrieved flights should be occurring
	 */
	private String day;
	
	/**
	 * The type of flight (i.e., from or to destination)
	 */
	private FlightType type;
	
	
	/**
	 * Constructor for the FlightRetrievalThread class
	 * @param departureCode The 3-letter code for the departure airport
	 * @param arrivalCode The 3-letter code for the arrival airport
	 * @param day The date during which the retrieved flights should be occurring
	 * @param type The type of flight (i.e., from or to destination)
	 */
	public TravelOptionFinder(String departureCode, String arrivalCode, String day, FlightType type) {
		this.departureCode = departureCode;
		this.arrivalCode = arrivalCode;
		this.day = day;
		this.type = type;
	}
	
	
	/**
	 * This method runs the ServerInterface method for acquiring
	 * flights from within the FlightRetrievalThread instance
	 */
	@Override
	public void run() {
		String team = ConfigSingleton.getInstance().get("team");
		ServerInterface resSys = ServerInterface.getInstance();
		try {
			Helper.getFlightList(resSys, team, departureCode, arrivalCode, day, type);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method launches the new thread for the FlightRetrievalThread
	 */
	public void getFlights() {
		TravelOptionFinder retriever = new TravelOptionFinder(departureCode, arrivalCode, day, type);
		Thread retrieverThread = new Thread(null, retriever, "flight retrieval thread");
		retrieverThread.start();
	}
}
