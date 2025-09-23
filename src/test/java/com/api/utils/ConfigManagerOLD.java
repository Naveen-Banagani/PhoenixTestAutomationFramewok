package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
	private static Properties prop = new Properties();
	private ConfigManagerOLD() {
		//private constructor-->we cannot create any class object of ConfigManager class
	}
	//Static block it will be executed during class loading time
	static {//Operation of loading properties file in memory
		File configFile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		try {
			FileReader fileReader = new FileReader(configFile);
			prop.load(fileReader);
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
		// WAP to read properties file
		//Special Class: Properties

		//Load the properties file using load()

		return prop.getProperty(Key);
	}

}
