/**
 * File: ReservationView.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
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
	 * The radio button for selecting coach seating for the
	 * initial to-destination flight
	 */
	private static JRadioButton rdbtnToInitialFlightCoach;
	
	/**
	 * The radio button for selecting first class seating for
	 * the initial to-destination flight
	 */
	private static JRadioButton rdbtnToInitialFlightFirstClass;
	
	/**
	 * The radio button for selecting coach seating for the
	 * first lay-over to-destination flight
	 */
	private static JRadioButton rdbtnToFirstLayoverCoach;
	
	/**
	 * The radio button for selecting first class seating for
	 * the first lay-over to-destination flight
	 */
	private static JRadioButton rdbtnToFirstLayoverFirstClass;
	
	/**
	 * The radio button for selecting coach seating for the
	 * second lay-over to-destination flight
	 */
	private static JRadioButton rdbtnToSecondLayoverCoach;
	
	/**
	 * The radio button for selecting first class seating for
	 * the second lay-over to-destination flight
	 */
	private static JRadioButton rdbtnToSecondLayoverFirstClass;
	
	/**
	 * The button for confirming the flight reservation
	 */
	private static JButton btnConfirm;
	
	/**
	 * The button for canceling the flight reservation
	 */
	private static JButton btnCancel;
	
	/**
	 * The radio button for selecting coach seating for the
	 * initial from-destination flight
	 */
	private static JRadioButton rdbtnFromInitialCoach;
	
	/**
	 * The radio button for selecting first class seating for
	 * the initial from-destination flight
	 */
	private static JRadioButton rdbtnFromInitialFirstClass;
	
	/**
	 * The radio button for selecting coach seating for the
	 * first lay-over from-destination flight
	 */
	private static JRadioButton rdbtnFromFirstLayoverCoach;
	
	/**
	 * The radio button for selecting first class seating for the
	 * first lay-over from-destination flight
	 */
	private static JRadioButton rdbtnFromFirstLayoverFirstClass;
	
	/**
	 * The radio button for selecting coach seating for the
	 * second lay-over from-destination flight
	 */
	private static JRadioButton rdbtnFromSecondLayoverCoach;
	
	/**
	 * The radio button for selecting first class seating for
	 * the second lay-over from-destination flight
	 */
	private static JRadioButton rdbtnFromSecondLayoverFirstClass;
	
	/**
	 * The model for the to-destination flight option
	 * that had been selected by the user
	 */
	private static DefaultListModel<String> toDestModel;
	
	/**
	 * The model for the from-destination flight option
	 * that had been selected by the user
	 */
	private static DefaultListModel<String> fromDestModel;
	
	/**
	 * The visual list containing the to-destination flight
	 * option that had been selected by the user
	 */
	private static JList<String> toDestList;
	
	/**
	 * The visual list containing the from-destination flight
	 * option that had been selected by the user
	 */
	private static JList<String> fromDestList;
	
	
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
		
		// Configure the frame used in the background
		frame = new JFrame();
		frame.setBounds(100, 100, 945, 800);
		frame.setTitle("Reservation");
		frame.getContentPane().setLayout(null);
		
		// Configure the panel used for the body
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(Color.GRAY);
		bodyPanel.setBounds(0, 81, 945, 697);
		frame.getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);
		
		// Configure the panel for the seating info header
		JPanel toDestSeatingPanel = new JPanel();
		toDestSeatingPanel.setBackground(Color.DARK_GRAY);
		toDestSeatingPanel.setBounds(0, 22, 247, 97);
		bodyPanel.add(toDestSeatingPanel);
		toDestSeatingPanel.setLayout(null);
		
		// Configure the label for the seating info header
		JLabel lblSeatingInfo = new JLabel("To Destination");
		lblSeatingInfo.setFont(new Font("Lucida Grande", Font.ITALIC, 30));
		lblSeatingInfo.setForeground(new Color(255, 255, 255));
		lblSeatingInfo.setBounds(6, 19, 235, 48);
		toDestSeatingPanel.add(lblSeatingInfo);
		
		// Configure the panel for the separator panel
		JPanel separatorPanel = new JPanel();
		separatorPanel.setBackground(Color.GRAY);
		separatorPanel.setBounds(0, 0, 945, 22);
		bodyPanel.add(separatorPanel);
		
		// Configure the panel for the seating radio buttons
		JPanel radioToButtonPanel = new JPanel();
		radioToButtonPanel.setBackground(Color.GRAY);
		radioToButtonPanel.setBounds(0, 119, 247, 517);
		bodyPanel.add(radioToButtonPanel);
		radioToButtonPanel.setLayout(null);
		
		// Configure the label for the initial flight radio buttons
		JLabel lblToInitialFlight = new JLabel("Initial Flight");
		lblToInitialFlight.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblToInitialFlight.setForeground(Color.WHITE);
		lblToInitialFlight.setBounds(6, 9, 122, 25);
		radioToButtonPanel.add(lblToInitialFlight);
		
		// Configure the radio button for initial flight coach seating
		rdbtnToInitialFlightCoach = new JRadioButton("Coach");
		rdbtnToInitialFlightCoach.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnToInitialFlightCoach.setForeground(Color.WHITE);
		rdbtnToInitialFlightCoach.setBounds(16, 46, 141, 23);
		radioToButtonPanel.add(rdbtnToInitialFlightCoach);
		
		// Configure the radio button for initial flight first class seating
		rdbtnToInitialFlightFirstClass = new JRadioButton("First Class");
		rdbtnToInitialFlightFirstClass.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnToInitialFlightFirstClass.setForeground(Color.WHITE);
		rdbtnToInitialFlightFirstClass.setBounds(16, 69, 141, 23);
		radioToButtonPanel.add(rdbtnToInitialFlightFirstClass);
		
		// Configure the label for the first lay-over radio buttons
		JLabel lblToFirstLayover = new JLabel("First Layover");
		lblToFirstLayover.setForeground(Color.WHITE);
		lblToFirstLayover.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblToFirstLayover.setBounds(6, 146, 122, 25);
		radioToButtonPanel.add(lblToFirstLayover);
		
		// Configure the first lay-over coach seating radio button
		rdbtnToFirstLayoverCoach = new JRadioButton("Coach");
		rdbtnToFirstLayoverCoach.setForeground(Color.WHITE);
		rdbtnToFirstLayoverCoach.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnToFirstLayoverCoach.setBounds(16, 175, 141, 23);
		radioToButtonPanel.add(rdbtnToFirstLayoverCoach);
		
		// Configure the first lay-over first class seating radio button
		rdbtnToFirstLayoverFirstClass = new JRadioButton("First Class");
		rdbtnToFirstLayoverFirstClass.setForeground(Color.WHITE);
		rdbtnToFirstLayoverFirstClass.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnToFirstLayoverFirstClass.setBounds(16, 199, 141, 23);
		radioToButtonPanel.add(rdbtnToFirstLayoverFirstClass);
		
		// Configure the label for the second lay-over radio buttons
		JLabel lblToSecondLayover = new JLabel("Second Layover");
		lblToSecondLayover.setForeground(Color.WHITE);
		lblToSecondLayover.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblToSecondLayover.setBounds(6, 267, 164, 25);
		radioToButtonPanel.add(lblToSecondLayover);
		
		// Configure the second lay-over coach seating radio button
		rdbtnToSecondLayoverCoach = new JRadioButton("Coach");
		rdbtnToSecondLayoverCoach.setForeground(Color.WHITE);
		rdbtnToSecondLayoverCoach.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnToSecondLayoverCoach.setBounds(16, 296, 141, 23);
		radioToButtonPanel.add(rdbtnToSecondLayoverCoach);
		
		// Configure the second lay-over first class seating radio button
		rdbtnToSecondLayoverFirstClass = new JRadioButton("First Class");
		rdbtnToSecondLayoverFirstClass.setForeground(Color.WHITE);
		rdbtnToSecondLayoverFirstClass.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnToSecondLayoverFirstClass.setBounds(16, 320, 141, 23);
		radioToButtonPanel.add(rdbtnToSecondLayoverFirstClass);
		
		// Configure the to-destination flight option header panel
		JPanel toDestHeaderPanel = new JPanel();
		toDestHeaderPanel.setLayout(null);
		toDestHeaderPanel.setBackground(Color.DARK_GRAY);
		toDestHeaderPanel.setBounds(592, 22, 353, 89);
		bodyPanel.add(toDestHeaderPanel);
		
		// Configure the label for the to-destination flight option
		JLabel lblToDestination = new JLabel("To Destination");
		lblToDestination.setForeground(Color.WHITE);
		lblToDestination.setFont(new Font("Lucida Grande", Font.ITALIC, 30));
		lblToDestination.setBounds(6, 19, 235, 48);
		toDestHeaderPanel.add(lblToDestination);
		
		// Configure the from-destination flight option header panel
		JPanel fromDestHeaderPanel = new JPanel();
		fromDestHeaderPanel.setLayout(null);
		fromDestHeaderPanel.setBackground(Color.DARK_GRAY);
		fromDestHeaderPanel.setBounds(592, 323, 353, 89);
		bodyPanel.add(fromDestHeaderPanel);
		
		// Configure the label for the from-destination flight option
		JLabel lblFromDestination = new JLabel("From Destination");
		lblFromDestination.setForeground(Color.WHITE);
		lblFromDestination.setFont(new Font("Lucida Grande", Font.ITALIC, 30));
		lblFromDestination.setBounds(6, 19, 304, 48);
		fromDestHeaderPanel.add(lblFromDestination);
		
		// Configure the to-destination flight option panel
		JPanel toDestPanel = new JPanel();
		toDestPanel.setBackground(Color.DARK_GRAY);
		toDestPanel.setBounds(592, 109, 353, 215);
		bodyPanel.add(toDestPanel);
		toDestPanel.setLayout(null);
		
		// Configure the to-destination option scroll pane
		JScrollPane toDestScrollPane = new JScrollPane();
		toDestScrollPane.setBounds(6, 6, 341, 203);
		toDestPanel.add(toDestScrollPane);
		
		// Configure the model for the to-destination flight option
		toDestModel = new DefaultListModel<String>();
		
		// Configure the list for the to-destination flight option
		toDestList = new JList<String>(toDestModel);
		toDestList.setBackground(Color.LIGHT_GRAY);
		toDestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		toDestScrollPane.setViewportView(toDestList);
		
		// Configure the from-destination flight option panel
		JPanel fromDestPanel = new JPanel();
		fromDestPanel.setBackground(Color.DARK_GRAY);
		fromDestPanel.setBounds(592, 412, 353, 226);
		bodyPanel.add(fromDestPanel);
		fromDestPanel.setLayout(null);
		
		// Configure the list for the from-destination flight option
		JScrollPane fromDestScrollPane = new JScrollPane();
		fromDestScrollPane.setBounds(6, 6, 341, 212);
		fromDestPanel.add(fromDestScrollPane);
		
		// Configure the model for the from-destination flight option
		fromDestModel = new DefaultListModel<String>();
		
		// Configure the list for the from-destination flight option
		fromDestList = new JList<String>(fromDestModel);
		fromDestList.setBackground(Color.LIGHT_GRAY);
		fromDestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fromDestScrollPane.setViewportView(fromDestList);
		
		// Configure the pane for the confirmation and cancel buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.DARK_GRAY);
		buttonPanel.setBounds(0, 637, 945, 60);
		bodyPanel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		// Configure the confirmation button
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(6, 19, 117, 29);
		buttonPanel.add(btnConfirm);
		
		// Configure the cancel button
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(124, 19, 117, 29);
		buttonPanel.add(btnCancel);
		
		// Configure the from-destination seating panel
		JPanel fromDestSeatingPanel = new JPanel();
		fromDestSeatingPanel.setLayout(null);
		fromDestSeatingPanel.setBackground(Color.DARK_GRAY);
		fromDestSeatingPanel.setBounds(282, 22, 275, 97);
		bodyPanel.add(fromDestSeatingPanel);
		
		// Configure the from-destination seating label
		JLabel lblFromDestination_1 = new JLabel("From Destination");
		lblFromDestination_1.setForeground(Color.WHITE);
		lblFromDestination_1.setFont(new Font("Lucida Grande", Font.ITALIC, 30));
		lblFromDestination_1.setBounds(6, 19, 250, 48);
		fromDestSeatingPanel.add(lblFromDestination_1);
		
		// Configure the first vertical separator
		JPanel verticalSeparator1 = new JPanel();
		verticalSeparator1.setLayout(null);
		verticalSeparator1.setBackground(Color.DARK_GRAY);
		verticalSeparator1.setBounds(243, 22, 40, 616);
		bodyPanel.add(verticalSeparator1);
		
		// Configure the second vertical separator
		JPanel verticalSeparator2 = new JPanel();
		verticalSeparator2.setLayout(null);
		verticalSeparator2.setBackground(Color.DARK_GRAY);
		verticalSeparator2.setBounds(554, 20, 40, 618);
		bodyPanel.add(verticalSeparator2);
		
		// Configure the label for from-destination initial flight seating
		JLabel lblFromInitialFlight = new JLabel("Initial Flight");
		lblFromInitialFlight.setForeground(Color.WHITE);
		lblFromInitialFlight.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblFromInitialFlight.setBounds(292, 128, 122, 25);
		bodyPanel.add(lblFromInitialFlight);
		
		// Configure radio button for initial from-destination coach seating
		rdbtnFromInitialCoach = new JRadioButton("Coach");
		rdbtnFromInitialCoach.setForeground(Color.WHITE);
		rdbtnFromInitialCoach.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnFromInitialCoach.setBounds(295, 165, 141, 23);
		bodyPanel.add(rdbtnFromInitialCoach);
		
		// Configure radio button for initial from-destination first class seating
		rdbtnFromInitialFirstClass = new JRadioButton("First Class");
		rdbtnFromInitialFirstClass.setForeground(Color.WHITE);
		rdbtnFromInitialFirstClass.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnFromInitialFirstClass.setBounds(295, 188, 141, 23);
		bodyPanel.add(rdbtnFromInitialFirstClass);
		
		// Configure the label for from-destination first lay-over flight seating
		JLabel lblFromFirstLayover = new JLabel("First Layover");
		lblFromFirstLayover.setForeground(Color.WHITE);
		lblFromFirstLayover.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblFromFirstLayover.setBounds(295, 261, 122, 25);
		bodyPanel.add(lblFromFirstLayover);
		
		// Configure radio button for first lay-over from-destination coach seating
		rdbtnFromFirstLayoverCoach = new JRadioButton("Coach");
		rdbtnFromFirstLayoverCoach.setForeground(Color.WHITE);
		rdbtnFromFirstLayoverCoach.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnFromFirstLayoverCoach.setBounds(295, 289, 141, 23);
		bodyPanel.add(rdbtnFromFirstLayoverCoach);
		
		// Configure radio button for first lay-over from-destination first class seating
		rdbtnFromFirstLayoverFirstClass = new JRadioButton("First Class");
		rdbtnFromFirstLayoverFirstClass.setForeground(Color.WHITE);
		rdbtnFromFirstLayoverFirstClass.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnFromFirstLayoverFirstClass.setBounds(295, 314, 141, 23);
		bodyPanel.add(rdbtnFromFirstLayoverFirstClass);
		
		// Configure the label for from-destination second lay-over flight seating
		JLabel lblFromSecondLayer = new JLabel("Second Layover");
		lblFromSecondLayer.setForeground(Color.WHITE);
		lblFromSecondLayer.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblFromSecondLayer.setBounds(295, 382, 164, 25);
		bodyPanel.add(lblFromSecondLayer);
		
		// Configure radio button for second lay-over from-destination coach seating
		rdbtnFromSecondLayoverCoach = new JRadioButton("Coach");
		rdbtnFromSecondLayoverCoach.setForeground(Color.WHITE);
		rdbtnFromSecondLayoverCoach.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnFromSecondLayoverCoach.setBounds(295, 412, 141, 23);
		bodyPanel.add(rdbtnFromSecondLayoverCoach);
		
		// Configure radio button for second lay-over from-destination first class seating
		rdbtnFromSecondLayoverFirstClass = new JRadioButton("First Class");
		rdbtnFromSecondLayoverFirstClass.setForeground(Color.WHITE);
		rdbtnFromSecondLayoverFirstClass.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnFromSecondLayoverFirstClass.setBounds(295, 438, 141, 23);
		bodyPanel.add(rdbtnFromSecondLayoverFirstClass);
		
		// Configure the header panel containing the title
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.DARK_GRAY);
		headerPanel.setBounds(0, 0, 945, 82);
		frame.getContentPane().add(headerPanel);
		headerPanel.setLayout(null);
		
		// Configure the label for the header
		JLabel lblConfirmation = new JLabel("Confirmation");
		lblConfirmation.setFont(new Font("Lucida Grande", Font.ITALIC, 40));
		lblConfirmation.setForeground(Color.WHITE);
		lblConfirmation.setBounds(6, 17, 264, 42);
		headerPanel.add(lblConfirmation);
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
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting coach seating for the initial to-destination
	 * flight
	 */
	public JRadioButton getToInitialCoachRadioButton() {
		return rdbtnToInitialFlightCoach;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting first class seating for the initial to-destination
	 * flight
	 */
	public JRadioButton getToInitialFirstClassRadioButton() {
		return rdbtnToInitialFlightFirstClass;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting coach seating for the first to-destination
	 * lay-over flight
	 */
	public JRadioButton getToFirstLayoverCoachRadioButton() {
		return rdbtnToFirstLayoverCoach;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting first class seating for the first to-destination
	 * lay-over flight
	 */
	public JRadioButton getToFirstLayoverFirstClassRadioButton() {
		return rdbtnToFirstLayoverFirstClass;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting coach seating for the second to-destination
	 * lay-over flight
	 */
	public JRadioButton getToSecondLayoverCoachRadioButton() {
		return rdbtnToSecondLayoverCoach;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting first class seating for the second to-destination
	 * lay-over flight
	 */
	public JRadioButton getToSecondLayoverFirstClassRadioButton() {
		return rdbtnToSecondLayoverFirstClass;
	}
	
	
	/**
	 * This method acquires the button for confirming
	 * the current reservation that is being made
	 */
	public JButton getConfirmButton() {
		return btnConfirm;
	}
	
	
	/**
	 * This method acquires the button for canceling
	 * the current reservation that is being made
	 */
	public JButton getCancelButton() {
		return btnCancel;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting coach seating for the initial 
	 * from-destination flight
	 */
	public JRadioButton getFromInitialCoachRadioButton() {
		return rdbtnFromInitialCoach;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting first class seating for the initial 
	 * from-destination flight
	 */
	public JRadioButton getFromInitialFirstClassRadioButton() {
		return rdbtnFromInitialFirstClass;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting coach seating for the first from-destination
	 * lay-over flight
	 */
	public JRadioButton getFromFirstLayoverCoachRadioButton() {
		return rdbtnFromFirstLayoverCoach;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting first class seating for the first from-destination
	 * lay-over flight
	 */
	public JRadioButton getFromFirstLayoverFirstClassRadioButton() {
		return rdbtnFromFirstLayoverFirstClass;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting coach seating for the second from-destination
	 * lay-over flight
	 */
	public JRadioButton getFromSecondLayoverCoachRadioButton() {
		return rdbtnFromSecondLayoverCoach;
	}
	
	
	/**
	 * This method acquires the radio button used for
	 * selecting first class seating for the second from-destination
	 * lay-over flight
	 */
	public JRadioButton getFromSecondLayoverFirstClassRadioButton() {
		return rdbtnFromSecondLayoverFirstClass;
	}
	
	
	/**
	 * This method updates the to-destination travel
	 * option being displayed to the user
	 * 
	 * @param option The new to-destination travel option
	 */
	public void addToDestinationModel(String option) {
		toDestModel.clear();
		if (option != null) {
			toDestModel.addElement(option);
			toDestList.setSelectedIndex(0);
		}
	}
	
	
	/**
	 * This method updates the from-destination travel
	 * option being displayed to the user
	 * 
	 * @param option The new from-destination travel option
	 */
	public void addFromDestinationModel(String option) {
		fromDestModel.clear();
		if (option != null) {
			fromDestModel.addElement(option);
			fromDestList.setSelectedIndex(0);
		}
	}
}
