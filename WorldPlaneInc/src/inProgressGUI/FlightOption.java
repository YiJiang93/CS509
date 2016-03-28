/**
 * File: FlightOption.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

/**
 * The FlightOption class represents the graphical display
 * for the information for one available flight appearing in
 * the displayed list of available flights in the SearchView
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 28, 2016
 */
public class FlightOption extends JPanel {

	/**
	 * The serial version identifier for this class
	 */
	private static final long serialVersionUID = -6989416981674011344L;

	/**
	 * Textual information for the first flight in the flight option
	 */
	private JLabel firstFlight;
	
	/**
	 * Textual information for the first lay-over flight in the flight option
	 */
	private JLabel firstLayover;
	
	/**
	 * Textual information for the second lay-over flight in the flight option
	 */
	private JLabel secondLayover;
	
	
	/**
	 * Create the panel containing information for the flight option
	 */
	public FlightOption() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		firstFlight = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, firstFlight, 49, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, firstFlight, 49, SpringLayout.WEST, this);
		add(firstFlight);
		
		firstLayover = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, firstLayover, 6, SpringLayout.SOUTH, firstFlight);
		springLayout.putConstraint(SpringLayout.WEST, firstLayover, 0, SpringLayout.WEST, firstFlight);
		add(firstLayover);
		
		secondLayover = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, secondLayover, 6, SpringLayout.SOUTH, firstLayover);
		springLayout.putConstraint(SpringLayout.WEST, secondLayover, 0, SpringLayout.WEST, firstFlight);
		add(secondLayover);
	}
	
	
	/**
	 * This method configures the information for the first
	 * flight described in this flight option
	 * 
	 * @param information Description of the first flight
	 */
	public void setFirstFlight(String information) {
		firstFlight.setText(information);
	}

	
	/**
	 * This method configures the information for the first
	 * lay-over flight described in this flight option
	 * 
	 * @param information Description of the first lay-over flight
	 */
	public void setFirstLayover(String information) {
		firstLayover.setText(information);
	}
	
	
	/**
	 * This method configures the information for the second
	 * lay-over flight described in this flight option
	 * 
	 * @param information Description of the second lay-over flight
	 */
	public void setSecondLayover(String information) {
		secondLayover.setText(information);
	}
}
