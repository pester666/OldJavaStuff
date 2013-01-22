package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class SlowAndResilent extends SpecialRule {

    public SlowAndResilent() {
        super("Langsam und Entschlossen", "Slow and Resilent", "Darf nicht rennen, Turboboosten, Vollgas geben oder Abwehrfeuern." +
        		                                        "\nZählt jedoch als stationär für Waffen vom Typ Schwer, Geschütz und Salve, selbst wenn es sich bewegt hat." +
        		                                        "\n Darf bei den genannten Typen und Schnellfeuerwaffen sogar in den Nahkampf angreifen.");
    }
}
