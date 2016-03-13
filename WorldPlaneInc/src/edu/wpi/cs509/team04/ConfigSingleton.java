package edu.wpi.cs509.team04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigSingleton {
	private static ConfigSingleton instance = null;
	private Properties props = new Properties();
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
	
	public String get(String key){
		return props.getProperty(key);
	}
	
	public static ConfigSingleton getInstance(){
		if(instance == null){
			instance = new ConfigSingleton();
		}
		return instance;
	}
}
