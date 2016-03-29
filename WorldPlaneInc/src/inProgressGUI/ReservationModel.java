/**
 * File: ReservationModel.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI;

import javax.swing.event.SwingPropertyChangeSupport;

import inProgressGUI.ReservationController;

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
	 * Means for signaling that a property has been changed
	 */
	private SwingPropertyChangeSupport propertyChangeSupport;
	
	
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
		propertyChangeSupport = new SwingPropertyChangeSupport(this);
	}
	
	
	/**
	 * This method registers the ReservationController as
	 * a listener for changes in the ReservationModel
	 */
	public void addListener() {
		propertyChangeSupport.addPropertyChangeListener(ReservationController.getInstance());
	}
}
