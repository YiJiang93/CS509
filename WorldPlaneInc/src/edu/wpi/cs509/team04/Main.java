package edu.wpi.cs509.team04;

import java.awt.EventQueue;
import java.util.Date;

import edu.wpi.cs509.team04.common.Airports;
import edu.wpi.cs509.team04.common.LocalTime;
import edu.wpi.cs509.team04.gui.ReservationController;
import edu.wpi.cs509.team04.gui.ReservationView;
import edu.wpi.cs509.team04.gui.SearchController;
import edu.wpi.cs509.team04.gui.SearchView;
import edu.wpi.cs509.team04.server.ServerInterface;

/**
 * Project EntryPoint
 * 
 * @author Daniel
 *
 */

public class Main {

	public static void main(String[] args) {
		
		ServerInterface resSys = ServerInterface.getInstance();
		String xmlAirport = resSys.getAirports();
			
		Airports ports = new Airports();
		ports.addAll(xmlAirport);
		
		for(int i =0; i < ports.size(); i++){

				LocalTime t = new LocalTime();
				System.out.println(t.Convert(ports.get(i), new Date()));
			}
		
		
		new Thread(new Runnable() {
			public void run() {
				LocalTime.init();
			}
		}).start();
				
		//ServerInterface resSys = ServerInterface.getInstance();
		//String team = ConfigSingleton.getInstance().get("team");
		
		//Uncomment this line for the Console Version of WorldPlaneInc.
		//ConsoleInput.main(resSys, team);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchController.getInstance();
					SearchView searchView = SearchView.getInstance();
					ReservationController.getInstance();
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
