/**
 * File: ReservationController.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import inProgressGUI.ReservationModel;

/**
 * The ReservationController class provides a means for coordinating changes
 * to the ReservationView and ReservationModel classes when a change is made
 * to any of their attributes. This coordination with the ReservationView is
 * done in particular by listening and responding to PropertyChangeEvents that
 * are thrown by the ReservationModel. The same coordination with the ReservationModel
 * is accomplished by defining the actions to take when user-driven events such
 * as a button click transpire.
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class ReservationController implements PropertyChangeListener {

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
		ReservationModel.getInstance().addListener();
		setupViewEvents();
	}
	
	
	/**
	 * This method configures all of the actions to be
	 * taken whenever a user-driven event such as a button
	 * click occurs in the ReservationView
	 */
	private void setupViewEvents() {
		
	}
	
	
	/**
	 * This method provides a means for responding to the
	 * event in which a change has occurred to one of the
	 * attributes in the ReservationModel
	 * 
	 * @param evt Event in which a ReservationModel property changed
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
	}
}
