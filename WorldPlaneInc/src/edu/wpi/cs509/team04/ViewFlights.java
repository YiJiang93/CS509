package edu.wpi.cs509.team04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JList;

/**
 * The ViewFlights class represents the main graphical user interface (GUI)
 * to the flight reservation system. It is the key class with which any user
 * to the system must interact to perform any operation.
 * 
 * @author Alexander W. Witt
 * @version March 14, 2016
 */
public class ViewFlights implements ListSelectionListener {
	
	private JFrame frame;
	private JTextField departureAirportEntryBox;
	private JTextField arrivalAirportEntryBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFlights window = new ViewFlights();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewFlights() {
		initialize();
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
		springLayout.putConstraint(SpringLayout.WEST, departureAirportLabel, 34, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(departureAirportLabel);
		
		// Configuration of layout for text box used for user entry of departure airport
		departureAirportEntryBox = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, departureAirportEntryBox, -6, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, departureAirportEntryBox, 6, SpringLayout.EAST, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.EAST, departureAirportEntryBox, 262, SpringLayout.EAST, departureAirportLabel);
		frame.getContentPane().add(departureAirportEntryBox);
		departureAirportEntryBox.setColumns(10);
		
		// Configuration of layout for label indicating arrival airport
		JLabel arrivalAirportLabel = new JLabel("Arrival Airport:");
		springLayout.putConstraint(SpringLayout.NORTH, arrivalAirportLabel, 0, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, arrivalAirportLabel, 38, SpringLayout.EAST, departureAirportEntryBox);
		frame.getContentPane().add(arrivalAirportLabel);
		
		// Configuration of layout for text box used for user entry of arrival airport
		arrivalAirportEntryBox = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, arrivalAirportEntryBox, -6, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, arrivalAirportEntryBox, 6, SpringLayout.EAST, arrivalAirportLabel);
		springLayout.putConstraint(SpringLayout.EAST, arrivalAirportEntryBox, -201, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(arrivalAirportEntryBox);
		arrivalAirportEntryBox.setColumns(10);
		
		// Configuration of layout and on click activity for departure and arrival airport search button
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			
			/**
			 * This method defines the activity that will be
			 * executed every time that the search button is
			 * clicked by the mouse icon.
			 * @param e An event in which the mouse clicks on the search button
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Search Button Clicked");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, -5, SpringLayout.NORTH, departureAirportLabel);
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 36, SpringLayout.EAST, arrivalAirportEntryBox);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, -26, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnSearch);
		
		// Configuration of layout for horizontal line used for visually separating different components of the view
		JSeparator separator = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator, 16, SpringLayout.SOUTH, btnSearch);
		springLayout.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, separator, 32, SpringLayout.SOUTH, btnSearch);
		springLayout.putConstraint(SpringLayout.EAST, separator, 1000, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(separator);
		
		// Configuration of layout for arrival date filter label
		JLabel arrivalDateLabel = new JLabel("Arrival Date:");
		springLayout.putConstraint(SpringLayout.WEST, arrivalDateLabel, 0, SpringLayout.WEST, departureAirportLabel);
		frame.getContentPane().add(arrivalDateLabel);
		
		// Configuration of layout for calendar selection of arrival date
		JXDatePicker selectArrivalDate = new JXDatePicker();
		springLayout.putConstraint(SpringLayout.NORTH, selectArrivalDate, 21, SpringLayout.SOUTH, arrivalDateLabel);
		springLayout.putConstraint(SpringLayout.WEST, selectArrivalDate, 0, SpringLayout.WEST, departureAirportLabel);
		frame.getContentPane().add(selectArrivalDate);
		
		// Configuration of layout for vertical line used for visually separating different components of the view
		JSeparator separator_1 = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator_1, 23, SpringLayout.SOUTH, departureAirportEntryBox);
		springLayout.putConstraint(SpringLayout.SOUTH, separator_1, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, separator_1, 55, SpringLayout.EAST, selectArrivalDate);
		separator_1.setForeground(new Color(212, 212, 212));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		springLayout.putConstraint(SpringLayout.WEST, separator_1, 35, SpringLayout.EAST, selectArrivalDate);
		frame.getContentPane().add(separator_1);
		
		// Configuration of layout and on click activity for apply filter button
		JButton btnApplyArrivalDateFilter = new JButton("Apply Filter");
		btnApplyArrivalDateFilter.addMouseListener(new MouseAdapter() {
			
			/**
			 * This method defines the activity that will be executed
			 * every time that the apply filter button for arrival date
			 * is clicked by the mouse icon.
			 * @param e An event in which the mouse clicks on the apply filter button for arrival date
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Apply Arrival Data Filter Button Clicked");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnApplyArrivalDateFilter, 23, SpringLayout.SOUTH, selectArrivalDate);
		springLayout.putConstraint(SpringLayout.WEST, btnApplyArrivalDateFilter, 0, SpringLayout.WEST, departureAirportLabel);
		frame.getContentPane().add(btnApplyArrivalDateFilter);
		
		// Configuration of layout for the list of available flights
		JList list = new JList();
		springLayout.putConstraint(SpringLayout.WEST, list, 6, SpringLayout.EAST, separator_1);
		springLayout.putConstraint(SpringLayout.EAST, list, 4, SpringLayout.EAST, btnSearch);
		frame.getContentPane().add(list);
		
		// Configuration of layout for the button used for making flight reservations
		JButton btnMakeReservation = new JButton("Make Reservation");
		btnMakeReservation.addMouseListener(new MouseAdapter() {
			
			/**
			 * This method defines the activity that will be executed every
			 * time that the make reservation button for price is clicked by the mouse icon.
			 * @param e An event in which the mouse clicks on the make reservation button
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Make Reservation Button Clicked");
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, list, -33, SpringLayout.NORTH, btnMakeReservation);
		springLayout.putConstraint(SpringLayout.WEST, btnMakeReservation, 283, SpringLayout.EAST, separator_1);
		springLayout.putConstraint(SpringLayout.SOUTH, btnMakeReservation, -30, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnMakeReservation);
		
		// Configuration of layout for available flights label
		JLabel availableFlightsLabel = new JLabel("Available Flights");
		springLayout.putConstraint(SpringLayout.NORTH, availableFlightsLabel, 2, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.NORTH, arrivalDateLabel, 0, SpringLayout.NORTH, availableFlightsLabel);
		springLayout.putConstraint(SpringLayout.NORTH, list, 14, SpringLayout.SOUTH, availableFlightsLabel);
		springLayout.putConstraint(SpringLayout.WEST, availableFlightsLabel, 306, SpringLayout.EAST, separator_1);
		frame.getContentPane().add(availableFlightsLabel);
		
		// Configuration of layout for filter by price label
		JLabel filterByPriceLabel = new JLabel("Filter Flights by Price:");
		springLayout.putConstraint(SpringLayout.NORTH, filterByPriceLabel, 34, SpringLayout.SOUTH, btnApplyArrivalDateFilter);
		springLayout.putConstraint(SpringLayout.WEST, filterByPriceLabel, 0, SpringLayout.WEST, departureAirportLabel);
		frame.getContentPane().add(filterByPriceLabel);
		
		// Configuration of layout and actions for apply filter for price button
		JButton btnApplyPriceFilter = new JButton("Apply Filter");
		btnApplyPriceFilter.addMouseListener(new MouseAdapter() {
			
			/**
			 * This method defines the activity that will be executed every
			 * time that the apply filter button for price is clicked by the mouse icon.
			 * @param e An event in which the mouse clicks on the apply filter button for price
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Apply Price Filter Button Clicked");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnApplyPriceFilter, 26, SpringLayout.SOUTH, filterByPriceLabel);
		springLayout.putConstraint(SpringLayout.WEST, btnApplyPriceFilter, 0, SpringLayout.WEST, departureAirportLabel);
		frame.getContentPane().add(btnApplyPriceFilter);
		
		// Configuration of layout for first horizontal separator in filter column of view
		JSeparator separator_2 = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator_2, 6, SpringLayout.SOUTH, btnApplyArrivalDateFilter);
		springLayout.putConstraint(SpringLayout.WEST, separator_2, 0, SpringLayout.WEST, separator);
		springLayout.putConstraint(SpringLayout.SOUTH, separator_2, -87, SpringLayout.SOUTH, btnApplyPriceFilter);
		springLayout.putConstraint(SpringLayout.EAST, separator_2, 6, SpringLayout.WEST, separator_1);
		frame.getContentPane().add(separator_2);
		
		// Configuration of layout for second horizontal separator in filter column of view
		JSeparator separator_3 = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator_3, 6, SpringLayout.SOUTH, btnApplyPriceFilter);
		springLayout.putConstraint(SpringLayout.WEST, separator_3, 0, SpringLayout.WEST, separator);
		springLayout.putConstraint(SpringLayout.EAST, separator_3, 6, SpringLayout.WEST, separator_1);
		frame.getContentPane().add(separator_3);
	}

	/**
	 * This method responds when the user's selection of
	 * a flight in the list of available flights has changed.
	 * @param e An event indicating that a new selection in the list has been made
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	}
}
