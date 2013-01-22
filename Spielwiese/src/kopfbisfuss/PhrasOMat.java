package kopfbisfuss;

public class PhrasOMat {

	public static String machPhrase() {

		String[] wortListeEins = { "verlässliche", "erfolgsorientierte",
				"webbasierte", "allumfassende", "clevere", "kundenorientierte",
				"pfadkritische", "dynamische", "konkurrenzfähige", "verteilte",
				"zielgerichtete" };

		String[] wortListeZwei = { "gepowerte", "haftende", "Mehrwert-",
				"zentirierte", "geclusterte", "proaktive", "Out-of-the-Box",
				"positionierte", "vernetzte", "fokussierte", "kraftvolle",
				"geordnete", "geteilte", "kooperative", "beschleunigte",
				"Multi-Tier-", "Enterprise-", "B2B-", "Frontend-" };

		String[] wortListeDrei = { "Schicht", "Endstufe", "Lösung",
				"Architektur", "Kernkompetenz", "Strategie", "Kooperation",
				"Ausrichtung", "Räumlichkeit", "Vision", "Dimension", "Mission" };

		int einsLänge = wortListeEins.length;
		int zweiLänge = wortListeZwei.length;
		int dreiLänge = wortListeDrei.length;

		int rand1 = (int) (Math.random() * einsLänge);
		int rand2 = (int) (Math.random() * zweiLänge);
		int rand3 = (int) (Math.random() * dreiLänge);

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
