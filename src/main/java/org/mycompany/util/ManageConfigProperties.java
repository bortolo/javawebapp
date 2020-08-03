package org.mycompany.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @author Crunchify.com
 *
 */

public class ManageConfigProperties {
	String result = "";
	InputStream inputStream;

  public ManageConfigProperties(){}

	public String getConfigValues(String configFile, String parameter) throws IOException {

		try {
			Properties prop = new Properties();
//			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(configFile);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + configFile + "' not found in the classpath");
			}

			Date time = new Date(System.currentTimeMillis());

			// get the property value and print it out
      result = prop.getProperty(parameter);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}
