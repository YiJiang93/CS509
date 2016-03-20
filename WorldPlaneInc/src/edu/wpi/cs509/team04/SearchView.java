package edu.wpi.cs509.team04;

import java.awt.Color;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

public class SearchView {

	private JFrame frame;
	private JButton btnSearch;
	private JTextField departureAirportEntryBox;
	private JTextField arrivalAirportEntryBox;
	private JXDatePicker selectDepartureDate;
	private JButton btnMakeReservation;
	private JButton btnApplyPriceFilter;
	private JList<String> list;
	private DefaultListModel<String> listModel;

	/**
	 * Create the application.
	 */
	public SearchView() {
		initialize();
		frame.setVisible(true);
	}

	public JFrame getWindow() {
		return frame;
	}
	
	public JButton getSearchButton() {
		return btnSearch;
	}
	
	public JButton getReservationButton() {
		return btnMakeReservation;
	}
	
	public JButton getPriceSortButton() {
		return btnApplyPriceFilter;
	}
	
	public JTextField getDepartureAirport() {
		return departureAirportEntryBox;
	}
	
	public JTextField getArrivalAirport() {
		return arrivalAirportEntryBox;
	}
	
	public Date getDepartureDate() {
		return selectDepartureDate.getDate();
	}
	
	public DefaultListModel<String> getListModel() {
		return listModel;
	}
	
	public void addFlight(String flight) {
		listModel.addElement(flight);
	}
	
	public void clearListModel() {
		listModel.removeAllElements();
	}
	
	public JList<String> getList() {
		return list;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Preliminary configuration of the graphical user interface (GUI)
		frame = new JFrame();
		frame.setBounds(200, 200, 1000, 750);
		frame.setTitle("World Plane Inc. Travel System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
				
		// Configuration of layout for label indicating departure airport
		JLabel departureAirportLabel = new JLabel("Departure Airport:");
		springLayout.putConstraint(SpringLayout.NORTH, departureAirportLabel, 26, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, departureAirportLabel, 20, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(departureAirportLabel);
				
		// Configuration of layout for text box used for user entry of departure airport
		departureAirportEntryBox = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, departureAirportEntryBox, -6, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, departureAirportEntryBox, 6, SpringLayout.EAST, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.EAST, departureAirportEntryBox, -706, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(departureAirportEntryBox);
		departureAirportEntryBox.setColumns(10);
				
		// Configuration of layout for label indicating arrival airport
		JLabel arrivalAirportLabel = new JLabel("Arrival Airport:");
		springLayout.putConstraint(SpringLayout.NORTH, arrivalAirportLabel, 0, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, arrivalAirportLabel, 26, SpringLayout.EAST, departureAirportEntryBox);
		frame.getContentPane().add(arrivalAirportLabel);
				
		// Configuration of layout for text box used for user entry of arrival airport
		arrivalAirportEntryBox = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, arrivalAirportEntryBox, -6, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, arrivalAirportEntryBox, 6, SpringLayout.EAST, arrivalAirportLabel);
		springLayout.putConstraint(SpringLayout.EAST, arrivalAirportEntryBox, -427, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(arrivalAirportEntryBox);
		arrivalAirportEntryBox.setColumns(10);
				
		// Configuration of layout and on click activity for departure and arrival airport search button
		btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, -5, SpringLayout.NORTH, departureAirportLabel);
		frame.getContentPane().add(btnSearch);
				
		// Configuration of layout for horizontal line used for visually separating different components of the view
		JSeparator separator = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator, 13, SpringLayout.SOUTH, btnSearch);
		springLayout.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, separator, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(separator);
				
		// Configuration of layout for departure date filter label
		JLabel departureDateLabel = new JLabel("Departure Date:");
		springLayout.putConstraint(SpringLayout.NORTH, departureDateLabel, 0, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, departureDateLabel, 25, SpringLayout.EAST, arrivalAirportEntryBox);
		frame.getContentPane().add(departureDateLabel);
				
		// Configuration of layout for calendar selection of departure date
		selectDepartureDate = new JXDatePicker();
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 20, SpringLayout.EAST, selectDepartureDate);
		springLayout.putConstraint(SpringLayout.NORTH, selectDepartureDate, -6, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, selectDepartureDate, 6, SpringLayout.EAST, departureDateLabel);
		frame.getContentPane().add(selectDepartureDate);
				
		// Configuration of layout for vertical line used for visually separating different components of the view
		JSeparator separator_1 = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator_1, 20, SpringLayout.SOUTH, departureAirportEntryBox);
		springLayout.putConstraint(SpringLayout.SOUTH, separator_1, 0, SpringLayout.SOUTH, frame.getContentPane());
		separator_1.setForeground(new Color(212, 212, 212));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		frame.getContentPane().add(separator_1);
				
		// Configuration of layout for the list of available flights
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(32);
		JScrollPane listScrollPane = new JScrollPane(list);
		springLayout.putConstraint(SpringLayout.EAST, separator_1, -6, SpringLayout.WEST, listScrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, 0, SpringLayout.EAST, listScrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, listScrollPane, 135, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, listScrollPane, 257, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, listScrollPane, -22, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(listScrollPane);
				
		// Configuration of layout for the button used for making flight reservations
		btnMakeReservation = new JButton("Make Reservation");
		springLayout.putConstraint(SpringLayout.WEST, btnMakeReservation, 283, SpringLayout.EAST, separator_1);
		springLayout.putConstraint(SpringLayout.SOUTH, list, -33, SpringLayout.NORTH, btnMakeReservation);
		springLayout.putConstraint(SpringLayout.SOUTH, btnMakeReservation, -30, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnMakeReservation);
				
		// Configuration of layout for available flights label
		JLabel availableFlightsLabel = new JLabel("Available Flights");
		springLayout.putConstraint(SpringLayout.SOUTH, separator, -33, SpringLayout.NORTH, availableFlightsLabel);
		springLayout.putConstraint(SpringLayout.NORTH, availableFlightsLabel, 103, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, availableFlightsLabel, -336, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(availableFlightsLabel);
				
		// Configuration of layout for filter by price label
		JLabel sortByPriceLabel = new JLabel("Sort Flights by Price:");
		springLayout.putConstraint(SpringLayout.WEST, separator_1, 82, SpringLayout.EAST, sortByPriceLabel);
		springLayout.putConstraint(SpringLayout.NORTH, sortByPriceLabel, 0, SpringLayout.NORTH, availableFlightsLabel);
		springLayout.putConstraint(SpringLayout.WEST, sortByPriceLabel, 0, SpringLayout.WEST, departureAirportLabel);
		frame.getContentPane().add(sortByPriceLabel);
				
		// Configuration of layout and actions for apply filter for price button
		btnApplyPriceFilter = new JButton("Sort");
		springLayout.putConstraint(SpringLayout.NORTH, btnApplyPriceFilter, -3, SpringLayout.NORTH, listScrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnApplyPriceFilter, 0, SpringLayout.WEST, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.EAST, btnApplyPriceFilter, 0, SpringLayout.EAST, sortByPriceLabel);
		frame.getContentPane().add(btnApplyPriceFilter);
	}
}
