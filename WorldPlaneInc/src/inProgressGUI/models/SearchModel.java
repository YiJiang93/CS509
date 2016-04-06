/**
 * File: SearchModel.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI.models;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.event.SwingPropertyChangeSupport;

import inProgressGUI.common.LayoverType;
import inProgressGUI.common.SeatingType;
import inProgressGUI.common.TravelOption;
import inProgressGUI.common.TravelType;
import inProgressGUI.controllers.SearchController;

/**
 * The SearchModel class provides a means for maintaining
 * information on the state of the SearchView
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class SearchModel {

	/**
	 * The singleton instance of the SearchModel
	 */
	private static SearchModel instance = null;
	
	/**
	 * The means for signaling that a property of this model has been changed
	 */
	private SwingPropertyChangeSupport propertyChangeSupport;
	
	/**
	 * The currently provided departure airport
	 */
	private String departureAirport;
	
	/**
	 * The currently provided arrival airport
	 */
	private String arrivalAirport;
	
	/**
	 * The currently selected type of travel
	 */
	private TravelType travelType;
	
	/**
	 * The currently selected seating type
	 */
	private SeatingType seatingType;
	
	/**
	 * The currently selected lay-over type
	 */
	private LayoverType layoverType;
	
	/**
	 * The currently selected first departure date
	 */
	private Date firstDepartureDate;
	
	/**
	 * The currently selected second departure date
	 */
	private Date secondDepartureDate;
	
	/**
	 * The current list of travel options for to-destination flights
	 */
	private Collection<TravelOption> toDestTravelOptions;
	
	/**
	 * The current list of travel options for from-destination flights
	 */
	private Collection<TravelOption> fromDestTravelOptions;
	
	/**
	 * The currently selected to-destination travel option
	 */
	private TravelOption selectedToDestOption;
	
	/**
	 * The currently selected from-destination travel option
	 */
	private TravelOption selectedFromDestOption;
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static SearchModel getInstance() {
		if (instance == null) {
			instance = new SearchModel();
		}
		return instance;
	}
	
	
	/**
	 * Constructor for the instance
	 */
	private SearchModel() {
		propertyChangeSupport = new SwingPropertyChangeSupport(this);
		departureAirport = "";
		arrivalAirport = "";
		travelType = TravelType.ONE_WAY;
		seatingType = SeatingType.COACH;
		layoverType = LayoverType.NO_LAYOVERS;
		firstDepartureDate = new Date();
		secondDepartureDate = new Date();
		toDestTravelOptions = new ArrayList<TravelOption>();
		fromDestTravelOptions = new ArrayList<TravelOption>();
	}
	
	
	/**
	 * This method registers an object to listen
	 * for changes in the SearchModel when they are fired
	 * 
	 * @param listener The property change listener to add
	 */
	public void addListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	
	/**
	 * This method acquires the current departure airport
	 * @return The current departure airport
	 */
	public String getDepartureAirport() {
		return departureAirport;
	}
	
	
	/**
	 * This method sets the current departure airport
	 * @param airport The new departure airport
	 */
	public void setDepartureAirport(String airport) {
		departureAirport = airport;
	}
	
	
	/**
	 * This method acquires the current arrival airport
	 * @return The current arrival airport
	 */
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	
	
	/**
	 * This method sets the current arrival airport
	 * @param airport The new arrival airport
	 */
	public void setArrivalAirport(String airport) {
		arrivalAirport = airport;
	}
	
	
	/**
	 * This method acquires the current seating type
	 * @return The current seating type
	 */
	public SeatingType getSeatingType() {
		return seatingType;
	}
	
	
	/**
	 * This method sets the current seating type
	 * @param type The new seating type
	 */
	public void setSeatingType(SeatingType type) {
		seatingType = type;
	}
	
	
	/**
	 * This method acquires the current travel type
	 * @return The current travel type
	 */
	public TravelType getTravelType() {
		return travelType;
	}
	
	
	/**
	 * This method sets the current travel type
	 * @param type The new travel type
	 */
	public void setTravelType(TravelType type) {
		travelType = type; 
	}
	
	
	/**
	 * This method acquires the current lay-over type
	 * @return The current lay-over type
	 */
	public LayoverType getLayoverType() {
		return layoverType;
	}
	
	
	/**
	 * This method sets the current lay-over type
	 * @param type The new lay-over type
	 */
	public void setLayoverType(LayoverType type) {
		layoverType = type;
	}
	
	
	/**
	 * This method acquires the current first departure date
	 * for a round-trip flight / the only departure date
	 * for a one-way flight
	 */
	public Date getFirstDepartureDate() {
		return firstDepartureDate;
	}
	
	
	/**
	 * This method sets the current first departure date
	 * @param date The new first departure date
	 */
	public void setFirstDepartureDate(Date date) {
		firstDepartureDate = date;
	}
	
	
	/**
	 * This method acquires the current second departure
	 * date for a round-trip flight
	 */
	public Date getSecondDepartureDate() {
		return secondDepartureDate;
	}
	
	
	/**
	 * This method sets the current second departure date
	 * @param date The new second departure date
	 */
	public void setSecondDepartureDate(Date date) {
		secondDepartureDate = date;
	}
	
	
	/**
	 * This method acquires the current to-destination travel options
	 * @return The current to-destination travel options
	 */
	public Collection<TravelOption> getToDestinationTravelOptions() {
		return toDestTravelOptions;
	}
	
	
	/**
	 * This method sets the current to-destination travel options
	 * @param options The new to-destination travel options
	 */
	public void setToDestinationTravelOptions(Collection<TravelOption> options) {
		toDestTravelOptions.clear();
		for (TravelOption option : options) {
			toDestTravelOptions.add(option);
		}
		propertyChangeSupport.firePropertyChange(
				SearchController.toDestUpdateTag, null, toDestTravelOptions);
	}
	
	
	/**
	 * This method acquires the current from-destination travel options
	 * @return The current from-destination travel options
	 */
	public Collection<TravelOption> getFromDestinationTravelOptions() {
		return fromDestTravelOptions;
	}
	
	
	/**
	 * This method sets the current from-destination travel options
	 * @param options The new from-destination travel options
	 */
	public void setFromDestinationTravelOptions(Collection<TravelOption> options) {
		fromDestTravelOptions.clear();
		for (TravelOption option : options) {
			fromDestTravelOptions.add(option);
		}
		propertyChangeSupport.firePropertyChange(
				SearchController.fromDestUpdateTag, null, fromDestTravelOptions);
	}
	
	
	/**
	 * This method acquires the currently selected to-destination travel option
	 * @return The currently selected to-destination travel option
	 */
	public TravelOption getSelectedToDestOption() {
		return selectedToDestOption;
	}
	
	
	/**
	 * This method sets the value of the currently selected to-destination travel option
	 * @param selected The new currently selected to-destination travel option
	 */
	public void setSelectedToDestOption(TravelOption selected) {
		selectedToDestOption = selected;
	}
	
	
	/**
	 * This method acquires the currently selected from-destination travel option
	 * @return The currently selected from-destination travel option
	 */
	public TravelOption getSelectedFromDestOption() {
		return selectedFromDestOption;
	}
	
	
	/**
	 * This method sets the value of the currently selected from-destination travel option
	 * @param selected The new currently selected from-destination travel option
	 */
	public void setSelectedFromDestOption(TravelOption selected) {
		selectedFromDestOption = selected;
	}
}
