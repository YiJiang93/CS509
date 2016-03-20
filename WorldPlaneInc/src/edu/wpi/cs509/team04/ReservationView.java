package edu.wpi.cs509.team04;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

public class ReservationView {
	
	private JFrame frame;
	private JButton btnCancelReserve;
	private JButton btnConfirmReserve;
	private JRadioButton rdbtnEconomy;
	private JRadioButton rdbtnCoach;
	
	public JFrame getWindow() {
		return frame;
	}
	
	public JButton getBtnCancelReserve() {
		return btnCancelReserve;
	}

	public void setBtnCancelReserve(JButton btnCancelReserve) {
		this.btnCancelReserve = btnCancelReserve;
	}

	public JButton getBtnConfirmReserve() {
		return btnConfirmReserve;
	}

	public void setBtnConfirmReserve(JButton btnConfirmReserve) {
		this.btnConfirmReserve = btnConfirmReserve;
	}

	public JRadioButton getRdbtnEconomy() {
		return rdbtnEconomy;
	}

	public void setRdbtnEconomy(JRadioButton rdbtnEconomy) {
		this.rdbtnEconomy = rdbtnEconomy;
	}

	public JRadioButton getRdbtnCoach() {
		return rdbtnCoach;
	}

	public void setRdbtnCoach(JRadioButton rdbtnCoach) {
		this.rdbtnCoach = rdbtnCoach;
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
		
		btnConfirmReserve = new JButton("Confirm");
		frame.getContentPane().add(btnConfirmReserve);
		
		btnCancelReserve = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.WEST, btnCancelReserve, 227, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancelReserve, -23, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirmReserve, 0, SpringLayout.NORTH, btnCancelReserve);
		springLayout.putConstraint(SpringLayout.EAST, btnConfirmReserve, -6, SpringLayout.WEST, btnCancelReserve);
		frame.getContentPane().add(btnCancelReserve);
		
		rdbtnEconomy = new JRadioButton("Economy");
		rdbtnEconomy.setSelected(true);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnEconomy, 143, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(rdbtnEconomy);
		
		rdbtnCoach = new JRadioButton("Coach");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnCoach, 10, SpringLayout.SOUTH, rdbtnEconomy);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnCoach, 0, SpringLayout.WEST, rdbtnEconomy);
		frame.getContentPane().add(rdbtnCoach);
		
		JLabel lblSeatingType = new JLabel("Seating Type");
		springLayout.putConstraint(SpringLayout.WEST, lblSeatingType, 143, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblSeatingType, -195, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnEconomy, 16, SpringLayout.SOUTH, lblSeatingType);
		frame.getContentPane().add(lblSeatingType);
	}
	
}
