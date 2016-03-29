/**
 * File: SearchView.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

/**
 * The SearchView class provides a graphic user interface with
 * which the user can interact to search for flights matching
 * a given criteria
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class SearchView {

	/**
	 * The singleton instance of the SearchView
	 */
	private static SearchView instance = null;
	
	/**
	 * The background frame for the SearchView
	 */
	private static JFrame frame;
	
	/**
	 * The text field for entering the departure airport
	 */
	private static JTextField dAirportField;
	
	/**
	 * The text field for entering the arrival airport
	 */
	private static JTextField aAirportField;
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static SearchView getInstance() {
		if (instance == null) {
			instance = new SearchView();
			initialize();
		}
		return instance;
	}
	
	
	/**
	 * Private constructor for SearchView
	 */
	private SearchView() {
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 2500, 1000);
		frame.getContentPane().setFont(new Font("Lucida Grande", Font.ITALIC, 22));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("World Plane Inc. - Travel System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		// Red header ribbon for search
		JPanel rRibbon = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, rRibbon, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, rRibbon, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, rRibbon, 63, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, rRibbon, 1440, SpringLayout.WEST, frame.getContentPane());
		rRibbon.setBackground(Color.RED);
		frame.getContentPane().add(rRibbon);
		SpringLayout sl_rRibbon = new SpringLayout();
		rRibbon.setLayout(sl_rRibbon);
		
		// White text label for red header ribbon for search
		JLabel flightSearchLabel = new JLabel("Flight Search");
		flightSearchLabel.setForeground(Color.WHITE);
		flightSearchLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 40));
		sl_rRibbon.putConstraint(SpringLayout.NORTH, flightSearchLabel, 10, SpringLayout.NORTH, rRibbon);
		sl_rRibbon.putConstraint(SpringLayout.WEST, flightSearchLabel, 10, SpringLayout.WEST, rRibbon);
		springLayout.putConstraint(SpringLayout.WEST, flightSearchLabel, 0, SpringLayout.WEST, rRibbon);
		springLayout.putConstraint(SpringLayout.SOUTH, flightSearchLabel, 0, SpringLayout.SOUTH, rRibbon);
		rRibbon.add(flightSearchLabel);
		
		// White ribbon underneath red header ribbon for search
		JPanel wRibbon = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, wRibbon, 0, SpringLayout.SOUTH, rRibbon);
		springLayout.putConstraint(SpringLayout.WEST, wRibbon, 0, SpringLayout.WEST, rRibbon);
		springLayout.putConstraint(SpringLayout.EAST, wRibbon, screenWidth, SpringLayout.EAST, frame.getContentPane());
		wRibbon.setBackground(Color.WHITE);
		frame.getContentPane().add(wRibbon);
		
		// Departure airport label for search
		JLabel dAirportLabel = new JLabel("Departure Airport");
		springLayout.putConstraint(SpringLayout.NORTH, dAirportLabel, 6, SpringLayout.SOUTH, wRibbon);
		springLayout.putConstraint(SpringLayout.WEST, dAirportLabel, 20, SpringLayout.WEST, frame.getContentPane());
		dAirportLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(dAirportLabel);
		
		// Text field for entering departure airport for search
		dAirportField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, dAirportField, 6, SpringLayout.SOUTH, dAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, dAirportField, 0, SpringLayout.WEST, dAirportLabel);
		dAirportField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(dAirportField);
		dAirportField.setColumns(10);
		
		// Arrival airport label for search
		JLabel aAirportLabel = new JLabel("Arrival Airport");
		springLayout.putConstraint(SpringLayout.NORTH, aAirportLabel, 6, SpringLayout.SOUTH, wRibbon);
		springLayout.putConstraint(SpringLayout.WEST, aAirportLabel, 28, SpringLayout.EAST, dAirportLabel);
		aAirportLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(aAirportLabel);
		
		// Text field for entering arrival airport for search
		aAirportField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, aAirportField, 6, SpringLayout.SOUTH, aAirportLabel);
		springLayout.putConstraint(SpringLayout.EAST, dAirportField, -28, SpringLayout.WEST, aAirportField);
		springLayout.putConstraint(SpringLayout.WEST, aAirportField, 0, SpringLayout.WEST, aAirportLabel);
		springLayout.putConstraint(SpringLayout.EAST, aAirportField, 0, SpringLayout.EAST, aAirportLabel);
		aAirportField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(aAirportField);
		aAirportField.setColumns(10);
		
		// Radio button for selecting one-way flight type
		JRadioButton oneWayRadioButton = new JRadioButton("One way");
		springLayout.putConstraint(SpringLayout.WEST, oneWayRadioButton, 28, SpringLayout.EAST, aAirportField);
		oneWayRadioButton.setSelected(true);
		oneWayRadioButton.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		frame.getContentPane().add(oneWayRadioButton);
		
		// Radio button for selecting round-trip flight type
		JRadioButton roundTripRadioButton = new JRadioButton("Round-trip");
		springLayout.putConstraint(SpringLayout.SOUTH, dAirportField, -9, SpringLayout.SOUTH, roundTripRadioButton);
		springLayout.putConstraint(SpringLayout.NORTH, roundTripRadioButton, 6, SpringLayout.SOUTH, oneWayRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, roundTripRadioButton, 0, SpringLayout.WEST, oneWayRadioButton);
		roundTripRadioButton.setSelected(false);
		roundTripRadioButton.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		frame.getContentPane().add(roundTripRadioButton);
		
		// Flight type label for search
		JLabel flightTypeLabel = new JLabel("Flight Type");
		springLayout.putConstraint(SpringLayout.NORTH, oneWayRadioButton, 6, SpringLayout.SOUTH, flightTypeLabel);
		springLayout.putConstraint(SpringLayout.NORTH, flightTypeLabel, 0, SpringLayout.NORTH, dAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, flightTypeLabel, 28, SpringLayout.EAST, aAirportLabel);
		flightTypeLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(flightTypeLabel);
		
		// Departure date 1 label for search
		JLabel dDateLabel = new JLabel("Departure Date");
		springLayout.putConstraint(SpringLayout.NORTH, dDateLabel, 0, SpringLayout.NORTH, dAirportLabel);
		dDateLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(dDateLabel);
		
		// Date selector for departure date 1
		JXDatePicker dDatePicker = new JXDatePicker();
		springLayout.putConstraint(SpringLayout.NORTH, dDatePicker, 6, SpringLayout.SOUTH, dDateLabel);
		springLayout.putConstraint(SpringLayout.WEST, dDatePicker, 0, SpringLayout.WEST, dDateLabel);
		dDatePicker.getEditor().setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(dDatePicker);
		
		// Departure date 2 label for search
		JLabel dDateLabel2 = new JLabel("Departure Date 2");
		springLayout.putConstraint(SpringLayout.NORTH, dDateLabel2, 0, SpringLayout.NORTH, dAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, dDateLabel2, 955, SpringLayout.WEST, frame.getContentPane());
		dDateLabel2.setVisible(false);
		dDateLabel2.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(dDateLabel2);
		
		// Date selector for departure date 2
		JXDatePicker dDatePicker2 = new JXDatePicker();
		springLayout.putConstraint(SpringLayout.NORTH, dDatePicker2, 6, SpringLayout.SOUTH, dDateLabel2);
		springLayout.putConstraint(SpringLayout.WEST, dDatePicker2, 955, SpringLayout.WEST, frame.getContentPane());
		dDatePicker2.setVisible(false);
		dDatePicker2.getEditor().setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(dDatePicker2);
		
		// Lay-overs label for search
		JLabel layoverLabel = new JLabel("Layovers");
		springLayout.putConstraint(SpringLayout.NORTH, layoverLabel, 0, SpringLayout.NORTH, dAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, layoverLabel, 39, SpringLayout.EAST, flightTypeLabel);
		layoverLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(layoverLabel);
		
		// No lay-overs radio button for search
		JRadioButton noLayoverRadioButton = new JRadioButton("No layovers");
		noLayoverRadioButton.setSelected(true);
		springLayout.putConstraint(SpringLayout.NORTH, noLayoverRadioButton, 0, SpringLayout.NORTH, oneWayRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, noLayoverRadioButton, 0, SpringLayout.WEST, layoverLabel);
		noLayoverRadioButton.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		frame.getContentPane().add(noLayoverRadioButton);
		
		// One lay-over radio button for search
		JRadioButton oneLayoverRadioButton = new JRadioButton("One layover");
		springLayout.putConstraint(SpringLayout.NORTH, oneLayoverRadioButton, 0, SpringLayout.NORTH, roundTripRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, oneLayoverRadioButton, 0, SpringLayout.WEST, layoverLabel);
		oneLayoverRadioButton.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		frame.getContentPane().add(oneLayoverRadioButton);
		
		// Two lay-over radio button for search
		JRadioButton twoLayoverRadioButton = new JRadioButton("Two layovers");
		springLayout.putConstraint(SpringLayout.NORTH, twoLayoverRadioButton, 6, SpringLayout.SOUTH, oneLayoverRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, twoLayoverRadioButton, 0, SpringLayout.WEST, layoverLabel);
		twoLayoverRadioButton.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		frame.getContentPane().add(twoLayoverRadioButton);
		
		// Search button panel
		JPanel searchPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, dDatePicker2, -27, SpringLayout.NORTH, searchPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, dDatePicker, -27, SpringLayout.NORTH, searchPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, aAirportField, -44, SpringLayout.NORTH, searchPanel);
		springLayout.putConstraint(SpringLayout.NORTH, searchPanel, 6, SpringLayout.SOUTH, twoLayoverRadioButton);
		springLayout.putConstraint(SpringLayout.SOUTH, searchPanel, 49, SpringLayout.SOUTH, twoLayoverRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, searchPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, searchPanel, screenWidth, SpringLayout.EAST, frame.getContentPane());
		searchPanel.setBackground(Color.RED);
		frame.getContentPane().add(searchPanel);
		SpringLayout sl_searchPanel = new SpringLayout();
		searchPanel.setLayout(sl_searchPanel);
		
		// Search button in search panel
		JButton searchButton = new JButton("Search");
		sl_searchPanel.putConstraint(SpringLayout.NORTH, searchButton, 0, SpringLayout.NORTH, searchPanel);
		sl_searchPanel.putConstraint(SpringLayout.WEST, searchButton, 20, SpringLayout.WEST, searchPanel);
		sl_searchPanel.putConstraint(SpringLayout.SOUTH, searchButton, 43, SpringLayout.NORTH, searchPanel);
		sl_searchPanel.putConstraint(SpringLayout.EAST, searchButton, 161, SpringLayout.WEST, searchPanel);
		searchButton.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		searchPanel.add(searchButton);
		
		// Seating label for search
		JLabel seatingLabel = new JLabel("Seating");
		springLayout.putConstraint(SpringLayout.WEST, dDateLabel, 82, SpringLayout.EAST, seatingLabel);
		springLayout.putConstraint(SpringLayout.NORTH, seatingLabel, 6, SpringLayout.SOUTH, wRibbon);
		springLayout.putConstraint(SpringLayout.WEST, seatingLabel, 65, SpringLayout.EAST, layoverLabel);
		seatingLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		frame.getContentPane().add(seatingLabel);
		
		// Radio button for first class seating
		JRadioButton firstClassRadioButton = new JRadioButton("First class");
		springLayout.putConstraint(SpringLayout.NORTH, firstClassRadioButton, 0, SpringLayout.NORTH, roundTripRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, firstClassRadioButton, 0, SpringLayout.WEST, seatingLabel);
		firstClassRadioButton.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		frame.getContentPane().add(firstClassRadioButton);
		
		// Radio button for coach seating
		JRadioButton coachRadioButton = new JRadioButton("Coach");
		coachRadioButton.setSelected(true);
		springLayout.putConstraint(SpringLayout.NORTH, coachRadioButton, 0, SpringLayout.NORTH, oneWayRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, coachRadioButton, 0, SpringLayout.WEST, seatingLabel);
		coachRadioButton.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		frame.getContentPane().add(coachRadioButton);
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
	
	
	/**
	 * This method acquires the three-digit airport code
	 * provided by the user as the departure airport
	 * 
	 * @return The three-digit departure airport code
	 */
	public String getDepartureAirport() {
		return dAirportField.getText();
	}
	
	
	/**
	 * This method acquires the three-digit airport code
	 * provided by the user as the arrival airport
	 * 
	 * @return The three-digit arrival airport code
	 */
	public String getArrivalAirport() {
		return aAirportField.getText();
	}
}
