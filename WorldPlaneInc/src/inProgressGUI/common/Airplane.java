/**
 * File: Airplane.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI.common;

/**
 * 
 * NEEDS PROPER FORMATTING
 *
 */
public class Airplane {
	
	/**
	 * Airport attributes as defined by the CS509 server interface XML
	 */
	private String mManufacturer;              	// Manufacturer of the Airplane
	private String mModel;              		// Model code of the Airplane
	private int mFirstClassSeats;          		// First Class Seats for the airplane in integer format
	private int mCoachSeats;         			// Coach Seats for the airplane in integer format
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 * 
	 * @precondition None
	 * @postcondition member attributes are initialized to invalid default values
	 */	
	public Airplane() {
		mManufacturer = "";
		mModel = "";
		mFirstClassSeats = 0;
		mCoachSeats = 0;
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 *  
	 * @param manufacturer The manufacturer of the airplane
	 * @param model The model number for the airplane
	 * @param firstclass The maximum number of first class seats on the airplane 
	 * @param coach The maximum number of coach seats on the airplane
	 * 
	 * @preconditions manufacturer and model and not null or empty strings, firstclass and coach are not negitive numbers.
	 * @postconditions member attributes are initialized with input parameter values
	 */
	public Airplane (String manufacturer, String model, int firstclass, int coach) {
		mManufacturer = manufacturer;
		mModel = model;
		mFirstClassSeats = firstclass;
		mCoachSeats = coach;
	}
	
	/**
	 * Initializing constructor with all params as type String. Converts firstclass and coach
	 * values to required integer format and invokes another ctor which initializes object using
	 * expected types.
	 * 
	 * @param manufacturer The manufacturer of the airplane
	 * @param model The model number for the airplane
	 * @param firstclass The maximum number of first class seats on the airplane 
	 * @param coach The maximum number of coach seats on the airplane
	 * 
	 * @preconditions the firstclass and coach are valid String representations of integer values
	 */
	public Airplane (String manufacturer, String model, String firstclass, String coach) {
		this (manufacturer, model, Integer.parseInt(firstclass), Integer.parseInt(coach));
	}
	
	/**
	 * Set the airplane manufacturer
	 * 
	 * @param manufacturer The manufacturer of the airplane
	 */
	public void manufacturer (String manufacturer) {
		mManufacturer = manufacturer;
	}
	
	/**
	 * get the airplane manufacturer
	 * 
	 * @return The airplane manufacturer
	 */
	public String manufacturer () {
		return mManufacturer;
	}
	
	/**
	 * set the airplane model number
	 * 
	 * @param model The model number for the airplane
	 */
	public void model (String model) {
		mModel = model;
	}
	
	/**
	 * Get the airplane model number
	 * 
	 * @return The airplane model number
	 */
	public String model () {
		return mModel;
	}
	
	/**
	 * Set the number of first class seats for the airplane
	 * 
	 * @param firstclass Set the maximum number of first class seats on the airplane 
	 */
	public void firstclass (int firstclass) {
		mFirstClassSeats = firstclass;
	}
	
	/**
	 * Get the number of first class seats for the airplane
	 * 
	 * @return The maximum number of first class seats on the airplane 
	 */
	public int firstclass () {
		return mFirstClassSeats;
	}
	
	/**
	 * Set the number of coach seats for the airplane
	 * 
	 * @param coach Set the maximum number of coach seats on the airplane
	 */
	public void coach (int coach) {
		mCoachSeats = coach;
	}
	
	/**
	 * get the number of coach seats for the airplane
	 * 
	 * @return Get the maximum number of coach seats on the airplane
	 */
	public int coach () {
		return mCoachSeats;
	}

	/**
	 * Determine if two airplane objects are the same airplane
	 * 
	 * Compare another object to this airplane and return true if the other 
	 * object specifies the same airplane as this object
	 * 
	 * @param obj is the object to compare against this object
	 * @return true if the param is the same airplane as this, else false
	 */
	@Override
	public boolean equals (Object obj) {
		// every object is equal to itself
		if (obj == this)
			return true;
		
		// null not equal to anything
		if (obj == null)
			return false;
		
		// can't be equal if obj is not an instance of Airplane
		if (!(obj instanceof Airplane)) 
			return false;
		
		// if all fields are equal, the Airplane are the same
		Airplane rhs = (Airplane) obj;
		if ((rhs.mManufacturer.equals(mManufacturer)) &&
				(rhs.mModel.equals(mModel)) &&
				(rhs.mFirstClassSeats == mFirstClassSeats) &&
				(rhs.mCoachSeats == mCoachSeats)) {
			return true;
		}
		
		return false;	
	}
	
	/**
	 * Determine if object instance has valid attribute data
	 * 
	 * Verifies the manufacturer is not null and not an empty string.
	 * Verifies the model is not null and not an empty string
	 * Verifies firstclass is greater than or equal to 0.
	 * Verifies coach is greater than or equal to 0.
	 * 
	 * @return true if object passes above validation checks
	 * 
	 */
	public boolean isValid() {
		
		// If the manufacturer isn't valid, the object isn't valid
		if ((mManufacturer == null) || (mManufacturer == ""))
			return false;
		
		// If the model isn't valid, object isn't valid
		if ((mModel == null) || (mModel == ""))
			return false;
		
		// Verify firstclass and coach are greater than or equal to 0.
		if ((mFirstClassSeats < 0) || (mCoachSeats < 0)) {
			return false;
		}
		
		return true;
	}
}
