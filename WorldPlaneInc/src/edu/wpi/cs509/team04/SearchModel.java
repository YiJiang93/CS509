package edu.wpi.cs509.team04;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.event.SwingPropertyChangeSupport;

public class SearchModel {

	private SwingPropertyChangeSupport propertyChangeSupport;
	private String departureAirport;
	private String arrivalAirport;
	private Date departureDate;
	private Flight selectedFlight;
	private String seatingType;
	private Collection<Flight> availableFlights;
	
	/**
	 * Constructor for the GuiModel class
	 */
	public SearchModel() {
		departureAirport = "";
		arrivalAirport = "";
		selectedFlight = new Flight("", "", "", "", "", "", "", "", 0, "", 0);
		seatingType = "";
		departureDate = new Date();
		availableFlights = new ArrayList<Flight>();
		propertyChangeSupport = new SwingPropertyChangeSupport(this);
	}
	
	/**
	 * Provides a means for adding allowing a
	 * class implementing the PropertyChangeListener
	 * to monitor this model for any change
	 * 
	 * @param property 
	 */
	public void addListener(PropertyChangeListener property) {
		propertyChangeSupport.addPropertyChangeListener(property);
	}
	
	// Methods for changing properties of this model
	
	public void setDepartureAirport(String airport) {
		departureAirport = airport;
	}
	
	public void setArrivalAirport(String airport) {
		arrivalAirport = airport;
	}
	
	public void setDepartureDate(Date date) {
		departureDate = date;
	}
	
	public void setSelectedFlight(Flight flight) {
		selectedFlight = flight;
	}
	
	public void setSeatingType(String seating) {
		seatingType = seating;
	}
	
	public void setAvailableFlights(Collection<Flight> flights) {
		Collection<Flight> oldValue = availableFlights;
		availableFlights.clear();
		int counter = 0;
		for(Flight flight : flights) {
			if (counter < 34) {
				availableFlights.add(flight);
			}
			else {
				break;
			}
		}
		propertyChangeSupport.firePropertyChange(SearchController.AVAILABLE_FLIGHTS, null, availableFlights);
	}
	
	// Methods for acquiring the current information from the model
	
	public String getDepartureAirport() {
		return departureAirport;
	}
	
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}
	
	public Flight getSelectedFlight() {
		return selectedFlight;
	}
	
	public String getSeatingType() {
		return seatingType;
	}
	
	public Collection<Flight> getAvailableFlights() {
		return availableFlights;
	}
}
