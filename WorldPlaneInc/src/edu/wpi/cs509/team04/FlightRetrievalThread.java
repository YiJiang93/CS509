package edu.wpi.cs509.team04;

public class FlightRetrievalThread implements Runnable {

	private String team;
	private String airportCode;
	private String day;
	private SearchModel model;
	
	public FlightRetrievalThread(SearchModel model, String team, String airportCode, String day) {
		this.team = team;
		this.airportCode = airportCode;
		this.day = day;
		this.model = model;
	}
	
	public void run() {
		ServerInterface serverInterface = new ServerInterface(model);
		serverInterface.getFlights(team, airportCode, day);
		System.out.println("Inside Flight Retrieval Thread");
	}
	
	public void getAvailableFlights() {
		(new Thread(new FlightRetrievalThread(model, team, airportCode, day))).start();
	}
}
