package edu.wpi.cs509.team04;

import java.awt.EventQueue;

/**
 * Project EntryPoint
 * 
 * @author Daniel
 *
 */
public class Main {

	public static void main(String[] args) {
		
		ServerInterface resSys = new ServerInterface();
		String team = ConfigSingleton.getInstance().get("team");
		
	
		
		// Try to get a list of airports
		String xmlAirport = resSys.getAirports(team);
		System.out.println(xmlAirport);
		
		Airports ports = new Airports();
		ports.addAll(xmlAirport);
		
		Airport airport1 = ports.get(0);
		
		
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
		
		String xmlReservation = "<Flights>"
				+ "<Flight number=\"" + flightNumber + "\" seating=\"Coach\"/>"
				+ "</Flights>";
		
		
		// Try to lock the database, purchase ticket and unlock database
		resSys.lock(team);
		resSys.buyTickets(team, xmlReservation);
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
					SearchView view = new SearchView();
					ReservationView view2 = new ReservationView();
					SearchModel model = new SearchModel();
					new SearchController(view, view2, model);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
