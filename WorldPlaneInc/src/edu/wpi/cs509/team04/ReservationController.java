package edu.wpi.cs509.team04;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ReservationController implements PropertyChangeListener, ListSelectionListener {

	private SearchView sView;
	private ReservationView rView;
	private ReservationModel model;

	public ReservationController(SearchView searchView, ReservationView reserveView, ReservationModel rModel) {
		this.sView = searchView;
		this.rView = reserveView;
		this.model = rModel;
		this.model.addListener(this);
		setupViewEvents();
	}

	private void setupViewEvents() {
		// TODO Auto-generated method stub
		rView.getBtnConfirmReserve().setAction(new AbstractAction("Confirm Reservation") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = sView.getList().getSelectedIndex();
				//TODO: find flight number and add it to the set flight number;
				model.setFlightNumber("TODO");
				if(rView.getRdbtnCoach().isSelected()){
					model.setSeatingType("Coach");
				}
				else{
					model.setSeatingType("Economy");
				}
				
				System.out.println(e.getActionCommand());
			}
		});
				
		rView.getBtnCancelReserve().setAction(new AbstractAction("Cancel Reservation") {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(e.getActionCommand());
			}
		});
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

}
