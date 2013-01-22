package gui;

import java.util.List;

/**
 * Gew�hrt Zugriff auf das Mainframe und dessen Methoden.
 * @author Pester
 */
public interface MainframeSteuerung {
	/**
	 * Das Spielpanel wird aktiviert und die zuvor angezeigte Karte wird wiederhergestellt
	 */
	public abstract void activateSpielPanel();
	/**
	 * Aktiviert das Karteneditorpanel und setzt s�mtliche Bedingungen
	 */
	public abstract void activateKartenEditorPanel(KarteiKarte aktuelleKarte);
	/**
	 * Aktiviert oder Deaktiviert einen Reiter auf dem tabbedPane
	 * @param atIndex : Bestimmt den Index des Reiters, angefangen bei 0
	 * @param isEnabled : Bestimmt ob aktiviert(true) oder deaktiviert(false)
	 */
	public abstract void tabbedPaneAktivieren(int atIndex, boolean isEnabled);
	/**
	 * Schaut, ob eine Phase gew�hlt ist und setzt einen neuen Kartensatz sowie den Name des aktuelle Kastens. Au�erdem trifft es alle Vorbereitungen zum Start der Abfrage.
	 */
	public abstract void ladenAusf�hren();
	/**
	 * Der Name der aktuellen Lektion
	 * @return Gibt einen String zur�ck
	 */
	public abstract String currentKartensatzName();
	/**
	 * Belegt den aktuellen Kartensatz mit einem neuen Namen
	 * @param kasten : Neuer Name des Kastens
	 */
	public abstract void setCurrentKartensatzName(String kasten);
	/**
	 * Aktiviert oder Deaktiviert den Bearbeiten-Men�punkt
	 * @param enabled : Legt fest ob true oder false
	 */
	public abstract void setMen�PunktBearbeiten(boolean enabled);
	/**
	 * Wenn eine Lektion hinzugef�gt oder gel�scht wird, wird die Liste aktualisiert
	 */
	public abstract void listeAktualisieren();
	/**
	 * Setzt die GUI auf alle Default-Werte zur�ck und wechselt auf das Spielpanel
	 */
	public abstract void resetKartensatz();
	/**
	 * Die Elemente der Lektionsliste
	 * @return Gibt einen Liste mit Strings zur�ck
	 */
	public abstract List<String> getListModelElements();
}
