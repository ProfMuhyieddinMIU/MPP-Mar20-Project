package business;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationFactory {

	public static String getProperty(String key) throws IOException {
		InputStream input = new FileInputStream("src/config.properties");

		Properties prop = new Properties();

		// load a properties file
		prop.load(input);

		return prop.getProperty(key);

	}
}
