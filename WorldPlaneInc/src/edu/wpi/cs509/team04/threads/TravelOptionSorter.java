package edu.wpi.cs509.team04.threads;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import edu.wpi.cs509.team04.common.Flight;
import edu.wpi.cs509.team04.common.TravelOption;
import edu.wpi.cs509.team04.enums.SeatingType;
import edu.wpi.cs509.team04.gui.SearchModel;

public class TravelOptionSorter implements Runnable {

	public TravelOptionSorter() {
		
	}
	
	@Override
	public void run() {
		SearchModel searchModel = SearchModel.getInstance();
		List<Dictionary<String, Flight>> toflightList = new ArrayList<Dictionary<String, Flight>>();
		List<Dictionary<String, Flight>> fromflightList = new ArrayList<Dictionary<String, Flight>>();
		Collection<TravelOption> toOptions = searchModel.getToDestinationTravelOptions();
		Collection<TravelOption> fromOptions = searchModel.getFromDestinationTravelOptions();
		Collection<TravelOption> empty = new ArrayList<TravelOption>();
		for (TravelOption toOption : toOptions) {
			Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>();
			flightStruct.put("First", toOption.getInitialFlight());
			flightStruct.put("Second", toOption.getFirstLayover());
			flightStruct.put("Third", toOption.getSecondLayover());
			toflightList.add(flightStruct);
		}
		for (TravelOption fromOption : fromOptions) {
			Dictionary<String, Flight> flightStruct = new Hashtable<String, Flight>();
			flightStruct.put("First", fromOption.getInitialFlight());
			flightStruct.put("Second", fromOption.getFirstLayover());
			flightStruct.put("Third", fromOption.getSecondLayover());
			fromflightList.add(flightStruct);
		}
		try {
			Collection<TravelOption> sortedToDest = new ArrayList<TravelOption>();
			Collection<TravelOption> sortedFromDest = new ArrayList<TravelOption>();
			if (searchModel.getSeatingType() == SeatingType.COACH) {
				List<Dictionary<String, Flight>> sortedToFlightList = Helper.getSortedFlightList(toflightList, "priceC");
				List<Dictionary<String, Flight>> sortedFromFlightList = Helper.getSortedFlightList(fromflightList, "priceC");
				for (Dictionary<String, Flight> entry : sortedToFlightList) {
					sortedToDest.add(new TravelOption(entry.get("First"), entry.get("Second"), entry.get("Third")));
				}
				for (Dictionary<String, Flight> entry : sortedFromFlightList) {
					sortedFromDest.add(new TravelOption(entry.get("First"), entry.get("Second"), entry.get("Third")));
				}
				searchModel.setToDestinationTravelOptions(empty);
				searchModel.setFromDestinationTravelOptions(empty);
				searchModel.setToDestinationTravelOptions(sortedToDest);
				searchModel.setFromDestinationTravelOptions(sortedFromDest);
			}
			else {
				List<Dictionary<String, Flight>> sortedToFlightList = Helper.getSortedFlightList(toflightList, "priceF");
				List<Dictionary<String, Flight>> sortedFromFlightList = Helper.getSortedFlightList(fromflightList, "priceF");
				for (Dictionary<String, Flight> entry : sortedToFlightList) {
					sortedToDest.add(new TravelOption(entry.get("First"), entry.get("Second"), entry.get("Third")));
				}
				for (Dictionary<String, Flight> entry : sortedFromFlightList) {
					sortedFromDest.add(new TravelOption(entry.get("First"), entry.get("Second"), entry.get("Third")));
				}
				searchModel.setToDestinationTravelOptions(empty);
				searchModel.setFromDestinationTravelOptions(empty);
				searchModel.setToDestinationTravelOptions(sortedToDest);
				searchModel.setFromDestinationTravelOptions(sortedFromDest);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void priceSort() {
		TravelOptionSorter sorter = new TravelOptionSorter();
		Thread sorterThread = new Thread(null, sorter, "travel option sorter thread");
		sorterThread.start();
	}
}
