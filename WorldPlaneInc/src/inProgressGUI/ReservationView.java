/**
 * File: ReservationView.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI;

import javax.swing.JFrame;

/**
 * The ReservationView class provides a graphic user interface
 * with which the user can interact to either confirm or cancel
 * a particular flight reservation
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class ReservationView {

	/**
	 * The singleton instance of the ReservationView
	 */
	private static ReservationView instance = null;
	
	/**
	 * The background frame for the ReservationView
	 */
	private static JFrame frame;
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static ReservationView getInstance() {
		if (instance == null) {
			instance = new ReservationView();
			initialize();
		}
		return instance;
	}
	

	/**
	 * Initialize the contents of the frame
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
	}
	
	
	/**
	 * Private constructor for ReservationView
	 */
	private ReservationView() {
		
	}
	
	
	/**
	 * This method sets the visibility of the entire
	 * window used for displaying the view
	 * 
	 * @param b Indication of whether to make the window visible
	 */
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
