package edu.wpi.cs509.team04;

import javax.swing.event.SwingPropertyChangeSupport;

public class ReservationModel {
	private String flightNumber;
	private String seatingType;
	private SwingPropertyChangeSupport propertyChangeSupport;
	
	public ReservationModel() {
		flightNumber = "";
		seatingType = "";
		propertyChangeSupport = new SwingPropertyChangeSupport(this);
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getSeatingType() {
		return seatingType;
	}

	public void setSeatingType(String seatingType) {
		this.seatingType = seatingType;
	}

	public void addListener(ReservationController reservationController) {
		propertyChangeSupport.addPropertyChangeListener(reservationController);
	}
	
}
