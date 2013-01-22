package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Scout extends SpecialRule {

    public Scout() {
        super("Scout", "Scout", "Mindestens ein Modell, dann darf die Einheit sich bis zu 6 Zoll neu aufstellen, nachdem alles aufgestellt wurde, aber vor Infiltratoren." +
        		        "\nZ�hlt f�r Infanterie, Artillerie, L�ufer und Monstr�se Kreatur." +
        		        "\nAlles andere darf sich bis zu 12 Zoll neu aufstellen." +
        		        "\nFalls Scoutbewegung dann im ersten Zug nicht angreifen." +
        		        "\nMuss mehr als 12 Zoll vom Gegner entfernt sein.");
    } 
}
