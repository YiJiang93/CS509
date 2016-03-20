package edu.wpi.cs509.team04;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

public class ReservationView {
	
	private JFrame frame;

	public JFrame getWindow() {
		return frame;
	}
	
	/**
	 * Create the application.
	 */
	public ReservationView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Reservation Confirmation");
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnNewButton = new JButton("Confirm");
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 227, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -23, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 0, SpringLayout.NORTH, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -6, SpringLayout.WEST, btnNewButton_1);
		frame.getContentPane().add(btnNewButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Economy");
		rdbtnNewRadioButton.setSelected(true);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton, 143, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Coach");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton_1, 10, SpringLayout.SOUTH, rdbtnNewRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton_1, 0, SpringLayout.WEST, rdbtnNewRadioButton);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JLabel lblSeatingType = new JLabel("Seating Type");
		springLayout.putConstraint(SpringLayout.WEST, lblSeatingType, 143, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblSeatingType, -195, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton, 16, SpringLayout.SOUTH, lblSeatingType);
		frame.getContentPane().add(lblSeatingType);
	}
	
}
