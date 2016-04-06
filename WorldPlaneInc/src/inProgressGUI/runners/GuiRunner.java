package inProgressGUI.runners;

import java.awt.EventQueue;

import inProgressGUI.controllers.SearchController;
import inProgressGUI.views.ReservationView;
import inProgressGUI.views.SearchView;

public class GuiRunner {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchController.getInstance();
					SearchView searchView = SearchView.getInstance();
					ReservationView reservationView = ReservationView.getInstance();
					reservationView.setVisible(false);
					searchView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
