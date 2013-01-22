package kopfbisfuss.spiele;

import java.util.ArrayList;

public class DotCom {

	private ArrayList<String> zellorte;

	private String name;

	public void setZellorte(ArrayList<String> orte) {
		zellorte = orte;
	}

	public void setName(String n) {
		name = n;
	}

	public String prüfDich(String benutzereingabe) {
		String ergebnis = "Vorbei";

		int index = zellorte.indexOf(benutzereingabe);
		if (index >= 0) {
			zellorte.remove(index);

			if (zellorte.isEmpty()) {
				ergebnis = "Versenkt";
				System.out
						.println("Arr! Ye had crushed the " + name + "  ò_ó ");
			} else
				ergebnis = "Treffer";
		}
		return ergebnis;
	}
}
