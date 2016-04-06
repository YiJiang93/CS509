/**
 * File: Flight.java
 * 
 * The Java source code contained within this file was produced
 * by professor Blake Nelson as a component of a software-based
 * flight reservation system produced for World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04;

/**
 * This class holds values pertaining to a single from one airport to another.
 * Class member attributes are the same as defined by the CS 509 server API
 * and store values after conversion from XML received from the server to
 * Java primitives. Attributes are accessed via getter and setter methods.
 * 
 * @author Blake Nelson
 * @version February 24, 2016
 */
public class Flight {
	
	/**
	 * Member attributes describing a flight
	 */
	private String mAirplane;
	private String mFlightTime;
	private String mNumber;
	private String mCodeDepart;
	private String mTimeDepart;
	private String mCodeArrival;
	private String mTimeArrival;
	private String mPriceFirstclass;
	private int mSeatsFirstclass;
	private String mPriceCoach;
	private int mSeatsCoach;
	
	
	/**
	 * Constructor for the Flight class
	 * 
	 * @param airplane The type of airplane
	 * @param flightTime The duration of the flight
	 * @param number The number of the flight
	 * @param codeDepart The 3-letter code for the departure airport
	 * @param timeDepart The departure time for the flight
	 * @param codeArrival The 3-letter code for the arrival airport
	 * @param timeArrival The arrival time for the flight
	 * @param priceFirstclass The price of first class seating
	 * @param seatsFirstclass The number of first class seats booked
	 * @param priceCoach The price of coach seating
	 * @param seatsCoach The number of coach seats booked
	 */
	public Flight (
			String airplane,
			String flightTime,
			String number,
			String codeDepart,
			String timeDepart,
			String codeArrival,
			String timeArrival,
			String priceFirstclass,
			int seatsFirstclass,
			String priceCoach,
			int seatsCoach) {
		
		mAirplane = airplane;
		mFlightTime = flightTime;
		mNumber = number;
		mCodeDepart = codeDepart;
		mTimeDepart = timeDepart;
		mCodeArrival = codeArrival;
		mTimeArrival = timeArrival;
		mPriceFirstclass = priceFirstclass;
		mSeatsFirstclass = seatsFirstclass;
		mPriceCoach = priceCoach;
		mSeatsCoach = seatsCoach;
	}
	
	
	/**
	 * 
	 */
	public boolean equals(Flight flight) {
		boolean isEqual = false;
		if (mAirplane.equals(flight.getmAirplane())
				&& mFlightTime.equals(flight.getmFlightTime())
				&& mNumber.equals(flight.getmNumber())
				&& mCodeDepart.equals(flight.getmCodeDepart())
				&& mTimeDepart.equals(flight.getmTimeDepart())
				&& mCodeArrival.equals(flight.getmCodeArrival())
				&& mTimeArrival.equals(flight.getmTimeArrival())
				&& mPriceFirstclass.equals(flight.getmPriceFirstclass())
				&& mSeatsFirstclass == flight.getmSeatsFirstclass()
				&& mPriceCoach.equals(flight.getmPriceCoach())
				&& mSeatsCoach == flight.getmSeatsCoach()) {
			isEqual = true;
		}
		return isEqual;
	}
	
	
	/**
	 * This method determines whether this Flight instances is valid
	 * @return true if this instance is valid; false otherwise
	 */
	public boolean isValid() {
		try {
			if ((mAirplane == null) || (mAirplane.length() == 0)) {
				return false;
			}
			if (Integer.parseInt(mFlightTime) <= 0) {
				return false;
			}
			if (Integer.parseInt(mNumber) <= 0) {
				return false;
			}
			if (mCodeDepart.length() != 3) {
				return false;
			}
			if (mCodeArrival.length() != 3) {
				return false;
			}
			if (!mPriceFirstclass.matches("^\\$\\d*\\.\\d\\d$")) {
				return false;
			}
			if (!mPriceCoach.matches("^\\$\\d*\\.\\d\\d$")) {
				return false;
			}
			if (mSeatsFirstclass < 0) {
				return false;
			}
			if (mSeatsCoach < 0) {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method returns the type of plane
	 * @return The type of plane for this flight
	 */
	public String getmAirplane() {
		return mAirplane;
	}

	/**
	 * This method sets the type of plane to use for this flight
	 * @param mAirplane The new type of plane to use for this flight
	 */
	public void setmAirplane(String mAirplane) {
		this.mAirplane = mAirplane;
	}

	/**
	 * This method returns the duration of this flight
	 * @return The duration of this flight
	 */
	public String getmFlightTime() {
		return mFlightTime;
	}

	/**
	 * This method sets the duration of this flight
	 * @param mFlightTime The new duration of this flight
	 */
	public void setmFlightTime(String mFlightTime) {
		this.mFlightTime = mFlightTime;
	}

	/**
	 * This method returns the number of this flight
	 * @return The number of this flight
	 */
	public String getmNumber() {
		return mNumber;
	}

	/**
	 * This method sets the number of this flight
	 * @param mNumber The new number of this flight
	 */
	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}

	/**
	 * This method returns the 3-letter code of the departure airport
	 * @return The 3-letter code of the departure airport
	 */
	public String getmCodeDepart() {
		return mCodeDepart;
	}

	/**
	 * This method sets the 3-letter code of the departure airport
	 * @param mCodeDepart The new 3-letter code of the departure airport
	 */
	public void setmCodeDepart(String mCodeDepart) {
		this.mCodeDepart = mCodeDepart;
	}

	/**
	 * This method returns the departure time for this flight
	 * @return The departure time for this flight
	 */
	public String getmTimeDepart() {
		return mTimeDepart;
	}

	/**
	 * This method sets the departure time for this flight
	 * @param mTimeDepart The new departure time for this flight
	 */
	public void setmTimeDepart(String mTimeDepart) {
		this.mTimeDepart = mTimeDepart;
	}

	/**
	 * This method returns the 3-letter code of the arrival airport
	 * @return The 3-letter code of the arrival airport
	 */
	public String getmCodeArrival() {
		return mCodeArrival;
	}

	/**
	 * This method sets the 3-letter code of the arrival airport
	 * @param mCodeArrival The new 3-letter code of the arrival airport
	 */
	public void setmCodeArrival(String mCodeArrival) {
		this.mCodeArrival = mCodeArrival;
	}

	/**
	 * This method returns the arrival time for this flight
	 * @return The arrival time for this flight
	 */
	public String getmTimeArrival() {
		return mTimeArrival;
	}

	/**
	 * This method sets the arrival time for this flight
	 * @param mTimeArrival The new arrival time for this flight
	 */
	public void setmTimeArrival(String mTimeArrival) {
		this.mTimeArrival = mTimeArrival;
	}

	/**
	 * This method returns the price of first class seating on this flight
	 * @return The price of first class seating on this flight
	 */
	public String getmPriceFirstclass() {
		return mPriceFirstclass;
	}

	/**
	 * This method sets the price of first class seating on this flight
	 * @param mPriceFirstclass The new price of first class seating
	 */
	public void setmPriceFirstclass(String mPriceFirstclass) {
		this.mPriceFirstclass = mPriceFirstclass;
	}

	/**
	 * This method returns the number of first class seats booked on this flight
	 * @return The number of first class seats booked on this flight
	 */
	public int getmSeatsFirstclass() {
		return mSeatsFirstclass;
	}

	/**
	 * This method sets the number of first class seats booked on this flight
	 * @param mSeatsFirstclass The new number of first class seats booked
	 */
	public void setmSeatsFirstclass(int mSeatsFirstclass) {
		this.mSeatsFirstclass = mSeatsFirstclass;
	}

	/**
	 * This method returns the price of coach seating on this flight
	 * @return the mPriceCoach The price of coach seating on this flight
	 */
	public String getmPriceCoach() {
		return mPriceCoach;
	}

	/**
	 * This method sets the price of coach seating on this flight
	 * @param mPriceCoach The new price of coach seating on this flight
	 */
	public void setmPriceCoach(String mPriceCoach) {
		this.mPriceCoach = mPriceCoach;
	}

	/**
	 * This method returns the number of coach seats booked on this flight
	 * @return The number of coach seats booked on this flight
	 */
	public int getmSeatsCoach() {
		return mSeatsCoach;
	}

	/**
	 * This method sets the number of coach seats booked on this flight
	 * @param mSeatsCoach The new number of coach seats booked on this flight
	 */
	public void setmSeatsCoach(int mSeatsCoach) {
		this.mSeatsCoach = mSeatsCoach;
	}	
}
 

