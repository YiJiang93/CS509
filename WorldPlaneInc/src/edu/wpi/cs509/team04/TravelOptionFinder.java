/**
 * File: TravelOptionFinder.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

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
	 * The type of travel
	 */
	private TravelType type;
	
	/**
	 * 
	 */
	private String toDay;
	
	/**
	 * 
	 */
	private String fromDay;
	
	
	/**
	 * Constructor for the TravelOptionFinder class
	 */
	public TravelOptionFinder() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
		SearchModel searchModel = SearchModel.getInstance();
		this.departureCode = searchModel.getDepartureAirport();
		this.arrivalCode = searchModel.getArrivalAirport();
		this.toDay = dateFormat.format(searchModel.getFirstDepartureDate());
		this.fromDay = dateFormat.format(searchModel.getSecondDepartureDate());
		this.type = searchModel.getTravelType();
	}
	
	
	/**
	 * This method runs the ServerInterface method for acquiring
	 * flights from within the FlightRetrievalThread instance
	 */
	@Override
	public void run() {
		String team = ConfigSingleton.getInstance().get("team");
		try {
			SearchModel searchModel = SearchModel.getInstance();
			if (type == TravelType.ROUND_TRIP) {
				List<Dictionary<String, Flight>> to = Helper.getFlightList(team, departureCode, arrivalCode, toDay);
				List<Dictionary<String, Flight>> from = Helper.getFlightList(team, arrivalCode, departureCode, fromDay);
				Collection<TravelOption> toOptions = new ArrayList<TravelOption>();
				Collection<TravelOption> fromOptions = new ArrayList<TravelOption>();
				for (Dictionary<String, Flight> option : to) {
					Flight initial = option.get("First");
					Flight firstLayover = option.get("Second");
					Flight secondLayover = option.get("Third");
					toOptions.add(new TravelOption(initial, firstLayover, secondLayover));
				}
				for (Dictionary<String, Flight> option : from) {
					Flight initial = option.get("First");
					Flight firstLayover = option.get("Second");
					Flight secondLayover = option.get("Third");
					fromOptions.add(new TravelOption(initial, firstLayover, secondLayover));
				}
				searchModel.setToDestinationTravelOptions(toOptions);
				searchModel.setFromDestinationTravelOptions(fromOptions);
			}
			if (type == TravelType.ONE_WAY) {
				List<Dictionary<String, Flight>> to = Helper.getFlightList(team, departureCode, arrivalCode, toDay);
				Collection<TravelOption> options = new ArrayList<TravelOption>();
				for (Dictionary<String, Flight> option : to) {
					Flight initial = option.get("First");
					Flight firstLayover = option.get("Second");
					Flight secondLayover = option.get("Third");
					options.add(new TravelOption(initial, firstLayover, secondLayover));
				}
				searchModel.setToDestinationTravelOptions(options);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method launches the new thread for the FlightRetrievalThread
	 */
	public void getFlights() {
		TravelOptionFinder retriever = new TravelOptionFinder();
		Thread retrieverThread = new Thread(null, retriever, "flight retrieval thread");
		retrieverThread.start();
	}
}
