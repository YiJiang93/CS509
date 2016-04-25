/**
 * File: ReservationView.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import edu.wpi.cs509.team04.common.TravelOption;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JButton;

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
	 * @wbp.parser.entryPoint
	 */
	private static void initialize() {
		
		// Configure the frame for the application window
		frame = new JFrame();
		frame.setBounds(100, 0, 759, 727);
		frame.setTitle("Reservation");
		frame.getContentPane().setLayout(null);
		
		// Configure the panel used for the window background
		JPanel background = new JPanel();
		background.setBounds(0, 0, 759, 707);
		background.setBackground(SystemColor.window);
		frame.getContentPane().add(background);
		background.setLayout(null);
		
		// Configure the panel used for the header
		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		header.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		header.setBounds(6, 6, 746, 91);
		background.add(header);
		header.setLayout(null);
		
		// Configure the label used for the header
		JLabel lblHeader = new JLabel("Confirm Reservation Details");
		lblHeader.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 45));
		lblHeader.setBounds(47, 6, 659, 79);
		header.add(lblHeader);
		
		// Configure the panel used for the selected to-destination option
		JPanel panelToDest = new JPanel();
		panelToDest.setBackground(Color.WHITE);
		panelToDest.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelToDest.setBounds(313, 150, 439, 248);
		background.add(panelToDest);
		
		// Configure the list for the selected to-destination option
		JList<TravelOption> listToDest = new JList<TravelOption>();
		panelToDest.add(listToDest);
		
		// Configure the panel used for the selected from-destination option
		JPanel panelFromDest = new JPanel();
		panelFromDest.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelFromDest.setBackground(Color.WHITE);
		panelFromDest.setBounds(313, 451, 439, 248);
		background.add(panelFromDest);
		
		// Configure the list for the selected from-destination option
		JList<TravelOption> listFromDest = new JList<TravelOption>();
		panelFromDest.add(listFromDest);
		
		// Configure the label for the selected to-destination option
		JLabel lblToDest = new JLabel("To Destination:");
		lblToDest.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblToDest.setBounds(313, 109, 278, 29);
		background.add(lblToDest);
		
		// Configure the label for the selected from-destination option
		JLabel lblFromDest = new JLabel("From Destination:");
		lblFromDest.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblFromDest.setBounds(313, 410, 278, 29);
		background.add(lblFromDest);
		
		// Configure the label for the selected seating
		JLabel lblSelectedSeating = new JLabel("Selected Seating:");
		lblSelectedSeating.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblSelectedSeating.setBounds(45, 188, 225, 29);
		background.add(lblSelectedSeating);
		
		// Configure the radio button for selecting coach seating
		JRadioButton rdbtnCoach = new JRadioButton("Coach");
		rdbtnCoach.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		rdbtnCoach.setBounds(45, 241, 235, 36);
		background.add(rdbtnCoach);
		
		// Configure the radio button for selected first class seating
		JRadioButton rdbtnFirstClass = new JRadioButton("First Class");
		rdbtnFirstClass.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		rdbtnFirstClass.setBounds(45, 297, 235, 36);
		background.add(rdbtnFirstClass);
		
		// Configure the button for canceling the reservation
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btnCancel.setBounds(25, 588, 245, 61);
		background.add(btnCancel);
		
		// Configure the button for confirming the reservation
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btnConfirm.setBounds(25, 501, 245, 61);
		background.add(btnConfirm);
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
