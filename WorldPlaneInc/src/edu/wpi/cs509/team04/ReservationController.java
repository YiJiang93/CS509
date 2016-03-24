package edu.wpi.cs509.team04;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ReservationController implements PropertyChangeListener, ListSelectionListener {

	private SearchView sView;
	private ReservationView rView;
	private ReservationModel model;
	private SearchModel sModel;

	public ReservationController(SearchView searchView, ReservationView reserveView, ReservationModel rModel, SearchModel sModel) {
		this.sView = searchView;
		this.rView = reserveView;
		this.model = rModel;
		this.model.addListener(this);
		this.sModel = sModel;
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
				Flights f = new Flights();;
				f.addAll(sModel.getAvailableFlights());
				Flight flight = f.get(sView.getList().getSelectedIndex());				
				model.setFlightNumber(flight.getmNumber());
				
				if(rView.getRdbtnCoach().isSelected()){
					model.setSeatingType("Coach");
				}
				else{
					model.setSeatingType("Economy");
				}
				
				//TODO: Add instruction to reserve the flight			
				System.out.println(e.getActionCommand());
			}
		});
				
		rView.getBtnCancelReserve().setAction(new AbstractAction("Cancel Reservation") {
			@Override
			public void actionPerformed(ActionEvent e) {	
				rView.getWindow().setVisible(false);
			}
		});
		
		rView.getRdbtnCoach().setAction(new AbstractAction("Select Coach Type"){
			@Override
			public void actionPerformed(ActionEvent e) {
				rView.getRdbtnCoach().setSelected(true);
				rView.getRdbtnEconomy().setSelected(false);				
			}
		});
		
		rView.getRdbtnEconomy().setAction(new AbstractAction("Select Economy Type"){
			@Override
			public void actionPerformed(ActionEvent e) {
				rView.getRdbtnCoach().setSelected(false);
				rView.getRdbtnEconomy().setSelected(true);
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
