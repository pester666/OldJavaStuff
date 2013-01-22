package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Infiltrate extends SpecialRule {

    public Infiltrate() {
        super("Infiltrieren", "Infiltrate", "Mindestens ein Modell, dann wird die Einheit zuletzt aufgestellt." +
        		                    "\nKönnen irgendwo auf dem Spielfeld platziert werden solang mehr als 12 Zoll von Feindlichen Einheiten und keine Sichtlinie." +
        		                    "\nKönnen irgendwo auf dem Spielfeld platziert werden solang mehr als 18 Zoll von Feindlichen Einheiten." +
        		                    "\nDarf im ersten Spielzug nicht angreifen." +
        		                    "\nBesitzen die Sonderregel 'Flankenangriff'." +
        		                    "\nCharakter ohne Infiltrieren kann sich keiner Einheit anschließen die dies tut.");
    }
}
