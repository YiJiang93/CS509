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

public class SearchController implements PropertyChangeListener, ListSelectionListener {
	
	private SearchView view;
	private ReservationView view2;
	private SearchModel model;
	// insert object that finds lists
	
	/**
	 * Constructor for the GuiController class
	 */
	public SearchController(SearchView view, ReservationView view2, SearchModel model) {
		this.view = view;
		this.view2 = view2;
		this.model = model;
		this.model.addListener(this);
		view.getList().addListSelectionListener(this);
		setupViewEvents();
	}
	
	private void setupViewEvents() {
		view.getSearchButton().setAction(new AbstractAction("Search") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!view.getDepartureAirport().equals("")
						&& !view.getArrivalAirport().equals("")
						&& !(view.getDepartureDate() == null)) {	
					model.setDepartureAirport(view.getDepartureAirport().getText());
					model.setArrivalAirport(view.getArrivalAirport().getText());
					model.setDepartureDate(view.getDepartureDate());
					
					Collection<String> flights = new ArrayList<String>();
					for (int i = 0; i < 100; i++) {
						flights.add("Flight " + i);
					}
					updateFlightList(flights);
					
					// call method to search for matching flights
					// call updateList(list of strings of flight info);
				}
				else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		view.getPriceSortButton().setAction(new AbstractAction("Sort by Price") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getListModel().isEmpty()) {
					Toolkit.getDefaultToolkit().beep();
				}
				else {
					// call method to acquire last saved search of flights
					// call updateList(list of strings of flight info)
				}
			}
		});
		view.getReservationButton().setAction(new AbstractAction("Reserve Flight") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getListModel().isEmpty()) {
					Toolkit.getDefaultToolkit().beep();
				}
				else {
					// open up new GUI window for making a reservation
					// indicate state of user selections
					view2.getWindow().setVisible(true);
				}
			}
		});
	}
	
	public void updateFlightList(Collection<String> list) {
		view.getListModel().clear();
		for (String element : list) {
			view.getListModel().addElement(element);
		}
		if (!view.getListModel().isEmpty()) {
			view.getList().setSelectedIndex(0);
			model.setSelectedFlight(view.getListModel().getElementAt(0));
		}
	}
	
	/**
	 * Provides a means for responding to the
	 * event in which a new item has been selected
	 * in the list component of the GUI that is
	 * being monitored
	 * 
	 * @param e An event in which a new list item is selected
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int selectedIndex = view.getList().getSelectedIndex();
		if (selectedIndex >= 0) {
			String selectedFlight = view.getListModel().getElementAt(selectedIndex);
			model.setSelectedFlight(selectedFlight);
		}
	}

	/**
	 * Provides a means for responding to the
	 * event in which the property of an item in
	 * the GUI model has changed
	 * 
	 * @param e An event in which the GUI model has changed
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// actions to be performed when the model has
		// changed. Essentially this is an update of the view
	}
}