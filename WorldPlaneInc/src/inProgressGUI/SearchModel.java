/**
 * File: SearchModel.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI;

import javax.swing.event.SwingPropertyChangeSupport;

import inProgressGUI.SearchController;

/**
 * The SearchModel class provides a means for maintaining
 * information on the state of the SearchView
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class SearchModel {

	/**
	 * The singleton instance of the SearchModel
	 */
	private static SearchModel instance = null;
	
	/**
	 * Means for signaling that a property has been changed
	 */
	private SwingPropertyChangeSupport propertyChangeSupport;
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static SearchModel getInstance() {
		if (instance == null) {
			instance = new SearchModel();
		}
		return instance;
	}
	
	
	/**
	 * Constructor for the instance
	 */
	private SearchModel() {
		propertyChangeSupport = new SwingPropertyChangeSupport(this);
	}
	
	
	/**
	 * This method registers the SearchController as
	 * a listener for changes in the SearchModel
	 */
	public void addListener() {
		propertyChangeSupport.addPropertyChangeListener(SearchController.getInstance());
	}
}
