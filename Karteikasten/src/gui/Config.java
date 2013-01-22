package gui;

import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Config {

	static final Properties props;
	static {
		try {
			InputStream is = Config.class.getClassLoader().getResourceAsStream(
					"application.properties");
			props = new Properties();
			props.load(is);
			int option = 0;

			Object[] configOptionen = { "XML-Dateien", "Datenbank" };
			String s = (String) JOptionPane.showInputDialog(null,
					"Wählen Sie:\n" + "Optionen",
					"Datenstruktur der Lektionen", JOptionPane.PLAIN_MESSAGE,
					null, configOptionen, "ham");

			if ((s != null) && (s.length() > 0)) {
				option = s.length();

				switch (option) {
				case 9:
					props.setProperty("persistor", "db");
					break;
				case 11:
					props.setProperty("persistor", "xml");
					break;
				default:
					break;
				}
			} else
				System.exit(1);
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

//	/**
//	 * Gibt die Zeitspanne zurück welche benötigt wird um eine Karte erneut
//	 * abzufragen
//	 * 
//	 * @param phase
//	 *            : Gibt die jeweilige Phase an mit welcher der Wert berechnet
//	 *            wird
//	 * @return Gibt die Zeit der Phase zurück
//	 */
//	static public int getPhaseXMinAge(int phase) {
//		String key = "phase." + phase + ".mindestalter";
//		String value = (String) props.get(key);
//		if (value == null)
//			return (phase * 100);
//		int iValue;
//		try {
//			iValue = Integer.parseInt(value);
//		} catch (NumberFormatException e) {
//			iValue = phase * 100;
//		}
//		return iValue;
//	}
}
