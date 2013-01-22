package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Blast extends SpecialRule {

    public Blast(int b) {
        super("Explosiv", "Blast", b + " Zoll Schablone." +
        		                "\nAbweichung 2W6 - BF des Sch�tzen." +
        		                "\nBen�tigt Sichtlinie zum Ziel." +
        		                "\nTreffer f�r vollst�ndig oder teilweise unter der Schablone befindliche Modelle." +
        		                "\nMehrere Explosiv-Waffen werden nacheinander abgehandelt." +
        		                "\nBei wiederholungen wird der Abweichungsw�rfel und die 2W6 neu geworfen." +
        		                "\nKeine Schnellsch�sse erlaubt.");
    }
}
