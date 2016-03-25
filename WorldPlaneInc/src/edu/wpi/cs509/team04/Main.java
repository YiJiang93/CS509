package edu.wpi.cs509.team04;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Project EntryPoint
 * 
 * @author Daniel
 *
 */

public class Main {

	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			public void run() {
				LocalTime.init();
			}
		}).start();
				
		SearchModel model = new SearchModel();
		ServerInterface resSys = new ServerInterface(model);
		String team = ConfigSingleton.getInstance().get("team");
		

		//Uncomment this line for the Console Version of WorldPlaneInc.
		//ConsoleInput.main(resSys, team);
		
		// Try to get a list of airports
		String xmlAirport = resSys.getAirports(team);
		System.out.println(xmlAirport);
		
		Airports ports = new Airports();
		ports.addAll(xmlAirport);
		
		//Debug
		Airport airport1 = ports.get(0);
		
		//Build array of airport codes
		ArrayList<String> codes = new ArrayList<String>();
		
		for(int i=0; i< ports.size(); i++){
			Airport airport = ports.get(i);
			codes.add(airport.code());
		}
		
		//System.out.println(codes);
		Collections.sort(codes);
		//System.out.println(codes);
		
		//Test Array
		for(String s : codes){
			if (s.equals("BOS")){
				System.out.println("Found");
				break;
			}	
		}
		//System.out.println("Report Error");
		
		String xmlAirplanes = resSys.getAirplanes(team);
		System.out.println(xmlAirplanes);
		
		Airplanes planes = new Airplanes();
		planes.addAll(xmlAirplanes);
		
		//Debug
		//Airplane airplane1 = planes.get(0);
		
		System.out.println("Lat: " +airport1.latitude() + " Long:" + airport1.longitude());

		// Get a sample list of flights from server
		String xmlFlights = resSys.getFlights(team, "BOS", "2016_05_10");
		System.out.println(xmlFlights);
		
		// Create the aggregate flights
		Flights flights = new Flights();
		flights.addAll(xmlFlights);
		
		//try to reserve a coach seat on one of the flights
		Flight flight = flights.get(0);
		String flightNumber = flight.getmNumber();
		int seatsReservedStart = flight.getmSeatsCoach();

//		
//		String xmlReservation = "<Flights>"
//				+ "<Flight number=\"" + flightNumber + "\" seating=\"Coach\"/>"
//				+ "</Flights>";
//		

		// Try to lock the database, purchase ticket and unlock database
		resSys.lock(team);
		resSys.buyTickets(team, flightNumber, "Coach");
		resSys.unlock(team);
		
		// Verify the operation worked
		xmlFlights = resSys.getFlights(team, "BOS", "2016_05_10");
		System.out.println(xmlFlights);
		flights.clear();
		flights.addAll(xmlFlights);
		
		// Find the flight number just updated
		int seatsReservedEnd = seatsReservedStart;
		for (Flight f : flights) {
			String tmpFlightNumber = f.getmNumber();
			if (tmpFlightNumber.equals(flightNumber)) {
				seatsReservedEnd = f.getmSeatsCoach();
				break;
			}
		}
		if (seatsReservedEnd == (seatsReservedStart + 1)) {
			System.out.println("Seat Reserved Successfully");
		} else {
			System.out.println("Reservation Failed");
		}		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchView searchView = new SearchView();
					ReservationView reserveView = new ReservationView();
					
					SearchModel sModel = new SearchModel();
					new SearchController(searchView, reserveView, sModel);					
					
					ReservationModel rModel = new ReservationModel();
					new ReservationController(searchView, reserveView, rModel, sModel);					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
