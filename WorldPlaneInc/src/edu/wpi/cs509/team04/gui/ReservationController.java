/**
 * File: ReservationController.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.AbstractAction;

import edu.wpi.cs509.team04.common.TravelOption;
import edu.wpi.cs509.team04.enums.TravelType;
import edu.wpi.cs509.team04.resources.ConfigSingleton;
import edu.wpi.cs509.team04.server.ServerInterface;

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
	 * The graphical view associated with this controller
	 */
	private ReservationView view;
	
	/**
	 * The model for the SearchView
	 */
	private SearchModel sModel;
	
	
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
		view = ReservationView.getInstance();
		sModel = SearchModel.getInstance();
		setupViewEvents();
	}
	
	
	/**
	 * This method configures all of the actions to be
	 * taken whenever a user-driven event such as a button
	 * click occurs in the ReservationView
	 */
	private void setupViewEvents() {
		
		view.getCoachRadioButton().setAction(new AbstractAction("Coach") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 5324769878236616054L;

			/**
			 * This method responds to the case in which the radio button
			 * used for selecting coach seating is clicked by the user
			 * in the associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCoachRadioButton().setSelected(true);
				view.getFirstClassRadioButton().setSelected(false);
			}
		});
		
		view.getFirstClassRadioButton().setAction(new AbstractAction("First Class") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 6684274742519294500L;
			
			/**
			 * This method responds to the case in which the radio button
			 * used for selecting first class seating is clicked by the user
			 * in the associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getFirstClassRadioButton().setSelected(true);
				view.getCoachRadioButton().setSelected(false);
			}
		});
		
		view.getCancelButton().setAction(new AbstractAction("Cancel") {

			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = -614192086558804230L;
			
			/**
			 * This method responds to the case in which the button
			 * used for canceling the reservation is clicked by the
			 * user in the associated view being monitored by this
			 * controller
			 * 
			 * @param e The event indicating the click on the button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.closeView();
			}
		});
		
		view.getConfirmButton().setAction(new AbstractAction("Confirm") {

			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = -2051392425121838148L;
			
			/**
			 * This method responds to the case in which the button
			 * used for confirming the reservation is clicked by the
			 * user in the associated view being monitored by this
			 * controller
			 * 
			 * @param e The event indicating the click on the button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerInterface serverInterface = ServerInterface.getInstance();
				ConfigSingleton configSingleton = ConfigSingleton.getInstance();
				String team = configSingleton.get("team");
				String seating = "Coach";
				Collection<String> flightNumbers = new ArrayList<String>();
				
				TravelOption firstLeg = sModel.getSelectedToDestOption();
				
				if (firstLeg.getInitialFlight() != null) {
					flightNumbers.add(firstLeg.getInitialFlight().getmNumber());
				}
				if (firstLeg.getFirstLayover() != null) {
					flightNumbers.add(firstLeg.getFirstLayover().getmNumber());
				}
				if (firstLeg.getSecondLayover() != null) {
					flightNumbers.add(firstLeg.getSecondLayover().getmNumber());
				}
				
				if (sModel.getTravelType() == TravelType.ROUND_TRIP) {
					TravelOption secondLeg = sModel.getSelectedFromDestOption();
					
					if (secondLeg.getInitialFlight() != null) {
						flightNumbers.add(secondLeg.getInitialFlight().getmNumber());
					}
					if (secondLeg.getFirstLayover() != null) {
						flightNumbers.add(secondLeg.getFirstLayover().getmNumber());
					}
					if (secondLeg.getSecondLayover() != null) {
						flightNumbers.add(secondLeg.getSecondLayover().getmNumber());
					}
				}
				
				if (view.getFirstClassRadioButton().isSelected()) {
					seating = "First Class";
				}
				
				for (String flightNumber : flightNumbers) {
					serverInterface.buyTickets(team, flightNumber, seating);
				}
			}
		});
	}
}
