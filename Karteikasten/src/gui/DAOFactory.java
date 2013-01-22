package gui;

import org.apache.log4j.Logger;

/**
 * Die Factory legt das Implementierungssystem anhand der Konfiguration fest
 * 
 * @author Pester
 * 
 */
public class DAOFactory {

	static private final Logger LOG = Logger.getLogger(DAOFactory.class);

	static {
		initDBDriver();
	}

	private boolean fileImpl;

	public DAOFactory() {
		// hier könnnte stehen, welche implementierung in
		// getKarteiastenDAO zurückgegeben wird.
		if (Config.getProperty("persistor", "xml").equals("xml"))
			fileImpl = true;
		else
			fileImpl = false;
	}

	public KarteikastenDAO getKarteikastenDAO() {
		if (fileImpl)
			return new KarteikastenDAOXStreamImpl();
		else
			return new KarteiKastenDAODBImpl();
	}

	/**
	 * Instantiate DB-Driver
	 */
	private static void initDBDriver() {
		try {
			Class.forName(Config.getProperty("db.driver", "sepp"));

		} catch (ClassNotFoundException e) {
			LOG.error("Datenbanktreiber oracle.jdbc.driver.OracleDriver konnte nicht geladen werden! Produktexport abgerochen");
		}
	}
}
