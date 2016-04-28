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
import java.util.Dictionary;

import javax.swing.AbstractAction;

import edu.wpi.cs509.team04.common.Flight;
import edu.wpi.cs509.team04.common.TravelOption;
import edu.wpi.cs509.team04.enums.TravelType;
import edu.wpi.cs509.team04.server.ServerInterface;
import edu.wpi.cs509.team04.threads.Helper;

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
				view.displayError(false);
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
				view.displayError(false);
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
				view.displayError(false);
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
				String seating = "Coach";
				String helperSeating = "Coach";
				ArrayList<String> flightNumbers = new ArrayList<String>();
				
				TravelOption firstLeg = sModel.getSelectedToDestOption();
				boolean overallAcceptableFirstLeg = false;
				boolean overallAcceptableSecondLeg = false;
				
				boolean acceptableFirstLeg1 = false;
				boolean acceptableFirstLeg2 = false;
				boolean acceptableFirstLeg3 = false;
				boolean acceptableSecondLeg1 = false;
				boolean acceptableSecondLeg2 = false;
				boolean acceptableSecondLeg3 = false;
				
				Flight nullFlight = new Flight("", "0", "", "", "", "", "", "0.0", 0, "0.0", 0);
				
				if (view.getFirstClassRadioButton().isSelected()) {
					seating = "FirstClass";
					helperSeating = "First Class";
				}
				
				if (firstLeg != null) {
					int firstLegFlights = 0;
					Dictionary<String, Integer> availableSeats1;
					Dictionary<String, Integer> availableSeats2;
					Dictionary<String, Integer> availableSeats3;
					if (firstLeg.getInitialFlight() != nullFlight) {
						flightNumbers.add(firstLeg.getInitialFlight().getmNumber());
						firstLegFlights++;
					}
					if (firstLeg.getFirstLayover() != nullFlight) {
						flightNumbers.add(firstLeg.getFirstLayover().getmNumber());
						firstLegFlights++;
					}
					if (firstLeg.getSecondLayover() != nullFlight) {
						flightNumbers.add(firstLeg.getSecondLayover().getmNumber());
						firstLegFlights++;
					}
					switch(firstLegFlights) {
						case 1:
							availableSeats1 = Helper.getAvailableSeats(firstLeg.getInitialFlight());
							acceptableFirstLeg1 = Helper.areSeatsAvailable(availableSeats1, helperSeating, firstLeg.getInitialFlight());
							overallAcceptableFirstLeg = acceptableFirstLeg1;
							break;
						case 2:
							availableSeats1 = Helper.getAvailableSeats(firstLeg.getInitialFlight());
							acceptableFirstLeg1 = Helper.areSeatsAvailable(availableSeats1, helperSeating, firstLeg.getInitialFlight());
							availableSeats2 = Helper.getAvailableSeats(firstLeg.getFirstLayover());
							acceptableFirstLeg2 = Helper.areSeatsAvailable(availableSeats2, helperSeating, firstLeg.getFirstLayover());
							overallAcceptableFirstLeg = acceptableFirstLeg1 && acceptableFirstLeg2;
							break;
						case 3:
							availableSeats1 = Helper.getAvailableSeats(firstLeg.getInitialFlight());
							acceptableFirstLeg1 = Helper.areSeatsAvailable(availableSeats1, helperSeating, firstLeg.getInitialFlight());
							availableSeats2 = Helper.getAvailableSeats(firstLeg.getFirstLayover());
							acceptableFirstLeg2 = Helper.areSeatsAvailable(availableSeats2, helperSeating, firstLeg.getFirstLayover());
							availableSeats3 = Helper.getAvailableSeats(firstLeg.getSecondLayover());
							acceptableFirstLeg3 = Helper.areSeatsAvailable(availableSeats3, helperSeating, firstLeg.getSecondLayover());
							overallAcceptableFirstLeg = acceptableFirstLeg1 && acceptableFirstLeg2 && acceptableFirstLeg3;
							break;
						default:
							break;
					}
				}
				
				if (sModel.getTravelType() == TravelType.ROUND_TRIP) {
					TravelOption secondLeg = sModel.getSelectedFromDestOption();
					
					if (secondLeg != null) {
						int secondLegFlights = 0;
						Dictionary<String, Integer> availableSeats1;
						Dictionary<String, Integer> availableSeats2;
						Dictionary<String, Integer> availableSeats3;
						if (secondLeg.getInitialFlight() != nullFlight) {
							flightNumbers.add(secondLeg.getInitialFlight().getmNumber());
							secondLegFlights++;
						}
						if (secondLeg.getFirstLayover() != nullFlight) {
							flightNumbers.add(secondLeg.getFirstLayover().getmNumber());
							secondLegFlights++;
						}
						if (secondLeg.getSecondLayover() != nullFlight) {
							flightNumbers.add(secondLeg.getSecondLayover().getmNumber());
							secondLegFlights++;
						}
						switch(secondLegFlights) {
							case 1:
								availableSeats1 = Helper.getAvailableSeats(secondLeg.getInitialFlight());
								acceptableSecondLeg1 = Helper.areSeatsAvailable(availableSeats1, helperSeating, secondLeg.getInitialFlight());
								overallAcceptableSecondLeg = acceptableSecondLeg1;
								break;
							case 2:
								availableSeats1 = Helper.getAvailableSeats(secondLeg.getInitialFlight());
								acceptableSecondLeg1 = Helper.areSeatsAvailable(availableSeats1, helperSeating, secondLeg.getInitialFlight());
								availableSeats2 = Helper.getAvailableSeats(secondLeg.getFirstLayover());
								acceptableSecondLeg2 = Helper.areSeatsAvailable(availableSeats2, helperSeating, secondLeg.getFirstLayover());
								overallAcceptableSecondLeg = acceptableSecondLeg1 && acceptableSecondLeg2;
								break;
							case 3:
								availableSeats1 = Helper.getAvailableSeats(secondLeg.getInitialFlight());
								acceptableSecondLeg1 = Helper.areSeatsAvailable(availableSeats1, helperSeating, secondLeg.getInitialFlight());
								availableSeats2 = Helper.getAvailableSeats(secondLeg.getFirstLayover());
								acceptableSecondLeg2 = Helper.areSeatsAvailable(availableSeats2, helperSeating, secondLeg.getFirstLayover());
								availableSeats3 = Helper.getAvailableSeats(secondLeg.getSecondLayover());
								acceptableSecondLeg3 = Helper.areSeatsAvailable(availableSeats3, helperSeating, secondLeg.getSecondLayover());
								overallAcceptableSecondLeg = acceptableSecondLeg1 && acceptableSecondLeg2 && acceptableSecondLeg3;
								break;
							default:
								break;
						}
					}
				}
				
				if (sModel.getTravelType() == TravelType.ONE_WAY) {
					if (overallAcceptableFirstLeg) {
						serverInterface.lock();
						for (String number : flightNumbers) {
							serverInterface.buyTickets(number, seating);
						}
						serverInterface.unlock();
						view.closeView();
					}
					else {
						view.displayError(true);
					}
				}
				
				if (sModel.getTravelType() == TravelType.ROUND_TRIP) {
					if (overallAcceptableFirstLeg && overallAcceptableSecondLeg) {
						serverInterface.lock();
						for (String number : flightNumbers) {
							serverInterface.buyTickets(number, seating);
						}
						serverInterface.unlock();
						view.closeView();
					}
					else {
						view.displayError(true);
					}
				}
			}
		});
	}
}
