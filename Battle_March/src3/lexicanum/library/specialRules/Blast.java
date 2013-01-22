package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Blast extends SpecialRule {

    public Blast(int b) {
        super("Explosiv", "Blast", b + " Zoll Schablone." +
        		                "\nAbweichung 2W6 - BF des Schützen." +
        		                "\nBenötigt Sichtlinie zum Ziel." +
        		                "\nTreffer für vollständig oder teilweise unter der Schablone befindliche Modelle." +
        		                "\nMehrere Explosiv-Waffen werden nacheinander abgehandelt." +
        		                "\nBei wiederholungen wird der Abweichungswürfel und die 2W6 neu geworfen." +
        		                "\nKeine Schnellschüsse erlaubt.");
    }
}
