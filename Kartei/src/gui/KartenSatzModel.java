package gui;

import java.util.List;

/**
 * Gewährt Zugriff auf die Kartenliste
 * @author Pester
 *
 */
public interface KartenSatzModel {

	/**
	 * Die Kartenliste der aktuellen Lektion
	 * @return Gibt eine Liste mit Karteikarten-Objekten zurück
	 */
	public abstract List<KarteiKarte> getKartenListe();

	/**
	 * Die Nummer der Phase in welcher sich die Karte befindet
	 * @param phase : Die Phasennummer
	 * @return Gibt einen Int zurück
	 */
	public int phasenNummer(int phase);
}
