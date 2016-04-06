package edu.wpi.cs509.team04;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

public class ConsoleInput {
	
	public static void main(ServerInterface resSys, String team){
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("World Plane Inc.");
		System.out.println("----------------------");
		System.out.println("Command Line Version");
		System.out.println(" Enter 'EXIT' at any prompt to quit.");
		System.out.println();
		
		// Try to get a list of airports
		String xmlAirport = resSys.getAirports(team);
		
		// Parse XML into Airports Structure
		Airports ports = new Airports();
		ports.addAll(xmlAirport);
		
		//Build array of airport codes
		ArrayList<String> codes = new ArrayList<String>();
		
		for(int i=0; i< ports.size(); i++){
			Airport airport = ports.get(i);
			codes.add(airport.code());
		}
		
		boolean trip = false;
		Flights flights = new Flights();
		ArrayList<String> foundFN = new ArrayList<String>();
		Flights foundFlights = new Flights();
		Flight selectedFlight = null;
		
	/* getFlightList() Debugging/Testing	
	 * String departCode = "BOS";
	 * String arriveCode = "ATL";
	 * String departDate = "2016_05_10";
	 * 
		List<Dictionary<String, Flight>> test = null;
		try {
			test = Helper.getFlightList(resSys, team, departCode, arriveCode, departDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dictionary<String, Flight> temp = test.get(0);
		
		System.out.println("========================================================");
		System.out.println("Flight Number: " + temp.get("First").getmNumber());
		System.out.println("Departure Location: " + temp.get("First").getmCodeDepart());
		System.out.println("Departure Time: " + temp.get("First").getmTimeDepart());
		System.out.println("Arrival Location: " + temp.get("First").getmCodeArrival());
		System.out.println("Arrival Time: " + temp.get("First").getmTimeArrival());
		System.out.println("Airplane: " + temp.get("First").getmAirplane());
		System.out.println("Total Travel Time: " + temp.get("First").getmFlightTime());
		System.out.println("First Class Seats: " + temp.get("First").getmSeatsFirstclass());
		System.out.println("First Class Price: " + temp.get("First").getmPriceFirstclass());
		System.out.println("Coach Seats: " + temp.get("First").getmSeatsCoach());
		System.out.println("Coach Price: " + temp.get("First").getmPriceCoach());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Flight Number: " + temp.get("Second").getmNumber());
		System.out.println("Departure Location: " + temp.get("Second").getmCodeDepart());
		System.out.println("Departure Time: " + temp.get("Second").getmTimeDepart());
		System.out.println("Arrival Location: " + temp.get("Second").getmCodeArrival());
		System.out.println("Arrival Time: " + temp.get("Second").getmTimeArrival());
		System.out.println("Airplane: " + temp.get("Second").getmAirplane());
		System.out.println("Total Travel Time: " + temp.get("Second").getmFlightTime());
		System.out.println("First Class Seats: " + temp.get("Second").getmSeatsFirstclass());
		System.out.println("First Class Price: " + temp.get("Second").getmPriceFirstclass());
		System.out.println("Coach Seats: " + temp.get("Second").getmSeatsCoach());
		System.out.println("Coach Price: " + temp.get("Second").getmPriceCoach());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Flight Number: " + temp.get("Third").getmNumber());
		System.out.println("Departure Location: " + temp.get("Third").getmCodeDepart());
		System.out.println("Departure Time: " + temp.get("Third").getmTimeDepart());
		System.out.println("Arrival Location: " + temp.get("Third").getmCodeArrival());
		System.out.println("Arrival Time: " + temp.get("Third").getmTimeArrival());
		System.out.println("Airplane: " + temp.get("Third").getmAirplane());
		System.out.println("Total Travel Time: " + temp.get("Third").getmFlightTime());
		System.out.println("First Class Seats: " + temp.get("Third").getmSeatsFirstclass());
		System.out.println("First Class Price: " + temp.get("Third").getmPriceFirstclass());
		System.out.println("Coach Seats: " + temp.get("Third").getmSeatsCoach());
		System.out.println("Coach Price: " + temp.get("Third").getmPriceCoach());
*/
		
		while(!trip){
			boolean dCode = false;
			boolean aCode = false;
			boolean vDate = false;
			
			String departCode = "";
			String arrivalCode = "";
			String departDate = "";
			
			while(!dCode){
				System.out.print("Enter Departure Airport (Three Letter Code): ");
				
				departCode = scanner.next();
				
				if (departCode.equals("EXIT")) System.exit(0);
				
				//Search array for matching airport code
				for(String s : codes){
					if (s.equals(departCode)){
						dCode = true;
						break;
					}	
				}
				
				if (!dCode)	System.out.println(departCode + " is not a valid Airport Code.");
			}
			
			while(!aCode){
				System.out.print("Enter Arrival Airport (Three Letter Code): ");
				
				arrivalCode = scanner.next();
				
				if (arrivalCode.equals("EXIT")) System.exit(0);
				
				//Search array for matching airport code
				for(String s : codes){
					if (s.equals(arrivalCode)){
						aCode = true;
						break;
					}	
				}
				
				if (!aCode)	System.out.println(arrivalCode + " is not a valid Airport Code.");
				if (arrivalCode.equals(departCode)){
					System.out.println("Arrival Airport cannot be the same as the Departure Airport.");
					aCode = false;
				}
			}
			
			while(!vDate){
				System.out.print("Enter Departure Date (YYYY_MM_DD): ");
				
				departDate = scanner.next();
				
				if (departDate.equals("EXIT")) System.exit(0);
				
				//Date Validation checking
				vDate = true;
				//TODO
				
				if (!vDate) System.out.println(departDate + " is outside the bounds of the current flight information.");
			}
		
			System.out.println("Retreiving flight information...");
			
			String xmlFlights = resSys.getFlights(team, departCode, departDate);
			
			// Create the aggregate flights
			flights = new Flights();
			flights.addAll(xmlFlights);
			
			for(int i=0; i< flights.size(); i++){
				Flight flight = flights.get(i);
				if(flight.getmCodeArrival().equals(arrivalCode)) {
					trip = true; //Found a flight from the departure airport to arrival airport
					foundFN.add(flight.getmNumber());
					foundFlights.add(flight);
					System.out.println("========================================================");
					System.out.println("Flight Number: " + flight.getmNumber());
					System.out.println("Departure Location: " + flight.getmCodeDepart());
					System.out.println("Departure Time: " + flight.getmTimeDepart());
					System.out.println("Arrival Location: " + flight.getmCodeArrival());
					System.out.println("Arrival Time: " + flight.getmTimeArrival());
					System.out.println("Airplane: " + flight.getmAirplane());
					System.out.println("Total Travel Time: " + flight.getmFlightTime());
					System.out.println("First Class Seats: " + flight.getmSeatsFirstclass());
					System.out.println("First Class Price: " + flight.getmPriceFirstclass());
					System.out.println("Coach Seats: " + flight.getmSeatsCoach());
					System.out.println("Coach Price: " + flight.getmPriceCoach());
				}
				
			}
			
			if (!trip) System.out.println("We've sorry, there is no direct flight from " + departCode + " to " + arrivalCode);
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		
		boolean seat = false; //Valid seat type not chosen
		boolean fn = false; //Flight Number of search results not chosen
		String flightNumber = "";
		String seatType = "";
		
		while(!fn){
			System.out.print("Please enter the Flight Number for the trip you wish to purchase: ");
			
			flightNumber = scanner.next();
			
			if (flightNumber.equals("EXIT")) System.exit(0);
			
			for(int i=0; i< foundFlights.size(); i++){
				selectedFlight = foundFlights.get(i);
				if(selectedFlight.getmNumber().equals(flightNumber)) {
					fn = true;
					break;
				}
			}
			/*
			for(String s : foundFN){
				if (s.equals(flightNumber)){
					fn = true;
					break;	
				}
			}
			*/
			if(!fn) System.out.println(flightNumber + " was not in the search results.");
		}
		
		Dictionary<String, Integer> seats = Helper.getAvailableSeats(selectedFlight, resSys, team);
		/*
		//Get airplane data
		String xmlAirplanes = resSys.getAirplanes(team);
		
		// Create the aggregate flights
		Airplanes planes = new Airplanes();
		planes.addAll(xmlAirplanes);
		
		int maxFirstClass = 0;
		int bookedFirstClass = selectedFlight.getmSeatsFirstclass();
		int maxCoach = 0;
		int bookedCoach = selectedFlight.getmSeatsCoach();
		
		for(int i=0; i< planes.size(); i++){
			Airplane plane = planes.get(i);
			if(plane.model().equals(selectedFlight.getmAirplane())) {
				maxFirstClass = plane.firstclass();
				maxCoach = plane.coach();
				break;
			}
		}
		*/
		//Get available seats
		
		
		while(!seat){
			//System.out.println("First Class (" + bookedFirstClass + "/" + maxFirstClass + ")  Coach (" + bookedCoach + "/" + maxCoach+ ")");
			System.out.println("First Class Seats Available: " + seats.get("FirstClassSeats") + ", Coach Seats Available: " + seats.get("CoachSeats"));
			System.out.print("First Class Seat or Coach Seat? (FC|C):");
			
			seatType = scanner.next();
			
			if (seatType.equals("EXIT")) System.exit(0);

/*			if (seatType.equals("FC") & maxFirstClass - bookedFirstClass > 0){
				seatType = "FirstClass";
				seat = true;
			}
			if (seatType.equals("C") & maxCoach - bookedCoach > 0){
				seatType = "Coach";
				seat = true;
			}

			if(!seat & maxFirstClass - bookedFirstClass <= 0){
				System.out.println("No more First Class Seats available.");
			} else if(!seat & maxCoach - bookedCoach <= 0) { 
				System.out.println("No more First Class Seats available.");
			} else if(!seat) {
				System.out.println("Invalid entry for seating.");
			}
			*/
			
		}
		
		System.out.println("Confirming Flight");
		
		resSys.lock(team);
		resSys.buyTickets(team, flightNumber, seatType);
		resSys.unlock(team);

		scanner.close();
		System.exit(0);
	}
}