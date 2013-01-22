package gui;

import java.util.List;

public interface KarteikastenDAO {
	/**
	 * L�dt einen Kartensatz aus einer Lektion
	 * 
	 * @param lektionsName
	 *            : Ein String, der den Lektionsnamen festlegt
	 * @return Gibt eine Liste mit Karteikarten zur�ck
	 * @throws Exception
	 */
	public List<KarteiKarte> kartenLaden(String lektionsName) throws Exception;

	/**
	 * Speichert und aktualisiert eine Karteikarte
	 * 
	 * @param index
	 *            : Der Index bzw die ID der Karte
	 * @param karte
	 *            : Das Kartenobjekt selbst
	 * @param lektionsNname
	 *            : Der dazugeh�rige Lektionsname
	 * @param neueKarte
	 *            : Legt fest ob eine Neue Karte erstellt wird oder eine alte
	 *            Karte aktualisert
	 * @param neueLektion
	 *            : Legt fest ob eine Neue Lektion erstellt wird
	 * @throws Exception
	 */
	public void karteSpeichern(int index, KarteiKarte karte, String lektionsNname,
			boolean neueKarte, boolean neueLektion) throws Exception;

	/**
	 * Legt im XML-Verfahren die n�chstgr��te ID der neuen Karte fest
	 * 
	 * @param lektionsName
	 *            : Die entsprechende Lektion
	 * @return Gibt einen Int zur�ck
	 * @throws Exception
	 */
	public int nextKartenID(String lektionsName) throws Exception;

	/**
	 * Die Liste mit allen vorhandenen Lektionen
	 * 
	 * @return Gibt eine Liste mit Strings zur�ck
	 * @throws Exception
	 */
	public List<String> getLektionen() throws Exception;

	/**
	 * L�scht eine ausgew�hlte Lektion
	 * 
	 * @param lektionsName
	 *            : Der Name der Lektion
	 * @throws Exception
	 */
	public void kartensatzLoeschen(String lektionsName) throws Exception;
}
