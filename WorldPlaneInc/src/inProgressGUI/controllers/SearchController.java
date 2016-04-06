/**
 * File: SearchController.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI.controllers;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import inProgressGUI.common.FlightType;
import inProgressGUI.common.LayoverType;
import inProgressGUI.common.SeatingType;
import inProgressGUI.common.TravelOption;
import inProgressGUI.common.TravelType;
import inProgressGUI.models.SearchModel;
import inProgressGUI.threads.TravelOptionFinder;
import inProgressGUI.views.ReservationView;
import inProgressGUI.views.SearchView;

/**
 * The SearchController class provides a means for coordinating changes
 * to the SearchView and SearchModel classes when a change is made to any
 * of their attributes. This coordination with the SearchView is done in
 * particular by listening and responding to PropertyChangeEvents that are
 * thrown by the SearchModel. The same coordination with the SearchModel is
 * accomplished by defining the actions to take when user-driven events such
 * as a button click transpire.
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class SearchController implements PropertyChangeListener, ListSelectionListener {
	
	/**
	 * The singleton instance of the SearchController
	 */
	private static SearchController instance = null;
	
	/**
	 * The graphical view associated with this controller
	 */
	private SearchView view;
	
	/**
	 * The model associated with this controller
	 */
	private SearchModel model;
	
	/**
	 * The tag associated with a property change event that updates
	 * the list of to-destination travel options
	 */
	public final static String toDestUpdateTag = "TO DEST UPDATE";
	
	/**
	 * The tag associated with a property change event that updates
	 * the list of from-destination travel options
	 */
	public final static String fromDestUpdateTag = "FROM DEST UPDATE";
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static SearchController getInstance() {
		if (instance == null) {
			instance = new SearchController();
		}
		return instance;
	}
	
	
	/**
	 * Constructor for the instance
	 */
	private SearchController() {
		view = SearchView.getInstance();
		model = SearchModel.getInstance();
		model.addListener(this);
		view.getToDestList().addListSelectionListener(this);
		view.getFromDestList().addListSelectionListener(this);
		setupViewEvents();
	}
	
	
	/**
	 * This method configures the actions to be taken when
	 * a user interacts with the view that is being monitored
	 * by this controller
	 */
	private void setupViewEvents() {
		
		view.getOneWayRadioButton().setAction(new AbstractAction("One Way") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 1961771171656652367L;

			/**
			 * This method responds to the case in which the radio button
			 * used for selecting a one-way flight is clicked by the user
			 * in the associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getOneWayRadioButton().setSelected(true);
				view.getRoundTripRadioButton().setSelected(false);
				model.setTravelType(TravelType.ONE_WAY);
				view.getSecondDepartureDateSelector().setVisible(false);
				view.getSecondDepartureDateSelector().getEditor().setText("");
				model.setToDestinationTravelOptions(new ArrayList<TravelOption>());
				model.setFromDestinationTravelOptions(new ArrayList<TravelOption>());
				SearchView.setupOneWayView();
			}
		});
		
		view.getRoundTripRadioButton().setAction(new AbstractAction("Round Trip") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = -4800308343068602377L;

			/**
			 * This method responds to the case in which the radio button
			 * used for selecting a round-trip flight is clicked by the user
			 * in the associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getOneWayRadioButton().setSelected(false);
				view.getRoundTripRadioButton().setSelected(true);
				model.setTravelType(TravelType.ROUND_TRIP);
				view.getSecondDepartureDateSelector().setVisible(true);
				model.setToDestinationTravelOptions(new ArrayList<TravelOption>());
				model.setFromDestinationTravelOptions(new ArrayList<TravelOption>());
				SearchView.setupRoundTripView();
			}
		});
		
		view.getCoachRadioButton().setAction(new AbstractAction("Coach") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 8081092511187816813L;

			/**
			 * This method responds to the case in which the radio button
			 * used for selecting coach seating is clicked by the user in
			 * the associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCoachRadioButton().setSelected(true);
				view.getFirstClassRadioButton().setSelected(false);
				model.setSeatingType(SeatingType.COACH);
			}
		});
		
		view.getFirstClassRadioButton().setAction(new AbstractAction("First Class") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 1927208906278689853L;

			/**
			 * This method responds to the case in which the radio button
			 * used for selecting first class seating is clicked by the user
			 * in the associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCoachRadioButton().setSelected(false);
				view.getFirstClassRadioButton().setSelected(true);
				model.setSeatingType(SeatingType.FIRST_CLASS);
			}
		});
		
		view.getNoLayoversRadioButton().setAction(new AbstractAction("No Layovers") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = -1163058288073234081L;

			/**
			 * This method responds to the case in which the radio button
			 * used for selecting no lay-overs is clicked by the user
			 * in the associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getNoLayoversRadioButton().setSelected(true);
				view.getOneLayoverRadioButton().setSelected(false);
				view.getTwoLayoversRadioButton().setSelected(false);
				model.setLayoverType(LayoverType.NO_LAYOVERS);
			}
		});
		
		view.getOneLayoverRadioButton().setAction(new AbstractAction("One Layover") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 573267871324180398L;

			/**
			 * This method responds to the case in which the radio button
			 * used for selecting one lay-over is clicked by the user in the
			 * associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getNoLayoversRadioButton().setSelected(false);
				view.getOneLayoverRadioButton().setSelected(true);
				view.getTwoLayoversRadioButton().setSelected(false);
				model.setLayoverType(LayoverType.ONE_LAYOVER);
			}
		});
		
		view.getTwoLayoversRadioButton().setAction(new AbstractAction("Two Layovers") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 6976368859082730879L;

			/**
			 * This method responds to the case in which the radio button
			 * used for selecting two lay-overs is clicked by the user in the
			 * associated view being monitored by this controller
			 * 
			 * @param e The event indicating the click on the radio button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getNoLayoversRadioButton().setSelected(false);
				view.getOneLayoverRadioButton().setSelected(false);
				view.getTwoLayoversRadioButton().setSelected(true);
				model.setLayoverType(LayoverType.TWO_LAYOVERS);
			}
		});
		
		view.getArrivalAirport().addMouseListener(new MouseListener() {

			/**
			 * This method responds to the case in which the user has
			 * clicked on the text field for entering the 3-letter code
			 * for the arrival airport
			 * 
			 * @param e The event indicating the click on the text field
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (view.getArrivalAirport().getText().equals("Enter Airport Code...")) {
					view.getArrivalAirport().setText("");
					view.getArrivalAirport().setForeground(Color.BLACK);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		
		view.getDepartureAirport().addMouseListener(new MouseListener() {

			/**
			 * This method responds to the case in which the user has
			 * clicked on the text field for entering the 3-letter code
			 * for the departure airport
			 * 
			 * @param e The event indicating the click on the text field
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (view.getDepartureAirport().getText().equals("Enter Airport Code...")) {
					view.getDepartureAirport().setText("");
					view.getDepartureAirport().setForeground(Color.BLACK);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		
		view.getSearchButton().setAction(new AbstractAction("Search") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 6909942462679418847L;

			/**
			 * This method responds to the case in which the search button
			 * used for finding travel options that match a specified search
			 * criteria is clicked
			 * 
			 * @param e The event indicating the click on the search button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getOneWayRadioButton().isSelected()) {
					if (view.getDepartureAirport().getText().length() == 3
							&& view.getArrivalAirport().getText().length() == 3) {
						Date first = view.getFirstDepartureDateSelector().getDate();
						if (first != null) {
							if (first.after(new Date()) || first.equals(new Date())) {
								model.setFirstDepartureDate(first);
								model.setDepartureAirport(view.getDepartureAirport().getText());
								model.setArrivalAirport(view.getArrivalAirport().getText());
								
								// Fire off a call to the thread for acquiring flights
								DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
								String date = dateFormat.format(model.getFirstDepartureDate());
								
								TravelOptionFinder finder = new TravelOptionFinder(
										model.getDepartureAirport(), 
										model.getArrivalAirport(),
										date,
										FlightType.TO_DESTINATION);
								
								finder.getFlights();
							}
							else {
								Toolkit.getDefaultToolkit().beep();
								view.getFirstDepartureDateSelector().getEditor().setText("");
							}
						}
						else {
							Toolkit.getDefaultToolkit().beep();
						}
					}
					else {
						Toolkit.getDefaultToolkit().beep();
						if (view.getDepartureAirport().getText().length() != 3) {
							view.getDepartureAirport().setForeground(Color.GRAY);
							view.getDepartureAirport().setText("Enter Airport Code...");
						}
						if (view.getArrivalAirport().getText().length() != 3) {
							view.getArrivalAirport().setForeground(Color.GRAY);
							view.getArrivalAirport().setText("Enter Airport Code...");
						}
					}
				}
				if (view.getRoundTripRadioButton().isSelected()) {
					if (view.getDepartureAirport().getText().length() == 3
							&& view.getArrivalAirport().getText().length() == 3) {
						Date first = view.getFirstDepartureDateSelector().getDate();
						Date second = view.getSecondDepartureDateSelector().getDate();
						if (first != null && second != null) {
							if (first.after(new Date()) && (second.after(first) || second.equals(first))) {
								model.setFirstDepartureDate(first);
								model.setSecondDepartureDate(second);
								model.setDepartureAirport(view.getDepartureAirport().getText());
								model.setArrivalAirport(view.getArrivalAirport().getText());
								
								// Fire off a call to the thread for acquiring flights
								DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
								String date = dateFormat.format(model.getFirstDepartureDate());
								
								TravelOptionFinder finder = new TravelOptionFinder(
										model.getDepartureAirport(), 
										model.getArrivalAirport(),
										date,
										FlightType.TO_DESTINATION);
								
								finder.getFlights();
								
								// Fire off a call to the thread for acquiring flights
								String date2 = dateFormat.format(model.getSecondDepartureDate());
								
								TravelOptionFinder finder2 = new TravelOptionFinder(
										model.getArrivalAirport(), 
										model.getDepartureAirport(),
										date2,
										FlightType.FROM_DESTINATION);
								
								finder2.getFlights();
							}
							else {
								Toolkit.getDefaultToolkit().beep();
								if (first.before(new Date())) {
									view.getFirstDepartureDateSelector().getEditor().setText("");
								}
								if (second.before(first) || second.before(new Date())) {
									view.getSecondDepartureDateSelector().getEditor().setText("");
								}
							}
						}
						else {
							Toolkit.getDefaultToolkit().beep();
							if (first != null) {
								if (!first.after(new Date()) || first.equals(new Date())) {
									view.getFirstDepartureDateSelector().getEditor().setText("");
								}
							}
							if (second != null) {
								if (second.before(new Date())) {
									view.getSecondDepartureDateSelector().getEditor().setText("");
								}
							}
						}
					}
					else {
						Toolkit.getDefaultToolkit().beep();
						if (view.getDepartureAirport().getText().length() != 3) {
							view.getDepartureAirport().setForeground(Color.GRAY);
							view.getDepartureAirport().setText("Enter Airport Code...");
						}
						if (view.getArrivalAirport().getText().length() != 3) {
							view.getArrivalAirport().setForeground(Color.GRAY);
							view.getArrivalAirport().setText("Enter Airport Code...");
						}
					}
				}
			}
		});
		
		view.getReserveButton().setAction(new AbstractAction("Reserve") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = -3768517299909043365L;

			/**
			 * This method responds to the case in which the reserve button
			 * used for reserving travel options is clicked
			 * 
			 * @param e The event indicating the click on the reserve button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getTravelType() == TravelType.ONE_WAY) {
					if (!model.getToDestinationTravelOptions().isEmpty()) {
						ReservationView reservationView = ReservationView.getInstance();
						reservationView.setVisible(true);
						reservationView.addToDestinationModel(model.getSelectedToDestOption().toHtmlString());
					}
					else {
						Toolkit.getDefaultToolkit().beep();
					}
				}
				if (model.getTravelType() == TravelType.ROUND_TRIP) {
					if (!model.getToDestinationTravelOptions().isEmpty()
							&& !model.getFromDestinationTravelOptions().isEmpty()) {
						ReservationView reservationView = ReservationView.getInstance();
						reservationView.setVisible(true);
						reservationView.addToDestinationModel(model.getSelectedToDestOption().toHtmlString());
						reservationView.addFromDestinationModel(model.getSelectedFromDestOption().toHtmlString());
					}
					else {
						Toolkit.getDefaultToolkit().beep();
					}
				}
			}
		});
		
		view.getPriceSortButton().setAction(new AbstractAction("Price Sort") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = 8064290264450305958L;

			/**
			 * This method responds to the case in which the sort
			 * by price button is clicked by the user in the associated view
			 * 
			 * @param e The event indicating the click on the price sort button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getTravelType() == TravelType.ONE_WAY) {
					if (!model.getToDestinationTravelOptions().isEmpty()) {
						// apply the sort to the list model
					}
					else {
						Toolkit.getDefaultToolkit().beep();
					}
				}
				if (model.getTravelType() == TravelType.ROUND_TRIP) {
					if (!model.getToDestinationTravelOptions().isEmpty()
							&& !model.getFromDestinationTravelOptions().isEmpty()) {
						// apply the sort to the list models
					}
					else {
						Toolkit.getDefaultToolkit().beep();
					}
				}
			}
		});
		
		view.getTimeSortButton().setAction(new AbstractAction("Time Sort") {
			
			/**
			 * The serial version unique identification number for this action
			 */
			private static final long serialVersionUID = -2545951728695823226L;

			/**
			 * This method responds to the case in which the sort
			 * by time button is clicked by the user in the associated view
			 * 
			 * @param e The event indicating the click on the time sort button
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getTravelType() == TravelType.ONE_WAY) {
					if (!model.getToDestinationTravelOptions().isEmpty()) {
						// apply the sort to the list model
					}
					else {
						Toolkit.getDefaultToolkit().beep();
					}
				}
				if (model.getTravelType() == TravelType.ROUND_TRIP) {
					if (!model.getToDestinationTravelOptions().isEmpty()
							&& !model.getFromDestinationTravelOptions().isEmpty()) {
						// apply the sort to the list models
					}
					else {
						Toolkit.getDefaultToolkit().beep();
					}
				}
			}
		});
	}
	
	
	/**
	 * This method responds to the case in which the value
	 * selected for a list of travel options has changed
	 * 
	 * @param e An event indicating a change in selection
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int selectedToDestIndex = view.getToDestList().getSelectedIndex();
		int selectedFromDestIndex = view.getFromDestList().getSelectedIndex();
		if (selectedToDestIndex >= 0) {
			int counter = 0;
			for (TravelOption option : model.getToDestinationTravelOptions()) {
				if (counter == selectedToDestIndex) {
					model.setSelectedToDestOption(option);
					break;
				}
				counter++;
			}
		}
		else {
			model.setSelectedToDestOption(null);
		}
		if (selectedFromDestIndex >= 0) {
			int counter = 0;
			for (TravelOption option : model.getFromDestinationTravelOptions()) {
				if (counter == selectedFromDestIndex) {
					model.setSelectedFromDestOption(option);
					break;
				}
				counter++;
			}
		}
		else {
			model.setSelectedFromDestOption(null);
		}
	}

	
	/**
	 * This method serves to update the associated view whenever
	 * a propertyChangeEvent is fired from the associated model
	 * 
	 * @param evt A propertyChangeEvent that had been fired
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase(toDestUpdateTag)) {
			view.updateToDestList((Collection<TravelOption>) evt.getNewValue());
		}
		if (evt.getPropertyName().equalsIgnoreCase(fromDestUpdateTag)) {
			view.updateFromDestList((Collection<TravelOption>) evt.getNewValue());
		}
	}
}
