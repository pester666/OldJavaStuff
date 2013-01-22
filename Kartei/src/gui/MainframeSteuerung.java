package gui;

import java.util.List;

/**
 * Gewährt Zugriff auf das Mainframe und dessen Methoden.
 * @author Pester
 */
public interface MainframeSteuerung {
	/**
	 * Das Spielpanel wird aktiviert und die zuvor angezeigte Karte wird wiederhergestellt
	 */
	public abstract void activateSpielPanel();
	/**
	 * Aktiviert das Karteneditorpanel und setzt sämtliche Bedingungen
	 */
	public abstract void activateKartenEditorPanel(KarteiKarte aktuelleKarte);
	/**
	 * Aktiviert oder Deaktiviert einen Reiter auf dem tabbedPane
	 * @param atIndex : Bestimmt den Index des Reiters, angefangen bei 0
	 * @param isEnabled : Bestimmt ob aktiviert(true) oder deaktiviert(false)
	 */
	public abstract void tabbedPaneAktivieren(int atIndex, boolean isEnabled);
	/**
	 * Schaut, ob eine Phase gewählt ist und setzt einen neuen Kartensatz sowie den Name des aktuelle Kastens. Außerdem trifft es alle Vorbereitungen zum Start der Abfrage.
	 */
	public abstract void ladenAusführen();
	/**
	 * Der Name der aktuellen Lektion
	 * @return Gibt einen String zurück
	 */
	public abstract String currentKartensatzName();
	/**
	 * Belegt den aktuellen Kartensatz mit einem neuen Namen
	 * @param kasten : Neuer Name des Kastens
	 */
	public abstract void setCurrentKartensatzName(String kasten);
	/**
	 * Aktiviert oder Deaktiviert den Bearbeiten-Menüpunkt
	 * @param enabled : Legt fest ob true oder false
	 */
	public abstract void setMenüPunktBearbeiten(boolean enabled);
	/**
	 * Wenn eine Lektion hinzugefügt oder gelöscht wird, wird die Liste aktualisiert
	 */
	public abstract void listeAktualisieren();
	/**
	 * Setzt die GUI auf alle Default-Werte zurück und wechselt auf das Spielpanel
	 */
	public abstract void resetKartensatz();
	/**
	 * Die Elemente der Lektionsliste
	 * @return Gibt einen Liste mit Strings zurück
	 */
	public abstract List<String> getListModelElements();
}
