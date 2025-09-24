package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static Properties prop = new Properties();
	private static String path ="config/config.properties";
	private static String env;
	private ConfigManager() {
		//private constructor-->we cannot create any  object of ConfigManager class outside this class
	}
	//Static block it will be executed during class loading time
	static {//Operation of loading properties file in memory
		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		System.out.println("Running tests in Env "+ env);
		switch(env) {
		case "dev" -> path = "config/config.dev.properties";
		case "qa" -> path = "config/config.qa.properties";
		case "uat" -> path = "config/config.uat.properties";
		default -> path = "config/config.qa.properties";
		}
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path); //this line helps us to read the file in our src/test/resources folder
		if(input==null)
			throw new RuntimeException("Cannot find the file at path "+path);
		try {
			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getProperty(String Key) throws IOException {
		return prop.getProperty(Key);
	}

}
