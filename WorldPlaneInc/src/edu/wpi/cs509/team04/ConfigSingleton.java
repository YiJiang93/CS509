package edu.wpi.cs509.team04;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * Global Configuration Singleton * 
 *
 */

public class ConfigSingleton {
	private static ConfigSingleton instance = null;
	private Properties props = new Properties();
	
	/**
	 * Private default constructor which load the "config.properties" file into the memory
	 * and initialize props object. 
	 * 
	 */
	private ConfigSingleton(){
		InputStream iStream;
		try {
			iStream = new FileInputStream("config.properties");			
			props.load(iStream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Get a String config value by providing a key 
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key){
		return props.getProperty(key);
	}
	
	/**
	 * Return an instance of the singleton
	 * 
	 * @return
	 */
	public static ConfigSingleton getInstance(){
		if(instance == null){
			instance = new ConfigSingleton();
		}
		return instance;
	}
}
