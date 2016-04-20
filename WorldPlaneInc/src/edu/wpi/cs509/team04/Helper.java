package edu.wpi.cs509.team04;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

public class Helper {
	
	/**
	 * Return Dictionary<String, Integer> pair with:
	 *    keys: "FirstClassSeats, CoachSeats"
	 *    values: Number of First Class Seats Available, Number of Coach Seats Available
	 * 
	 * Retrieve the list of airplanes from the server, find the airplane object for the plane on the
	 * 	given flight, and store maximum seats for the plane.  Calculate the available seats for both
	 * 	First Class and Coach seats and store in a Dictionary.  Return the Dictionary.
	 * 	
	 * 
	 * @param flight identifies the selected flight to match the airplane.
	 * @param resSys identifies the Server Interface.
	 * @param team identifies the team instance on the database to connect.
	 * @return Dictionary<String, Integer> with available seat information
	 */
	public static Dictionary<String, Integer> getAvailableSeats(Flight flight, ServerInterface resSys, String team) {

		//Get airplane data
		String xmlAirplanes = resSys.getAirplanes(team);
		
		// Create the aggregate flights
		Airplanes planes = new Airplanes();
		planes.addAll(xmlAirplanes);
		
		int maxFirstClass = 0;
		int bookedFirstClass = flight.getmSeatsFirstclass();
		int maxCoach = 0;
		int bookedCoach = flight.getmSeatsCoach();
		
		for(int i=0; i< planes.size(); i++){
			Airplane plane = planes.get(i);
			if(plane.model().equals(flight.getmAirplane())) {
				maxFirstClass = plane.firstclass();
				maxCoach = plane.coach();
				break;
			}
		}

		Dictionary<String, Integer> seats = new Hashtable<String, Integer>(); 

		seats.put("FirstClassSeats", maxFirstClass - bookedFirstClass);
		seats.put("CoachSeats", maxCoach - bookedCoach);

		return seats;
	}
	
	/**
	 * Return String in format HH:MM
	 * 
	 * Given a DepartTime or ArrivalTime from a Flight and an Integer of number of hours to adjust the time,
	 *  split the Strings and add the offset.  Return the String in format HH:MM.
	 * 
	 * @param time identifies the time from a flight object.
	 * @param offset identifies the number of hours to modify the time.
	 * @return String in format HH:MM
	 */
	private static Date adjustTime(String time, int offset){
		
		Dictionary<String, Integer> month = new Hashtable<String, Integer>();
		month.put("Jan", 1);
		month.put("Feb", 2);
		month.put("Mar", 3);
		month.put("Apr", 4);
		month.put("May", 5);
		month.put("Jun", 6);
		month.put("Jul", 7);
		month.put("Aug", 8);
		month.put("Sep", 9);
		month.put("Oct", 10);
		month.put("Nov", 11);
		month.put("Dec", 12);
		
		//Break String into Array
		String[] timeArray = time.split(" ");
				
		//Get just the hours and minutes
		String[] hm = timeArray[3].split(":");
				
	    Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.set(Integer.parseInt(timeArray[0]), month.get(timeArray[1]), Integer.parseInt(timeArray[2]), 
				Integer.parseInt(hm[0]), Integer.parseInt(hm[1]), 0);
	    cal.add(Calendar.HOUR_OF_DAY, offset); // adds one hour
	    
	    return cal.getTime(); // returns new date object, adjusting for offset		
	}
	
	/**
	 * Return List<Dictionary<String, Flight>> pair with:
	 *    keys: "First, Second, Third"
	 *    values: Flight Object for the leg of the trip, or a null Flight.
	 * 
	 * Retrieve the list of possible trips from departure airport to arrival airport with up to two stop-overs.
	 *  At least one hour after first flight lands before the next flight leaves, and no more than three hours.
	 *  If the flight does not have two or three legs, null objects are returned.
	 * 
	 * @param team identifies the team instance on the database to connect.
	 * @param departCode identifies the Departure Airport Code.
	 * @param arriveCode identifies the final Arrival Airport Code.
	 * @param departDate identifies the Departure Date as YYYY_MM_DD.
	 * @return List<Dictionary<String, Flight>> with list of flights.
	 */
	public static List<Dictionary<String, Flight>> getFlightList(String team, String departCode, String arriveCode, String departDate) throws ParseException {
		
		ServerInterface resSys = ServerInterface.getInstance();
		String xmlFlights = resSys.getFlights(team, departCode, departDate);
		Flights flights = new Flights();
		Flight nullFlight = new Flight("", "0", "", "", "", "", "", "0.0", 0, "0.0", 0);

		// Create the aggregate flights
		flights = new Flights();
		flights.addAll(xmlFlights);

		List<Dictionary<String, Flight>> flightArray = new ArrayList<Dictionary<String, Flight>>();

		DateFormat f = new SimpleDateFormat("hh:mm");
		for(int i=0; i<flights.size() - 1; i++) {
			System.out.println("FlightList 1, Flight " + i);
			Flight flight = flights.get(i);
			if(flight.getmCodeArrival().equals(arriveCode)) {
				Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>(); 
				flightStruct.put("First", flight);
				flightStruct.put("Second",  nullFlight);
				flightStruct.put("Third", nullFlight);
				flightArray.add(flightStruct);
			} else {
				String departCode2 = flight.getmCodeArrival();
				String arriveTime2 = flight.getmTimeArrival();
				Date departTime2min = adjustTime(arriveTime2, 1);
				Date departTime2max = adjustTime(arriveTime2, 3);
				String xmlFlights2 = resSys.getFlights(team, departCode2, departDate);
				Flights flights2 = new Flights();
				flights2 = new Flights();
				flights2.addAll(xmlFlights2);
				for(int j=0; j<flights2.size() - 1; j++) {
					System.out.println("FlightList 2, Flight " + j);
					Flight flight2 = flights2.get(j);
					Date departTime2 = adjustTime(flight2.getmTimeDepart(), 0);
					if(departTime2min.getTime() <= departTime2.getTime() && departTime2.getTime() <= departTime2max.getTime()){
						//if(flight2.getmArrivalCode().equals(departCode)) IGNORE
						if(flight2.getmCodeArrival().equals(arriveCode)) {
							Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>(); 
							flightStruct.put("First", flight);
							flightStruct.put("Second",  flight2);
							flightStruct.put("Third", nullFlight);
							flightArray.add(flightStruct);
						} else {
							if (j==11){
								j=11;
							}
							String departCode3 = flight2.getmCodeArrival();
							String arriveTime3 = flight2.getmTimeArrival();
							Date departTime3min = adjustTime(arriveTime3, 1);
							Date departTime3max = adjustTime(arriveTime3, 3);
							String xmlFlights3 = resSys.getFlights(team, departCode3, departDate);
							Flights flights3 = new Flights();
							flights3 = new Flights();
							flights3.addAll(xmlFlights3);
							for(int k=0; k < flights3.size() - 1; k++) {
								System.out.println("FlightList 3, Flight " + k);
								Flight flight3 = flights3.get(k);
								Date departTime3 = adjustTime(flight3.getmTimeDepart(), 0);
								if (k==10 && flight.getmCodeArrival().equals(arriveCode)){
									k=10;
								}
								if(departTime3min.getTime() <= departTime3.getTime() && departTime3.getTime() <= departTime3max.getTime()){
									if(flight3.getmCodeArrival().equals(arriveCode)) {
										Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>(); 
										flightStruct.put("First", flight);
										flightStruct.put("Second",  flight2);
										flightStruct.put("Third", flight3);
										flightArray.add(flightStruct);
									}
								}
							}
						}
					}
				}
			}
		}
		
		getSortedFlightList(flightArray, "priceF");
		return flightArray;
	}
	
	
	public static List<Dictionary<String, Flight>> getSortedFlightList(List<Dictionary<String, Flight>> flightList, String type) throws ParseException {
		
		List<Dictionary<String, Flight>> sortedFlightList = new ArrayList<Dictionary<String, Flight>>();
		int[] sortHelper  = new int[flightList.size()];  //Array of Integers that represent the Index of the Flight Dictionary in the flightList.
		int minFT = -1;
		BigDecimal minPC = new BigDecimal("0");
		BigDecimal minPF = new BigDecimal("0");
		
		//i is the order number
		//k is the index of the flight
		for (int i = 0; i < flightList.size(); i++){
			int ft = 0;
			BigDecimal pc = new BigDecimal("0");
			BigDecimal pf = new BigDecimal("0");
			for (int k = 0; k < flightList.size(); k++) {
				Dictionary<String, Flight> trip;
				trip = flightList.get(k);
				if(type == "time"){
					int newFT = Integer.parseInt(trip.get("First").getmFlightTime()) + 
							    Integer.parseInt(trip.get("Second").getmFlightTime()) +
							    Integer.parseInt(trip.get("Third").getmFlightTime());
					System.out.println(newFT);
					if (ft == 0 && newFT > minFT){
						ft = newFT;
						sortHelper[i] = k;
					} else if (newFT < ft && newFT > minFT) {
						ft = newFT;
						sortHelper[i] = k;						
					}
				} else if (type == "priceC") {
					BigDecimal newPC = new BigDecimal("0.0");
					BigDecimal firstPC = new BigDecimal(trip.get("First").getmPriceCoach().replaceAll("[^\\d.]+", ""));
					BigDecimal secondPC = new BigDecimal(trip.get("Second").getmPriceCoach().replaceAll("[^\\d.]+", ""));
					BigDecimal thirdPC = new BigDecimal(trip.get("Third").getmPriceCoach().replaceAll("[^\\d.]+", ""));
					newPC = newPC.add(firstPC);
					newPC = newPC.add(secondPC);
					newPC = newPC.add(thirdPC);
					System.out.println(NumberFormat.getCurrencyInstance().format(newPC));
					System.out.println(pc.compareTo(newPC));
					System.out.println(pc.compareTo(BigDecimal.ZERO));
					if (pc.compareTo(BigDecimal.ZERO) == 0 && newPC.compareTo(minPC) == 1){
						pc = newPC;
						sortHelper[i] = k;
					} else if (pc.compareTo(newPC) == 1 && newPC.compareTo(minPC) == 1) {
						pc = newPC;
						sortHelper[i] = k;						
					}
				} else if (type == "priceF") {
					BigDecimal newPF = new BigDecimal("0.0");
					BigDecimal firstPF = new BigDecimal(trip.get("First").getmPriceFirstclass().replaceAll("[^\\d.]+", ""));
					BigDecimal secondPF = new BigDecimal(trip.get("Second").getmPriceFirstclass().replaceAll("[^\\d.]+", ""));
					BigDecimal thirdPF = new BigDecimal(trip.get("Third").getmPriceFirstclass().replaceAll("[^\\d.]+", ""));
					newPF = newPF.add(firstPF);
					newPF = newPF.add(secondPF);
					newPF = newPF.add(thirdPF);
					if (pf.compareTo(BigDecimal.ZERO) == 0 && newPF.compareTo(minPF) == 1){
						pf = newPF;
						sortHelper[i] = k;
					} else if (pf.compareTo(newPF) == 1 && newPF.compareTo(minPF) == 1) {
						pf = newPF;
						sortHelper[i] = k;						
					}
				}
			}
			minFT = ft;
			minPC = pc;
			minPF = pf;
			sortedFlightList.add(flightList.get(sortHelper[i]));
		}
		//System.out.println(NumberFormat.getCurrencyInstance().format(test));
		
		return sortedFlightList;
	}
}
