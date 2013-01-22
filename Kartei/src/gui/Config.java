package gui;

import java.io.InputStream;
import java.util.Properties;

public class Config {

	static final Properties props;
	static {
		try {
			InputStream is = Config.class.getClassLoader().getResourceAsStream(
					"application.properties");
			props = new Properties();
			props.load(is);

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	/**
	 * Gibt die jeweilige Property zurück
	 * 
	 * @param propertyname
	 *            : Reicht einen Namen ein welcher Abgefragt wird
	 * @param defaultValue
	 *            : Gibt die Größe an, welche bei Null eingetragen wird
	 * @return Gibt den Wert des Propertynamen zurück
	 */
	static public String getProperty(String propertyname, String defaultValue) {
		if (defaultValue == null)
			throw new IllegalArgumentException("Default darf nicht null sein");
		String value = (String) props.get(propertyname);
		if (value == null)
			return defaultValue;
		return value;
	}
}
