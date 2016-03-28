/**
 * File: SearchController.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package inProgressGUI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The SearchController class provides a means for coordinating changes
 * to the SearchView and SearchModel classes when a change is made to any
 * of their attributes. This coordination with the SearchView is done in
 * particular by listening and responding to PropertyChangeEvents that are
 * thrown by the SearchModel. The same coordination with the SearchModel is
 * accomplished by defining the actions to take when user-driven events such
 * as a button click transpire.
 * 
 * @author Alexander W. Witt (awitt at wpi.edu)
 * @version March 24, 2016
 */
public class SearchController implements PropertyChangeListener, ListSelectionListener {

	
	/**
	 * The singleton instance of the SearchController
	 */
	private static SearchController instance = null;
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static SearchController getInstance() {
		if (instance == null) {
			instance = new SearchController();
		}
		return instance;
	}
	
	
	/**
	 * Constructor for the instance
	 */
	private SearchController() {
		
	}
	
	
	/**
	 * 
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	}

	
	/**
	 * 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
	}
}
