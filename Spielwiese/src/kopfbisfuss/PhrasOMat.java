package kopfbisfuss;

public class PhrasOMat {

	public static String machPhrase() {

		String[] wortListeEins = { "verl�ssliche", "erfolgsorientierte",
				"webbasierte", "allumfassende", "clevere", "kundenorientierte",
				"pfadkritische", "dynamische", "konkurrenzf�hige", "verteilte",
				"zielgerichtete" };

		String[] wortListeZwei = { "gepowerte", "haftende", "Mehrwert-",
				"zentirierte", "geclusterte", "proaktive", "Out-of-the-Box",
				"positionierte", "vernetzte", "fokussierte", "kraftvolle",
				"geordnete", "geteilte", "kooperative", "beschleunigte",
				"Multi-Tier-", "Enterprise-", "B2B-", "Frontend-" };

		String[] wortListeDrei = { "Schicht", "Endstufe", "L�sung",
				"Architektur", "Kernkompetenz", "Strategie", "Kooperation",
				"Ausrichtung", "R�umlichkeit", "Vision", "Dimension", "Mission" };

		int einsL�nge = wortListeEins.length;
		int zweiL�nge = wortListeZwei.length;
		int dreiL�nge = wortListeDrei.length;

		int rand1 = (int) (Math.random() * einsL�nge);
		int rand2 = (int) (Math.random() * zweiL�nge);
		int rand3 = (int) (Math.random() * dreiL�nge);

		String phrase = wortListeEins[rand1] + " " + wortListeZwei[rand2] + " "
				+ wortListeDrei[rand3];

		return ("Was wir brauchen, ist eine " + phrase);

	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		PhrasOMat befehl = new PhrasOMat();
		befehl.machPhrase();
		System.out.println(machPhrase());
	}

}
