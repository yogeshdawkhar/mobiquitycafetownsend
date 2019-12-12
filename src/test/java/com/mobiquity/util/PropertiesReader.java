package com.mobiquity.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader {

	static Properties prop = new Properties();
	private static final Logger LOGGER = Logger.getLogger(PropertiesReader.class.getName());

	public void load(String propertyFilePath) {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(propertyFilePath);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, () -> "Configuration file is not found. terminating Process !!!" + e);
			System.exit(0);
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, () -> "Not able to load file:" + e);
		}
	}

	public String get(String key) {

		String propertyFileValue = null;
		if (!prop.isEmpty()) {
			propertyFileValue = prop.getProperty(key);
		} else {
			LOGGER.log(Level.SEVERE, () -> "Configuration file is empty. Processing is terminated");
			System.exit(0);
		}
		return propertyFileValue;
	}
}
