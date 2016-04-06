/**
 * File: ReservationModel.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI.models;

import inProgressGUI.common.SeatingType;

/**
 * The ReservationModel class provides a means for maintaining
 * information on the state of the ReservationView
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class ReservationModel {

	/**
	 * Singleton instance of the ReservationModel
	 */
	private static ReservationModel instance = null;
	
	/**
	 * Indication of the type of seating for
	 * the initial to-destination flight
	 */
	private SeatingType initialToDestinationFlight;
	
	/**
	 * Indication of the type of seating for the
	 * first lay-over to-destination flight
	 */
	private SeatingType firstLayoverToDestinationFlight;
	
	/**
	 * Indication of the type of seating for the
	 * second lay-over to-destination flight
	 */
	private SeatingType secondLayoverToDestinationFlight;
	
	/**
	 * Indication of the type of seating for the
	 * initial from-destination flight
	 */
	private SeatingType initialFromDestinationFlight;
	
	/**
	 * Indication of the type of seating for the
	 * first lay-over from-destination flight
	 */
	private SeatingType firstLayoverFromDestinationFlight;
	
	/**
	 * Indication of the type of seating for the
	 * second lay-over from-destination flight
	 */
	private SeatingType secondLayoverFromDestinationFlight;
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static ReservationModel getInstance() {
		if (instance == null) {
			instance = new ReservationModel();
		}
		return instance;
	}
	
	
	/**
	 * Constructor for the instance
	 */
	private ReservationModel() {
		initialToDestinationFlight = SeatingType.COACH;
		firstLayoverToDestinationFlight = SeatingType.COACH;
		secondLayoverToDestinationFlight = SeatingType.COACH;
		initialFromDestinationFlight = SeatingType.COACH;
		firstLayoverFromDestinationFlight = SeatingType.COACH;
		secondLayoverFromDestinationFlight = SeatingType.COACH;
	}
	
	
	/**
	 * This method sets the seating type for the initial
	 * to-destination flight
	 * 
	 * @param type The type of seating for the flight
	 */
	public void setInitialToDestinationFlight(SeatingType type) {
		initialToDestinationFlight = type;
	}
	
	
	/**
	 * This method acquires the seating type for the initial
	 * to-destination flight
	 * 
	 * @return The seating type for the initial to-destination flight
	 */
	public SeatingType getInitialToDestinationFlight() {
		return initialToDestinationFlight;
	}
	
	
	/**
	 * This method sets the seating type for the first
	 * lay-over to-destination flight
	 * 
	 * @param type The type of seating for the flight
	 */
	public void setFirstLayoverToDestinationFlight(SeatingType type) {
		firstLayoverToDestinationFlight = type;
	}
	
	
	/**
	 * This method acquires the seating type for the first
	 * lay-over to-destination flight
	 * 
	 * @return The seating type for the first lay-over to-destination flight
	 */
	public SeatingType getFirstLayoverToDestinationFlight() {
		return firstLayoverToDestinationFlight;
	}
	
	
	/**
	 * This method sets the seating type for the second
	 * lay-over to-destination flight
	 * 
	 * @param type The type of seating for the flight
	 */
	public void setSecondLayoverToDestinationFlight(SeatingType type) {
		secondLayoverToDestinationFlight = type;
	}
	
	
	/**
	 * This method acquires the seating type for the second
	 * lay-over to-destination flight
	 * 
	 * @return The seating type for the second lay-over to-destination flight
	 */
	public SeatingType getSecondLayoverToDestinationFlight() {
		return secondLayoverToDestinationFlight;
	}
	
	
	/**
	 * This method sets the seating type for the initial
	 * from-destination flight
	 * 
	 * @param type The type of seating for the flight
	 */
	public void setInitialFromDestinationFlight(SeatingType type) {
		initialFromDestinationFlight = type;
	}
	
	
	/**
	 * This method acquires the seating type for the initial
	 * from-destination flight
	 * 
	 * @return The seating type for the initial from-destination flight
	 */
	public SeatingType getInitialFromDestinationFlight() {
		return initialFromDestinationFlight;
	}
	
	
	/**
	 * This method sets the seating type for the first
	 * lay-over from-destination flight
	 * 
	 * @param type The type of seating for the flight
	 */
	public void setFirstLayoverFromDestinationFlight(SeatingType type) {
		firstLayoverFromDestinationFlight = type;
	}
	
	
	/**
	 * This method acquires the seating type for the
	 * first lay-over from-destination flight
	 * 
	 * @return The seating type for the first lay-over from-destination flight
	 */
	public SeatingType getFirstLayoverFromDestinationFlight() {
		return firstLayoverFromDestinationFlight;
	}
	
	
	/**
	 * This method sets the seating type for the second
	 * lay-over from-destination flight
	 * 
	 * @param type The type of seating for the flight
	 */
	public void setSecondLayoverFromDestinationFlight(SeatingType type) {
		secondLayoverFromDestinationFlight = type;
	}
	
	
	/**
	 * This method acquires the seating type for the
	 * second lay-over from-destination flight
	 * 
	 * @return The seating type for the second lay-over from-destination flight
	 */
	public SeatingType getSecondLayoverFromDestinationFlight() {
		return secondLayoverFromDestinationFlight;
	}
}
