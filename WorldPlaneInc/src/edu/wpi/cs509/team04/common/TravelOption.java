/**
 * File: TravelOption.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.cs509.team04.enums.SeatingType;
import edu.wpi.cs509.team04.threads.AirportFinder;

/**
 * The TravelOption class provides a means for storing an initial
 * flight and its associated one or two lay-over flights
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 28, 2016
 */
public class TravelOption {
	
	/**
	 * The initial flight in this option
	 */
	private Flight initialFlight;
	
	/**
	 * The first lay-over flight in this option
	 */
	private Flight firstLayover;
	
	/**
	 * The second lay-over flight in this option
	 */
	private Flight secondLayover;
	
	
	/**
	 * Constructor for the FlightOption class
	 * @param flight The initial flight in this option
	 * @param layover1 The first lay-over flight in this option 
	 * @param layover2 The second lay-over flight in this option
	 */
	public TravelOption(Flight flight, Flight layover1, Flight layover2) {
		this.initialFlight = flight;
		this.firstLayover = layover1;
		this.secondLayover = layover2;
	}
	
	
	/**
	 * This method acquires the first flight in the TravelOption
	 * @return The first flight in the TravelOption
	 */
	public Flight getInitialFlight() {
		return initialFlight;
	}
	
	
	/**
	 * This method acquires the first lay-over in the TravelOption
	 * @return The first lay-over flight in the TravelOption
	 */
	public Flight getFirstLayover() {
		return firstLayover;
	}
	
	
	/**
	 * This method acquires the second lay-over in the TravelOption
	 * @return The second lay-over flight in the TravelOption
	 */
	public Flight getSecondLayover() {
		return secondLayover;
	}
	
	
	/**
	 * This method produces an HTML-formatted string representing
	 * the information contained within this TravelOption
	 * @return An HTML-formatted string containing the TravelOption information
	 */
	@SuppressWarnings("deprecation")
	public String toHtmlString() {
		Flight nullFlight = new Flight("", "0", "", "", "", "", "", "0.0", 0, "0.0", 0);
		String infoString = "";
		AirportFinder finder = AirportFinder.getInstance();
		
		infoString += "<html><hr>";
		if (!initialFlight.equals(nullFlight)) {
			Airport arrival = finder.getAirport(initialFlight.getmCodeArrival());
			Airport depart = finder.getAirport(initialFlight.getmCodeDepart());
			String arrivalRaw = initialFlight.getmTimeArrival();
			Date arrivalDate = new Date(arrivalRaw);
			Date arrivalTime = LocalTime.Convert(arrival, arrivalDate);
			String departureRaw = initialFlight.getmTimeDepart();
			Date departureDate = new Date(departureRaw);
			Date departureTime = LocalTime.Convert(depart, departureDate);
			String arrivalTimeString = arrivalTime.toString();
			String departTimeString = departureTime.toString();
			
			infoString += "Depart";
			infoString += "<hr>";
			infoString += departTimeString;
			infoString += " - " + arrivalTimeString;
			infoString += " (" + initialFlight.getmFlightTime() + ")";
			infoString += "<br>";
			infoString += "(" + initialFlight.getmCodeDepart() + ") - (";
			infoString += initialFlight.getmCodeArrival() + ")";
			infoString += "<br>";
			infoString += "Flight # " + initialFlight.getmNumber() + " , ";
			infoString += initialFlight.getmAirplane() + "<br>";
			infoString += "First class = " + initialFlight.getmPriceFirstclass();
			infoString += " , Coach = " + initialFlight.getmPriceCoach() + "<br>";
			infoString += "Coach Seats = " + initialFlight.getmSeatsCoach() + " , ";
			infoString += "First Class Seats = " + initialFlight.getmSeatsFirstclass();
			infoString += "<hr>";
		} 
		if (!firstLayover.equals(nullFlight)) {
			Airport arrival = finder.getAirport(firstLayover.getmCodeArrival());
			Airport depart = finder.getAirport(firstLayover.getmCodeDepart());
			String arrivalRaw = firstLayover.getmTimeArrival();
			Date arrivalDate = new Date(arrivalRaw);
			Date arrivalTime = LocalTime.Convert(arrival, arrivalDate);
			String departureRaw = firstLayover.getmTimeDepart();
			Date departureDate = new Date(departureRaw);
			Date departureTime = LocalTime.Convert(depart, departureDate);
			String arrivalTimeString = arrivalTime.toString();
			String departTimeString = departureTime.toString();
			
			infoString += "Change planes at (" + firstLayover.getmCodeDepart() + ")";
			infoString += "<hr>";
			infoString += departTimeString;
			infoString += " - " + arrivalTimeString;
			infoString += " (" + firstLayover.getmFlightTime() + ")";
			infoString += "<br>";
			infoString += "(" + firstLayover.getmCodeDepart() + ") - (";
			infoString += firstLayover.getmCodeArrival() + ")";
			infoString += "<br>";
			infoString += "Flight # " + firstLayover.getmNumber() + " , ";
			infoString += firstLayover.getmAirplane() + "<br>";
			infoString += "First class = " + firstLayover.getmPriceFirstclass();
			infoString += " , Coach = " + firstLayover.getmPriceCoach() + "<br>";
			infoString += "Coach Seats = " + firstLayover.getmSeatsCoach() + " , ";
			infoString += "First Class Seats = " + firstLayover.getmSeatsFirstclass();
			infoString += "<hr>";
		}
		if (!(firstLayover.equals(nullFlight)) && !(secondLayover.equals(nullFlight))) {
			Airport arrival = finder.getAirport(secondLayover.getmCodeArrival());
			Airport depart = finder.getAirport(secondLayover.getmCodeDepart());
			String arrivalRaw = secondLayover.getmTimeArrival();
			Date arrivalDate = new Date(arrivalRaw);
			Date arrivalTime = LocalTime.Convert(arrival, arrivalDate);
			String departureRaw = secondLayover.getmTimeDepart();
			Date departureDate = new Date(departureRaw);
			Date departureTime = LocalTime.Convert(depart, departureDate);
			String arrivalTimeString = arrivalTime.toString();
			String departTimeString = departureTime.toString();
			
			infoString += "Change planes at (" + secondLayover.getmCodeDepart() + ")";
			infoString += "<hr>";
			infoString += departTimeString;
			infoString += " - " + arrivalTimeString;
			infoString += " (" + secondLayover.getmFlightTime() + ")";
			infoString += "<br>";
			infoString += "(" + secondLayover.getmCodeDepart() + ") - (";
			infoString += secondLayover.getmCodeArrival() + ")";
			infoString += "<br>";
			infoString += "Flight # " + secondLayover.getmNumber() + " , ";
			infoString += secondLayover.getmAirplane() + "<br>";
			infoString += "First class = " + secondLayover.getmPriceFirstclass();
			infoString += " , Coach = " + secondLayover.getmPriceCoach() + "<br>";
			infoString += "Coach Seats = " + secondLayover.getmSeatsCoach() + " , ";
			infoString += "First Class Seats = " + secondLayover.getmSeatsFirstclass();
			infoString += "<hr>";
		}
		infoString += "</html>";
		return infoString;
	}
	
	
	/**
	 * This method acquires the price of the TravelOption
	 * @param The type of seating for which price is being computed
	 * @return The price of the TravelOption
	 */
	public Float getPrice(SeatingType type) {
		Float travelOptionCost = new Float(0);
		if (initialFlight != null) {
			if (type == SeatingType.COACH) {
				String coachPriceString = initialFlight.getmPriceCoach();
				coachPriceString = coachPriceString.replaceAll("$", "");
				Float coachPrice = Float.parseFloat(coachPriceString);
				travelOptionCost += coachPrice;
			}
			if (type == SeatingType.FIRST_CLASS) {
				String firstClassPriceString = initialFlight.getmPriceFirstclass();
				firstClassPriceString = firstClassPriceString.replace("$", "");
				Float firstClassPrice = Float.parseFloat(firstClassPriceString);
				travelOptionCost += firstClassPrice;
			}
		}
		if (firstLayover != null) {
			if (type == SeatingType.COACH) {
				String coachPriceString = firstLayover.getmPriceCoach();
				coachPriceString = coachPriceString.replaceAll("$", "");
				Float coachPrice = Float.parseFloat(coachPriceString);
				travelOptionCost += coachPrice;
			}
			if (type == SeatingType.FIRST_CLASS) {
				String firstClassPriceString = firstLayover.getmPriceFirstclass();
				firstClassPriceString = firstClassPriceString.replace("$", "");
				Float firstClassPrice = Float.parseFloat(firstClassPriceString);
				travelOptionCost += firstClassPrice;
			}
		}
		if (secondLayover != null) {
			if (type == SeatingType.COACH) {
				String coachPriceString = secondLayover.getmPriceCoach();
				coachPriceString = coachPriceString.replaceAll("$", "");
				Float coachPrice = Float.parseFloat(coachPriceString);
				travelOptionCost += coachPrice;
			}
			if (type == SeatingType.FIRST_CLASS) {
				String firstClassPriceString = secondLayover.getmPriceFirstclass();
				firstClassPriceString = firstClassPriceString.replace("$", "");
				Float firstClassPrice = Float.parseFloat(firstClassPriceString);
				travelOptionCost += firstClassPrice;
			}
		}
		return travelOptionCost;
	}
	
	public long getDuration() {
		Flight nullFlight = new Flight("", "0", "", "", "", "", "", "0.0", 0, "0.0", 0);
		long flightTime = 0;
		DateFormat format = new SimpleDateFormat("YYYY MMM DD HH:mm Z");
		
		Date timeDepart1 = new Date();
		Date timeArrive1 = new Date();
		Date timeDepart2 = new Date();
		Date timeArrive2 = new Date();
		Date timeDepart3 = new Date();
		Date timeArrive3 = new Date();
		
		try {
			if (!this.initialFlight.equals(nullFlight)) {
				timeDepart1 = format.parse(initialFlight.getmTimeDepart());
				timeArrive1 = format.parse(initialFlight.getmTimeArrival());
				flightTime += timeArrive1.getTime() - timeDepart1.getTime(); 
			}
			if (!this.firstLayover.equals(nullFlight)) {
				timeDepart2 = format.parse(initialFlight.getmTimeDepart());
				timeArrive2 = format.parse(initialFlight.getmTimeArrival());
				flightTime += timeArrive2.getTime() - timeDepart2.getTime();
				flightTime += timeDepart2.getTime() - timeArrive1.getTime();
			}
			if (!this.secondLayover.equals(nullFlight)) {
				timeDepart3 = format.parse(initialFlight.getmTimeDepart());
				timeArrive3 = format.parse(initialFlight.getmTimeArrival());
				flightTime += timeArrive3.getTime() - timeDepart3.getTime();
				flightTime += timeDepart3.getTime() - timeArrive2.getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return flightTime;
	}
}
