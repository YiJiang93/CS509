/**
 * File: ConfigSingleton.java
 * 
 * The Java source code contained within this file was produced
 * by the software development team, "Team04", as a component of
 * a software-based flight reservation system produced for
 * World Plane Inc. (WPI)
 */

package edu.wpi.cs509.team04.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The ConfigSingleton class is used for acquiring
 * configuration values stored in a configuration file
 * 
 * @author Daniel (amoghimi @ wpi.edu)
 * @version April 3, 2016
 */
public class ConfigSingleton {

	/**
	 * The singleton instance of the ConfigSingleton
	 */
	private static ConfigSingleton instance = null;
	
	/**
	 * Persistent set of properties that can be loaded from a stream
	 */
	private Properties properties = new Properties();
	
	
	/**
	 * Constructor for the instance
	 */
	private ConfigSingleton() {
		InputStream iStream;
		try {
			iStream = new FileInputStream("config.properties");
			properties.load(iStream);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method provides a means for acquiring a String-type
	 * configuration value when providing a String-type key as input
	 * 
	 * @param key The key for a configuration value
	 * @return A configuration value corresponding to the key
	 */
	public String get(String key) {
		return properties.getProperty(key);
	}
	
	
	/**
	 * This method acquires the singleton instance
	 * @return The singleton instance
	 */
	public static ConfigSingleton getInstance() {
		if (instance == null) {
			instance = new ConfigSingleton();
		}
		return instance;
	}
}
