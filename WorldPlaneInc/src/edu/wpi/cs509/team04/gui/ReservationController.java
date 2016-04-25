/**
 * File: ReservationController.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04.gui;

/**
 * The ReservationController class provides a means for coordinating changes
 * for responding to events that occur in the ReservationView class.
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class ReservationController {

	/**
	 * The singleton instance of the ReservationController
	 */
	private static ReservationController instance = null;

	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static ReservationController getInstance() {
		if (instance == null) {
			instance = new ReservationController();
		}
		return instance;
	}
	
	
	/**
	 * Constructor for the instance
	 */
	private ReservationController() {
		setupViewEvents();
	}
	
	
	/**
	 * This method configures all of the actions to be
	 * taken whenever a user-driven event such as a button
	 * click occurs in the ReservationView
	 */
	private void setupViewEvents() {
		
	}
}
