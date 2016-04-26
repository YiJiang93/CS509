package edu.wpi.cs509.team04.threads;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import edu.wpi.cs509.team04.common.Airplane;
import edu.wpi.cs509.team04.common.Airplanes;
import edu.wpi.cs509.team04.common.Flight;
import edu.wpi.cs509.team04.common.Flights;
import edu.wpi.cs509.team04.server.ServerInterface;

public class Helper {
	
	static String departDate;
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
	 * @return Dictionary<String, Integer> with available seat information
	 */
	public static Dictionary<String, Integer> getAvailableSeats(Flight flight, ServerInterface resSys) {

		//Get airplane data
		String xmlAirplanes = resSys.getAirplanes();
		
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
	 * Return Calendar Object with time adjusted
	 * 
	 * Given a DepartTime or ArrivalTime from a Flight and an Integer of number of hours to adjust the time,
	 *  split the Strings, create a Calendar object and add the offset.  Return the Calendar Object with time adjustment.
	 * 
	 * @param time identifies the time from a flight object.
	 * @param offset identifies the number of hours to modify the time.
	 * @return Calendar Object
	 */
	private static Calendar adjustTime(String time, int offset){
		
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
	    
	    return cal; // returns new Calendar object, adjusting for offset		
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
	public static List<Dictionary<String, Flight>> getFlightList(String departCode, String arriveCode, String departureDate) throws ParseException {
		
		ServerInterface resSys = ServerInterface.getInstance();
		departDate = departureDate;
		String xmlFlights = resSys.getFlights(departCode, departDate);
		Flights flights = new Flights();
		Flight nullFlight = new Flight("", "0", "", "", "", "", "", "0.0", 0, "0.0", 0);

		// Create the aggregate flights
		flights = new Flights();
		flights.addAll(xmlFlights);

		List<Dictionary<String, Flight>> flightArray = new ArrayList<Dictionary<String, Flight>>();

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
				Calendar departTime2min = adjustTime(arriveTime2, 1);
				Calendar departTime2max = adjustTime(arriveTime2, 3);
				Flights flights2 = new Flights();
				flights2 = new Flights();
				if (departTime2min.get(Calendar.DAY_OF_YEAR) == departTime2max.get(Calendar.DAY_OF_YEAR)){
					String departDate2 = String.format("%d_%02d_%02d", departTime2min.get(Calendar.YEAR), departTime2min.get(Calendar.MONTH), departTime2min.get(Calendar.DAY_OF_MONTH));
					String xmlFlights2 = resSys.getFlights(departCode2, departDate2);
					flights2.addAll(xmlFlights2);
				} else {
					String departDate2min = String.format("%d_%02d_%02d", departTime2min.get(Calendar.YEAR), departTime2min.get(Calendar.MONTH), departTime2min.get(Calendar.DAY_OF_MONTH));
					String departDate2max = String.format("%d_%02d_%02d", departTime2max.get(Calendar.YEAR), departTime2max.get(Calendar.MONTH), departTime2max.get(Calendar.DAY_OF_MONTH));
					String xmlFlights2min = resSys.getFlights(departCode2, departDate2min);
					String xmlFlights2max = resSys.getFlights(departCode2, departDate2max);
					flights2.addAll(xmlFlights2min);
					flights2.addAll(xmlFlights2max);
				}
				for(int j=0; j<flights2.size() - 1; j++) {
					System.out.println("FlightList 2, Flight " + j);
					Flight flight2 = flights2.get(j);
					Calendar departTime2 = adjustTime(flight2.getmTimeDepart(), 0);
					if(departTime2min.before(departTime2) && departTime2max.after(departTime2)){	
						//if(flight2.getmArrivalCode().equals(departCode)) IGNORE
						if(flight2.getmCodeArrival().equals(arriveCode)) {
							Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>(); 
							flightStruct.put("First", flight);
							flightStruct.put("Second",  flight2);
							flightStruct.put("Third", nullFlight);
							flightArray.add(flightStruct);
						} else {
							String departCode3 = flight2.getmCodeArrival();
							String arriveTime3 = flight2.getmTimeArrival();
							Calendar departTime3min = adjustTime(arriveTime3, 1);
							Calendar departTime3max = adjustTime(arriveTime3, 3);
							Flights flights3 = new Flights();
							flights3 = new Flights();
							if (departTime3min.get(Calendar.DAY_OF_YEAR) == departTime3max.get(Calendar.DAY_OF_YEAR)){
								String departDate3 = String.format("%d_%02d_%02d", departTime3min.get(Calendar.YEAR), departTime3min.get(Calendar.MONTH), departTime3min.get(Calendar.DAY_OF_MONTH));
								String xmlFlights3 = resSys.getFlights(departCode3, departDate3);
								flights3.addAll(xmlFlights3);
							} else {
								String departDate3min = String.format("%d_%02d_%02d", departTime3min.get(Calendar.YEAR), departTime3min.get(Calendar.MONTH), departTime3min.get(Calendar.DAY_OF_MONTH));
								String departDate3max = String.format("%d_%02d_%02d", departTime3max.get(Calendar.YEAR), departTime3max.get(Calendar.MONTH), departTime3max.get(Calendar.DAY_OF_MONTH));
								String xmlFlights3min = resSys.getFlights(departCode3, departDate3min);
								String xmlFlights3max = resSys.getFlights(departCode3, departDate3max);
								flights3.addAll(xmlFlights3min);
								flights3.addAll(xmlFlights3max);
							}
							for(int k=0; k < flights3.size() - 1; k++) {
								System.out.println("FlightList 3, Flight " + k);
								Flight flight3 = flights3.get(k);
								Calendar departTime3 = adjustTime(flight3.getmTimeDepart(), 0);
								if (k==10 && flight.getmCodeArrival().equals(arriveCode)){
									k=10;
								}
								if(departTime3min.before(departTime3) && departTime3max.after(departTime3)){
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
