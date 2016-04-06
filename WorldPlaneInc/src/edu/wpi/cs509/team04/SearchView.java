/**
 * File: SearchView.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXDatePicker;

/**
 * The SearchView class provides a graphic user interface with
 * which the user can interact to search for flights matching
 * a given criteria
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version April 2, 2016
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
	private static JTextField txtDepartureAirport;
	
	/**
	 * The text field for entering the arrival airport
	 */
	private static JTextField txtArrivalAirport;
	
	/**
	 * The radio button for selecting a one-way flight
	 */
	private static JRadioButton rdbtnOneWay;
	
	/**
	 * The radio button for selecting a round-trip flight
	 */
	private static JRadioButton rdbtnRoundTrip;
	
	/**
	 * The radio button for selecting coach seating
	 */
	private static JRadioButton rdbtnCoach;
	
	/**
	 * The radio button for selecting first class seating
	 */
	private static JRadioButton rdbtnFirstClass;
	
	/**
	 * The radio button for selecting no lay-overs
	 */
	private static JRadioButton rdbtnNoLayovers;
	
	/**
	 * The radio button for selecting one lay-over
	 */
	private static JRadioButton rdbtnOneLayover;
	
	/**
	 * The radio button for selecting two lay-overs
	 */
	private static JRadioButton rdbtnTwoLayovers;
	
	/**
	 * The calendar selector for the first departure date
	 */
	private static JXDatePicker firstDepartureDate;
	
	/**
	 * The calendar selector for the second departure date
	 */
	private static JXDatePicker secondDepartureDate;
	
	/**
	 * The list model for the to-destination travel options
	 */
	private static DefaultListModel<String> toDestModel;
	
	/**
	 * The list model for the from-destination travel options
	 */
	private static DefaultListModel<String> fromDestModel;
	
	/**
	 * The button for searching for available travel options
	 */
	private static JButton btnSearch;
	
	/**
	 * The button for reserving travel option(s)
	 */
	private static JButton btnReserve;
	
	/**
	 * The button for applying sorting of the travel options by price 
	 */
	private static JButton btnPrice;
	
	/**
	 * The button for applying sorting of the travel options by time
	 */
	private static JButton btnTime;
	
	/**
	 * The label indicating where to enter the first departure date
	 */
	private static JLabel lblFirstDepartureDate;
	
	/**
	 * The label indicating where to enter the second departure date
	 */
	private static JLabel lblSecondDepartureDate;
	
	/**
	 * The label indicating where to find from-destination travel options
	 */
	private static JLabel lblFromDestination;
	
	/**
	 * The scroll pane housing from-destination travel options
	 */
	private static JScrollPane fromDestScrollPane;
	
	/**
	 * The displayed list of from-destination travel options
	 */
	private static JList<String> fromDestList;
	
	/**
	 * The displayed list of to-destination travel options
	 */
	private static JList<String> toDestList;
	
	
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
		
		// Configure the frame used in the background
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Lucida Grande", Font.ITALIC, 22));
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("World Plane Inc. - Travel System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Acquire the dimensions of the screen on the computer
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		// Configure the bounds of the frame used in the background
		frame.setBounds(0, 0, screenWidth, screenHeight);
		
		// Configure the header panel for the search bar
		JPanel searchHeaderPanel = new JPanel();
		searchHeaderPanel.setBounds(0, 0, screenWidth, 84);
		searchHeaderPanel.setBackground(Color.BLACK);
		frame.getContentPane().add(searchHeaderPanel);
		searchHeaderPanel.setLayout(null);
		
		// Configure the label used in the header panel for the search bar
		JLabel lblSearchFlights = new JLabel("Search Flights");
		lblSearchFlights.setFont(new Font("Lucida Grande", Font.ITALIC, 40));
		lblSearchFlights.setForeground(Color.WHITE);
		lblSearchFlights.setBounds(6, 10, 288, 59);
		searchHeaderPanel.add(lblSearchFlights);
		
		// Configure the panel used for entering search criteria
		JPanel searchEntryPanel = new JPanel();
		searchEntryPanel.setBackground(new Color(128, 128, 128));
		searchEntryPanel.setBounds(0, 84, 1440, 166);
		frame.getContentPane().add(searchEntryPanel);
		searchEntryPanel.setLayout(null);
		
		// Configure the label used for indicating where to enter the departure airport
		JLabel lblDepartureAirport = new JLabel("Departure Airport");
		lblDepartureAirport.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblDepartureAirport.setForeground(Color.WHITE);
		lblDepartureAirport.setBounds(6, 37, 176, 26);
		searchEntryPanel.add(lblDepartureAirport);
		
		// Configure the text field used for entering the departure airport code
		txtDepartureAirport = new JTextField();
		txtDepartureAirport.setText("Enter Airport Code...");
		txtDepartureAirport.setForeground(Color.GRAY);
		txtDepartureAirport.setBackground(Color.LIGHT_GRAY);
		txtDepartureAirport.setBounds(6, 73, 176, 40);
		txtDepartureAirport.setHighlighter(null);
		searchEntryPanel.add(txtDepartureAirport);
		txtDepartureAirport.setColumns(10);
		
		// Configure the text field used for entering the arrival airport code
		txtArrivalAirport = new JTextField();
		txtArrivalAirport.setText("Enter Airport Code...");
		txtArrivalAirport.setForeground(Color.GRAY);
		txtArrivalAirport.setBackground(Color.LIGHT_GRAY);
		txtArrivalAirport.setBounds(220, 73, 176, 40);
		txtArrivalAirport.setHighlighter(null);
		searchEntryPanel.add(txtArrivalAirport);
		txtArrivalAirport.setColumns(10);
		
		// Configure the label used for indicating where to enter the arrival airport
		JLabel lblArrivalAirport = new JLabel("Arrival Airport");
		lblArrivalAirport.setForeground(Color.WHITE);
		lblArrivalAirport.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblArrivalAirport.setBounds(220, 38, 176, 24);
		searchEntryPanel.add(lblArrivalAirport);
		
		// Configure the label used for indicating where to enter the type of flight
		JLabel lblFlightType = new JLabel("Flight Type");
		lblFlightType.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblFlightType.setForeground(Color.WHITE);
		lblFlightType.setBounds(446, 37, 118, 24);
		searchEntryPanel.add(lblFlightType);
		
		// Configure the radio button for selecting a one-way flight
		rdbtnOneWay = new JRadioButton("One-Way");
		rdbtnOneWay.setSelected(true);
		rdbtnOneWay.setForeground(Color.WHITE);
		rdbtnOneWay.setBounds(456, 73, 141, 23);
		searchEntryPanel.add(rdbtnOneWay);
		
		// Configure the radio button for selecting a round-trip flight
		rdbtnRoundTrip = new JRadioButton("Round-Trip");
		rdbtnRoundTrip.setSelected(false);
		rdbtnRoundTrip.setForeground(Color.WHITE);
		rdbtnRoundTrip.setBounds(456, 97, 141, 23);
		searchEntryPanel.add(rdbtnRoundTrip);
		
		// Configure the label used for indicating where to enter the seating type
		JLabel lblSeatingType = new JLabel("Seating Type");
		lblSeatingType.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblSeatingType.setForeground(Color.WHITE);
		lblSeatingType.setBounds(609, 38, 132, 23);
		searchEntryPanel.add(lblSeatingType);
		
		// Configure the radio button for selecting coach seating
		rdbtnCoach = new JRadioButton("Coach");
		rdbtnCoach.setSelected(true);
		rdbtnCoach.setForeground(Color.WHITE);
		rdbtnCoach.setBounds(619, 73, 141, 23);
		searchEntryPanel.add(rdbtnCoach);
		
		// Configure the radio button for selecting first class seating
		rdbtnFirstClass = new JRadioButton("First Class");
		rdbtnFirstClass.setSelected(false);
		rdbtnFirstClass.setForeground(Color.WHITE);
		rdbtnFirstClass.setBounds(619, 97, 141, 23);
		searchEntryPanel.add(rdbtnFirstClass);
		
		// Configure the label used for indicating where to entering the lay-over type
		JLabel lblLayovers = new JLabel("Layovers");
		lblLayovers.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblLayovers.setForeground(Color.WHITE);
		lblLayovers.setBounds(788, 37, 83, 24);
		searchEntryPanel.add(lblLayovers);
		
		// Configure the radio button for selecting no lay-overs
		rdbtnNoLayovers = new JRadioButton("No Layovers");
		rdbtnNoLayovers.setSelected(true);
		rdbtnNoLayovers.setForeground(Color.WHITE);
		rdbtnNoLayovers.setBounds(798, 73, 141, 23);
		searchEntryPanel.add(rdbtnNoLayovers);
		
		// Configure the radio button for selecting one lay-over
		rdbtnOneLayover = new JRadioButton("One Layover");
		rdbtnOneLayover.setSelected(false);
		rdbtnOneLayover.setForeground(Color.WHITE);
		rdbtnOneLayover.setBounds(798, 97, 141, 23);
		searchEntryPanel.add(rdbtnOneLayover);
		
		// Configure the radio button for selecting two lay-overs
		rdbtnTwoLayovers = new JRadioButton("Two Layovers");
		rdbtnTwoLayovers.setSelected(false);
		rdbtnTwoLayovers.setForeground(Color.WHITE);
		rdbtnTwoLayovers.setBounds(798, 121, 141, 23);
		searchEntryPanel.add(rdbtnTwoLayovers);
		
		// Configure the means for selecting the first departure date
		firstDepartureDate = new JXDatePicker();
		firstDepartureDate.getEditor().setForeground(Color.BLACK);
		firstDepartureDate.getEditor().setText("");
		firstDepartureDate.getEditor().setBackground(Color.LIGHT_GRAY);
		firstDepartureDate.setBounds(951, 73, 194, 40);
		searchEntryPanel.add(firstDepartureDate);
		
		// Configure the means for selecting the second departure date
		secondDepartureDate = new JXDatePicker();
		secondDepartureDate.getEditor().setForeground(Color.BLACK);
		secondDepartureDate.getEditor().setText("");
		secondDepartureDate.getEditor().setBackground(Color.LIGHT_GRAY);
		secondDepartureDate.setBounds(1197, 73, 194, 40);
		searchEntryPanel.add(secondDepartureDate);
		
		// Configure the label used for indicating where to enter first departure date
		lblFirstDepartureDate = new JLabel("First Departure Date");
		lblFirstDepartureDate.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblFirstDepartureDate.setForeground(Color.WHITE);
		lblFirstDepartureDate.setBounds(953, 37, 205, 26);
		searchEntryPanel.add(lblFirstDepartureDate);
		
		// Configure the label used for indicating where to enter second departure date
		lblSecondDepartureDate = new JLabel("Second Departure Date");
		lblSecondDepartureDate.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblSecondDepartureDate.setForeground(Color.WHITE);
		lblSecondDepartureDate.setBounds(1197, 37, 237, 26);
		searchEntryPanel.add(lblSecondDepartureDate);
		
		// Configure the panel containing the search button
		JPanel searchButtonPanel = new JPanel();
		searchButtonPanel.setBackground(Color.DARK_GRAY);
		searchButtonPanel.setBounds(0, 250, 1440, 43);
		frame.getContentPane().add(searchButtonPanel);
		searchButtonPanel.setLayout(null);
		
		// Configure the button for searching for available flights
		btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.setBounds(6, 8, 117, 29);
		btnSearch.requestFocus();
		searchButtonPanel.add(btnSearch);
		
		// Configure panel used for separating the search panel from the other panels
		JPanel searchDividerPanel = new JPanel();
		searchDividerPanel.setBackground(Color.BLACK);
		searchDividerPanel.setBounds(0, 293, screenWidth, 10);
		frame.getContentPane().add(searchDividerPanel);
		
		// Configure the panel used for displaying the search results
		JPanel searchResultsPanel = new JPanel();
		searchResultsPanel.setBackground(Color.GRAY);
		searchResultsPanel.setBounds(327, 303, screenWidth, 456);
		frame.getContentPane().add(searchResultsPanel);
		searchResultsPanel.setLayout(null);
		
		// Configure the scroll pane used for displaying to-destination flight options
		JScrollPane toDestScrollPane = new JScrollPane();
		toDestScrollPane.setBounds(49, 41, 300, 350);
		searchResultsPanel.add(toDestScrollPane);
		
		// Configure the model used for maintaining to-destination flight options
		toDestModel = new DefaultListModel<String>();
		
		// Configure the list of to-destination flight options
		toDestList = new JList<String>(toDestModel);
		toDestList.setBackground(Color.LIGHT_GRAY);
		toDestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		toDestScrollPane.setViewportView(toDestList);
		
		// Configure the scroll pane used for displaying from-destination flight options
		fromDestScrollPane = new JScrollPane();
		fromDestScrollPane.setBounds(401, 41, 300, 350);
		searchResultsPanel.add(fromDestScrollPane);
		
		// Configure the model used for maintaining to-destination flight options
		fromDestModel = new DefaultListModel<String>();
		
		// Configure the list of from-destination flight options
		fromDestList = new JList<String>(fromDestModel);
		fromDestList.setBackground(Color.LIGHT_GRAY);
		fromDestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fromDestScrollPane.setViewportView(fromDestList);
		
		// Configure the button for reserving travel option(s)
		btnReserve = new JButton("Reserve");
		btnReserve.setBounds(49, 408, 117, 29);
		searchResultsPanel.add(btnReserve);
		
		// Configure the label indicating where to find to-destination flight options
		JLabel lblToDestination = new JLabel("To Destination");
		lblToDestination.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblToDestination.setForeground(Color.WHITE);
		lblToDestination.setBounds(49, 6, 155, 23);
		searchResultsPanel.add(lblToDestination);
		
		// Configure the label indicating where to find from-destination flight options
		lblFromDestination = new JLabel("From Destination");
		lblFromDestination.setForeground(Color.WHITE);
		lblFromDestination.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblFromDestination.setBounds(401, 6, 208, 23);
		searchResultsPanel.add(lblFromDestination);
		
		// Configure the footer panel
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.BLACK);
		footerPanel.setBounds(0, 759, screenWidth, 55);
		frame.getContentPane().add(footerPanel);
		
		// Configure the panel housing different types of sorting options
		JPanel sortPanel = new JPanel();
		sortPanel.setBackground(Color.DARK_GRAY);
		sortPanel.setBounds(0, 303, 327, 456);
		frame.getContentPane().add(sortPanel);
		sortPanel.setLayout(null);
		
		// Configure the panel used for housing the header for the sorting panel
		JPanel sortHeaderPanel = new JPanel();
		sortHeaderPanel.setBackground(Color.BLACK);
		sortHeaderPanel.setBounds(0, 0, 327, 84);
		sortPanel.add(sortHeaderPanel);
		sortHeaderPanel.setLayout(null);
		
		// Configure label or indicating where to find different sorting options
		JLabel lblSortBy = new JLabel("Sort By...");
		lblSortBy.setBounds(6, 17, 261, 48);
		sortHeaderPanel.add(lblSortBy);
		lblSortBy.setForeground(Color.WHITE);
		lblSortBy.setFont(new Font("Lucida Grande", Font.ITALIC, 40));
		
		// Configure the button used for sorting flight options by price
		btnPrice = new JButton("Price");
		btnPrice.setBounds(6, 96, 117, 29);
		sortPanel.add(btnPrice);
		btnPrice.setBackground(Color.LIGHT_GRAY);
		
		// Configure the button used for sorting flight options by time
		btnTime = new JButton("Time");
		btnTime.setBounds(6, 137, 117, 29);
		sortPanel.add(btnTime);
		
		setupOneWayView();
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
	 * This method acquires the text field containing
	 * information entered for the departure airport
	 * 
	 * @return The text field for the departure airport
	 */
	public JTextField getDepartureAirport() {
		return txtDepartureAirport;
	}
	
	
	/**
	 * This method acquires the text field containing
	 * information entered for the arrival airport
	 * 
	 * @return The text field for the arrival airport
	 */
	public JTextField getArrivalAirport() {
		return txtArrivalAirport;
	}
	
	
	/**
	 * This method acquires the radio button for
	 * selecting to find one-way flight travel options
	 * 
	 * @return The radio button for one-way flights
	 */
	public JRadioButton getOneWayRadioButton() {
		return rdbtnOneWay;
	}
	
	
	/**
	 * This method acquires the radio button for
	 * selecting to find round-trip travel options
	 * 
	 * @return The radio button for round-trip flights
	 */
	public JRadioButton getRoundTripRadioButton() {
		return rdbtnRoundTrip;
	}
	
	
	/**
	 * This method acquires the radio button for
	 * selecting to find travel options with coach seating
	 * 
	 * @return The radio button for coach seating
	 */
	public JRadioButton getCoachRadioButton() {
		return rdbtnCoach;
	}
	
	
	/**
	 * This method acquires the radio button for selecting
	 * to find travel options with first class seating
	 * 
	 * @return The radio button for first class seating
	 */
	public JRadioButton getFirstClassRadioButton() {
		return rdbtnFirstClass;
	}
	
	
	/**
	 * This method acquires the radio button for selecting
	 * to find travel options with no lay-overs
	 * 
	 * @return The radio button for no lay-overs
	 */
	public JRadioButton getNoLayoversRadioButton() {
		return rdbtnNoLayovers;
	}
	
	
	/**
	 * This method acquires the radio button for selecting
	 * to find travel options with one lay-over
	 * 
	 * @return The radio button for one lay-over
	 */
	public JRadioButton getOneLayoverRadioButton() {
		return rdbtnOneLayover;
	}
	
	
	/**
	 * This method acquires the radio button for selecting
	 * to find travel options with two lay-overs
	 * 
	 * @return The radio button for two lay-overs
	 */
	public JRadioButton getTwoLayoversRadioButton() {
		return rdbtnTwoLayovers;
	}
	
	
	/**
	 * This method acquires the calendar selector for selecting
	 * to find travel options matching the first departure date
	 * 
	 * @return The calendar selector for the first departure date
	 */
	public JXDatePicker getFirstDepartureDateSelector() {
		return firstDepartureDate;
	}
	
	
	/**
	 * This method acquires the calendar selector for selecting
	 * to find travel options matching the second departure date
	 * 
	 * @return The calendar selector for the second departure date
	 */
	public JXDatePicker getSecondDepartureDateSelector() {
		return secondDepartureDate;
	}
	
	
	/**
	 * This method provides a means for updating the visual
	 * list of to-destination travel options with a travel
	 * option provided as input
	 * 
	 * @param option A new travel option provided as input
	 */
	public void updateToDestList(Collection<TravelOption> options) {
		toDestModel.clear();
		for (TravelOption option : options) {
			toDestModel.addElement(option.toHtmlString());
		}
		if (!toDestModel.isEmpty()) {
			toDestList.setSelectedIndex(0);
		}
	}
	
	
	/**
	 * This method provides a means for updating the visual
	 * list of from-destination travel options with a travel
	 * option provided as input
	 * 
	 * @param option A new travel option provided as input
	 */
	public void updateFromDestList(Collection<TravelOption> options) {
		fromDestModel.clear();
		for (TravelOption option : options) {
			fromDestModel.addElement(option.toHtmlString());
		}
		if (!fromDestModel.isEmpty()) {
			fromDestList.setSelectedIndex(0);
		}
	}
	
	
	/**
	 * This method acquires the button for searching travel options
	 * @return The button for searching travel options
	 */
	public JButton getSearchButton() {
		return btnSearch;
	}
	
	
	/**
	 * This method acquires the button for reserving a travel option(s)
	 * @return The button for reserving travel option(s)
	 */
	public JButton getReserveButton() {
		return btnReserve;
	}
	
	
	/**
	 * This method acquires the button for sorting travel options by price
	 * @return The button for sorting travel options by price
	 */
	public JButton getPriceSortButton() {
		return btnPrice;
	}
	
	
	/**
	 * This method acquires the button for sorting travel options by time
	 * @return The button for sorting travel options by time
	 */
	public JButton getTimeSortButton() {
		return btnTime;
	}
	
	
	/**
	 * This method acquires the displayed graphical list 
	 * of to-destination travel options
	 * 
	 * @return The displayed list of to-destination travel options
	 */
	public JList<String> getToDestList() {
		return toDestList;
	}
	
	
	/**
	 * This method acquires the displayed graphical list
	 * of from-destination travel options
	 * 
	 * @return The displayed list of from-destination travel options
	 */
	public JList<String> getFromDestList() {
		return fromDestList;
	}
	
	
	/**
	 * This method modifies the view so that it only displays graphical
	 * objects that are relevant when searching for a one-way travel option
	 */
	public static void setupOneWayView() {
		lblFirstDepartureDate.setText("Departure Date");
		lblSecondDepartureDate.setVisible(false);
		lblFromDestination.setVisible(false);
		fromDestScrollPane.setVisible(false);
		fromDestList.setVisible(false);
		secondDepartureDate.setVisible(false);
	}
	
	
	/**
	 * This method modifies the view so that it only displays graphical
	 * objects that are relevant when searching for round-trip travel options
	 */
	public static void setupRoundTripView() {
		lblFirstDepartureDate.setText("First Departure Date");
		lblSecondDepartureDate.setVisible(true);
		lblFromDestination.setVisible(true);
		fromDestScrollPane.setVisible(true);
		fromDestList.setVisible(true);
		secondDepartureDate.setVisible(true);
	}
}
